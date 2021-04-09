/*
 * Copyright (C) 2021 daniel.casique
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package practice142.labs.pm.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author daniel.casique
 */
public class ProductManager {

    private Map<Product, List<Review>> products = new HashMap<>();

//    private ResourceFormatter formatter;
    private final ResourceBundle config = ResourceBundle.getBundle("practice142.labs.pm.data.config");
    private final MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private final MessageFormat productformat = new MessageFormat(config.getString("product.data.format"));

    private final Path reportsFolder = Path.of(config.getString("reports.folder"));
    private final Path dataFolder = Path.of(config.getString("data.folder"));
    private final Path tempFolder = Path.of(config.getString("temp.folder"));

    private static final Map<String, ResourceFormatter> formatters
            = Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "es-CO", new ResourceFormatter(new Locale("es", "CO")));

    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    private static final ProductManager pm = new ProductManager();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    private ProductManager() {
        loadAllData();
    }

    public static ProductManager getInstance() {
        return pm;
    }

    public static Set<String> getSupportedLocales() {
        return formatters.keySet();
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = null;
        try {
            writeLock.lock();
            product = new Food(id, name, price, rating, bestBefore);
            products.putIfAbsent(product, new ArrayList<>());
        } catch (Exception ex) {
            logger.log(Level.INFO, "Error adding product " + ex.getMessage());
        } finally {
            writeLock.unlock();
        }
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = null;
        try {
            writeLock.lock();
            product = new Drink(id, name, price, rating);
            products.putIfAbsent(product, new ArrayList<>());
        } catch (Exception ex) {
            logger.log(Level.INFO, "Error adding product " + ex.getMessage());
        } finally {
            writeLock.unlock();
        }
        return product;
    }

    public Product reviewProduct(int id, Rating rating, String comments) {
        try {
            writeLock.lock();
            return reviewProduct(findProduct(id), rating, comments);
        } catch (ProductManagerException ex) {
            logger.log(Level.INFO, ex.getMessage());
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    private Product reviewProduct(Product product, Rating rating, String comments) {
        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

        product = product.applyRating(
                Rateable.convert(
                        (int) Math.round(
                                reviews.stream()
                                        .mapToInt(r -> r.getRating().ordinal())
                                        .average()
                                        .orElse(0)
                        )));
        products.put(product, reviews);

        return product;
    }

    public void printProducts(Predicate<Product> filter,
            Comparator<Product> sorter,
            String languageTag) {

        try {
            readLock.lock();
            ResourceFormatter formatter
                    = formatters.getOrDefault(languageTag, formatters.get("en-GB"));

            StringBuilder txt = new StringBuilder();

            products.keySet()
                    .stream()
                    .sorted(sorter)
                    .filter(filter)
                    .forEach(p -> txt.append(formatter.formatProduct(p) + "\n"));

            System.out.println(txt);
        } finally {
            readLock.unlock();
        }
    }

    public void printProducts(Comparator<Product> sorter, String languageTag) {

        ResourceFormatter formatter
                = formatters.getOrDefault(languageTag, formatters.get("en-GB"));

        StringBuilder txt = new StringBuilder();

        products.keySet()
                .stream()
                .sorted(sorter)
                .forEach(p -> txt.append(formatter.formatProduct(p) + "\n"));

        System.out.println(txt);
    }

    public void printReport(int id, String languageTag, String client) {
        try {
            readLock.lock();
            printReport(findProduct(id), languageTag, client);
        } catch (ProductManagerException ex) {
            logger.log(Level.INFO, ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error printing product report " + ex.getMessage(), ex);
        } finally {
            readLock.unlock();
        }
    }

    public Product findProduct(int id) throws ProductManagerException {
        try {
            readLock.lock();
            return products.keySet()
                    .stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new ProductManagerException("Product with id " + id + " not found"));
        } finally {
            readLock.unlock();
        }
    }

    private void printReport(Product product, String languageTag, String client) throws IOException {
        ResourceFormatter formatter
                = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
        List<Review> reviews = products.get(product);
        Collections.sort(reviews);
        Path productFile = reportsFolder.resolve(
                MessageFormat.format(
                        config.getString("report.file"), product.getId(), client));
        try ( PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        Files.newOutputStream(productFile, StandardOpenOption.CREATE), "UTF-8"))) {
            out.append(formatter.formatProduct(product));
            out.append(System.lineSeparator());
            if (reviews.isEmpty()) {
                out.append(formatter.getText("no.reviews"));
                out.append(System.lineSeparator());
            } else {
                out.append(
                        reviews.stream()
                                .map(r -> formatter.formatReview(r) + System.lineSeparator())
                                .collect(Collectors.joining()));
            };
        }
    }

    private Review parseReview(String text) {
        Review review = null;
        try {
            Object[] values = reviewFormat.parse(text);
            review = new Review(
                    Rateable.convert(Integer.parseInt((String) values[0])),
                    (String) values[1]);
        } catch (ParseException | NumberFormatException ex) {
            logger.log(Level.WARNING, "Error parsing review " + text);
        }
        return review;
    }

    private Product parseProduct(String text) {
        Product product = null;
        try {
            Object[] values = productformat.parse(text);
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            switch ((String) values[0]) {
                case "D":
                    product = new Drink(id, name, price, rating);
                    break;
                case "F":
                    LocalDate bestBefore = LocalDate.parse((String) values[5]);
                    product = new Food(id, name, price, rating, bestBefore);
                    break;
            }

        } catch (ParseException | NumberFormatException | DateTimeParseException ex) {
            logger.log(Level.WARNING, "Error parsing review " + text);
        }
        return product;
    }

    private void loadAllData() {
        try {
            products = Files.list(dataFolder)
                    .filter(file -> file.getFileName().toString().startsWith("product"))
                    .map(file -> loadProduct(file))
                    .filter(product -> product != null)
                    .collect(Collectors.toMap(product -> product, product -> loadReviews(product)));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error loading data" + ex.getMessage(), ex);
        }
    }

    private Product loadProduct(Path file) {
        Product product = null;
        try {
            product = parseProduct(
                    Files.lines(
                            dataFolder.resolve(file), Charset.forName("UTF-8"))
                            .findFirst()
                            .orElseThrow());
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error loading product " + ex.getMessage());
        }
        return product;
    }

    private List<Review> loadReviews(Product product) {
        Path file = dataFolder.resolve(
                MessageFormat.format(
                        config.getString("reviews.data.file"), product.getId())
        );
        List<Review> reviews = null;
        if (Files.notExists(file)) {
            reviews = new ArrayList<>();
        } else {
            try {
                reviews = Files.lines(file, Charset.forName("UTF-8"))
                        .map(text -> parseReview(text))
                        .filter(review -> review != null)
                        .collect(Collectors.toList());
            } catch (IOException ex) {
                logger.log(Level.WARNING, "Error loading reviews " + ex.getMessage());
            }
        }

        return reviews;
    }

    public void dumpData() {
        try {
            if (Files.notExists(tempFolder)) {
                Files.createDirectory(tempFolder);
            }
            Path tempFile = tempFolder.resolve(
                    MessageFormat.format(
                            config.getString("temp.file"), Instant.now().toEpochMilli()));
            try ( ObjectOutputStream out = new ObjectOutputStream(
                    Files.newOutputStream(tempFile, StandardOpenOption.CREATE))) {
                out.writeObject(products);
//                products = new HashMap<>();
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error dumping data " + ex.getMessage(), ex);
        }
    }

    @SuppressWarnings("unchecked")
    public void restoreData() {
        try {
            Path tempFile = Files.list(tempFolder)
                    .filter(path -> path.getFileName().toString().endsWith("tmp"))
                    .findFirst().orElseThrow();
            try ( ObjectInputStream in
                    = new ObjectInputStream(
                            Files.newInputStream(tempFile, StandardOpenOption.DELETE_ON_CLOSE))) {
                products = (HashMap) in.readObject();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error restoring data " + ex.getMessage(), ex);
        }
    }

    public Map<String, String> getDiscounts(String languageTag) {
        try {
            readLock.lock();
            ResourceFormatter formatter = formatters.getOrDefault(languageTag, formatters.get("en-GB"));
            return products.keySet()
                    .stream()
                    .collect(
                            Collectors.groupingBy(
                                    product -> product.getRating().getStarts(),
                                    Collectors.collectingAndThen(
                                            Collectors.summingDouble(
                                                    product -> product.getDiscount().doubleValue()),
                                            discount -> formatter.moneyFormat.format(discount)
                                    )
                            )
                    );
        } finally {
            readLock.unlock();
        }

    }

    private static class ResourceFormatter {

        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormmat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale) {
            this.locale = locale;
            resources = ResourceBundle.getBundle("practice142.labs.pm.data.resources", locale);
            dateFormmat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product) {
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStarts(),
                    dateFormmat.format(product.getBestBefore()));
        }

        private String formatReview(Review review) {
            return MessageFormat.format(resources.getString("review"),
                    review.getRating().getStarts(), review.getComments());
        }

        private String getText(String key) {
            return resources.getString(key);
        }
    }

}
