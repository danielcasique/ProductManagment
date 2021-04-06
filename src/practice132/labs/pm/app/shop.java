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
package practice132.labs.pm.app;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.Predicate;
import practice132.labs.pm.data.ProductManager;
import practice132.labs.pm.data.Rating;
import practice132.labs.pm.data.Product;

/**
 * {@code Shop} class represents an application that manages Products
 *
 * @author daniel.casique
 *
 * Refers: 
 * # Practice 13-2: Bulk-Load Data from Files…Part 1 
 * # Practice 13-2: Bulk-Load Data from Files…Part 2
 */
public class shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductManager pm = new ProductManager("es-CO");
        pm.printReport(101);
        pm.createProduct(164, "kombucha", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        pm.reviewProduct(164, Rating.TWO_STARTS, "Looks like tea but is it?");
        pm.reviewProduct(164, Rating.FOUR_STARTS, "Fine tea");
        pm.reviewProduct(164, Rating.FOUR_STARTS, "This is not tea");
        pm.reviewProduct(164, Rating.FIVE_STARTS, "Perfect!");
        pm.printReport(164);
        pm.printProducts(p -> p.getPrice().floatValue() < 2,
                (p1, p2) -> p2.getRating().ordinal() - p1.getRating().ordinal());
        pm.getDiscounts().forEach((rating, discount) -> System.out.println(rating + "\t" + discount));
    }

}
