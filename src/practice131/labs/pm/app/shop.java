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
package practice131.labs.pm.app;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.Predicate;
import practice131.labs.pm.data.ProductManager;
import practice131.labs.pm.data.Rating;
import practice131.labs.pm.data.Product;

/**
 * {@code Shop} class represents an application that manages Products
 *
 * @author daniel.casique
 *
 * Refers: Practice 13-1: Print Product Report to a File
 */
public class shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductManager pm = new ProductManager("es-CO");
//        pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        pm.parseProduct("D,101,Tea,1.99,0,2019-09-19");
        pm.printReport(101);
        pm.parseReview("101,4,Nice hot cup of tea");
        pm.parseReview("101,2,Rather weak tea");
        pm.parseReview("101,4,Fine tea");
        pm.parseReview("101,4,Good tea");
        pm.parseReview("101,5,Perfect tea");
        pm.parseReview("101,3,Just add some lemon");
//        pm.reviewProduct(101, Rating.FOUR_STARTS, "Nice hot cup of tea");
//        pm.reviewProduct(101, Rating.TWO_STARTS, "Rather weak tea");
//        pm.reviewProduct(101, Rating.FOUR_STARTS, "Fine tea");
//        pm.reviewProduct(101, Rating.FOUR_STARTS, "Good tea");
//        pm.reviewProduct(101, Rating.FIVE_STARTS, "Perfect tea");
//        pm.reviewProduct(101, Rating.THREE_STARTS, "Just add some lemon");
        pm.printReport(101);
        pm.parseProduct("F,103,Cake,3.99,0,2019-09-19");
        pm.printReport(103);
//        pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
//        pm.reviewProduct(102, Rating.THREE_STARTS, "Coffee was ok");
//        pm.reviewProduct(102, Rating.ONE_START, "Where is the milk?!");
//        pm.reviewProduct(102, Rating.FIVE_STARTS, "It's perfect wih ten spoons of sugar!");
////        pm.printReport(102);
//
//        pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_RATED);
//        pm.reviewProduct(103, Rating.FIVE_STARTS, "Very nice cake");
//        pm.reviewProduct(103, Rating.FOUR_STARTS, "It's good, but I've expected more chocolate");
//        pm.reviewProduct(103, Rating.FIVE_STARTS, "This cake is perfect");
////        pm.printReport(103);
//
//        pm.createProduct(104, "Cookie", BigDecimal.valueOf(2.99), Rating.NOT_RATED);
//        pm.reviewProduct(104, Rating.THREE_STARTS, "Just another cookie");
//        pm.reviewProduct(104, Rating.THREE_STARTS, "Ok");
////        pm.printReport(104);
//
//        pm.createProduct(105, "Hot Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED);
//        pm.reviewProduct(105, Rating.FOUR_STARTS, "Tasty!");
//        pm.reviewProduct(105, Rating.FOUR_STARTS, "Not bad at all");
////        pm.printReport(105);
//
//        pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED);
//        pm.reviewProduct(106, Rating.TWO_STARTS, "Too sweet");
//        pm.reviewProduct(106, Rating.THREE_STARTS, "Better than cookie");
//        pm.reviewProduct(106, Rating.TWO_STARTS, "Too bitter");
//        pm.reviewProduct(106, Rating.ONE_START, "I don't get it!");
//        pm.printReport(106);
//
////        pm.printProducts((p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());
////        pm.printProducts((p1, p2) ->  p2.getPrice().compareTo(p1.getPrice()));
////        
//        Comparator<Product> ratingSorter = (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
////        Comparator<Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());
////        pm.printProducts(ratingSorter.thenComparing(priceSorter));
////        pm.printProducts(ratingSorter.thenComparing(priceSorter).reversed());
//
//        Predicate<Product> filter = p -> p.getPrice().floatValue() < 2;
//        pm.printProducts(filter, ratingSorter);
//        
//        pm.getDiscounts().forEach((rating, discount) -> System.out.println(rating + "\t" + discount));
    }

}
