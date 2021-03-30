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
package practice071.labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import practice071.labs.pm.data.ProductManager;
import practice071.labs.pm.data.Product;
import practice071.labs.pm.data.Rating;

/**
 * {@code Shop} class represents an application that manages Products
 *
 * @author daniel.casique
 * 
 * Refers: Practice 7-1: Design the Rateable Interface			
 */
public class shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductManager pm = new ProductManager();
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.THREE_STARTS);
        Product p2 = pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STARTS);
        Product p3 = pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STARTS, LocalDate.now().plusDays(2));
        Product p4 = pm.createProduct(105, "Cookie", BigDecimal.valueOf(3.99), Rating.TWO_STARTS, LocalDate.now());
        Product p5 = p3.applyRating(Rating.THREE_STARTS);
        Product p6 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(1.99), Rating.FIVE_STARTS);
        Product p7 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(3.99), Rating.FIVE_STARTS, LocalDate.now().plusDays(2));
        Product p8 = p4.applyRating(Rating.FIVE_STARTS);
        Product p9 = p1.applyRating(Rating.TWO_STARTS);
        System.out.println(p6.equals(p7));
        System.out.println(p1.getBestBefore());
        System.out.println(p3.getBestBefore());
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
        System.out.println(p8);
        System.out.println(p9);
    }

}
