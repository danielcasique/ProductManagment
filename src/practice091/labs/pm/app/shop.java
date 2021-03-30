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
package practice091.labs.pm.app;

import java.math.BigDecimal;
import java.util.Locale;
import practice091.labs.pm.data.ProductManager;
import practice091.labs.pm.data.Product;
import practice091.labs.pm.data.Rating;

/**
 * {@code Shop} class represents an application that manages Products
 *
 * @author daniel.casique
 *
 * Refers: Practice 9-1: Organize Products and Reviews into a HashMap
 */
public class shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductManager pm = new ProductManager(Locale.UK);
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        pm.printReport(p1);
        p1 = pm.reviewProduct(p1, Rating.FOUR_STARTS, "Nice hot cup of tea");
        p1 = pm.reviewProduct(p1, Rating.TWO_STARTS, "Rather weak tea");
        p1 = pm.reviewProduct(p1, Rating.FOUR_STARTS, "Fine tea");
        p1 = pm.reviewProduct(p1, Rating.FOUR_STARTS, "Good tea");
        p1 = pm.reviewProduct(p1, Rating.FIVE_STARTS, "Perfect tea");
        p1 = pm.reviewProduct(p1, Rating.THREE_STARTS, "Just add some lemon");
        pm.printReport(p1);

        Product p2 = pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        p2 = pm.reviewProduct(p2, Rating.THREE_STARTS, "Coffee was ok");
        p2 = pm.reviewProduct(p2, Rating.ONE_START, "Where is the milk?!");
        p2 = pm.reviewProduct(p2, Rating.FIVE_STARTS, "It's perfect wih ten spoons of sugar!");
        pm.printReport(p2);
        
        Product p3 = pm.createProduct(103, "Cake", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        p3 = pm.reviewProduct(p3, Rating.FIVE_STARTS, "Very nice cake");
        p3 = pm.reviewProduct(p3, Rating.FOUR_STARTS, "It's good, but I've expected more chocolate");
        p3 = pm.reviewProduct(p3, Rating.FIVE_STARTS, "This cake is perfect");
        pm.printReport(p3);

        Product p4 = pm.createProduct(104, "Cookie", BigDecimal.valueOf(2.99), Rating.NOT_RATED);
        p4 = pm.reviewProduct(p4, Rating.THREE_STARTS, "Just another cookie");
        p4 = pm.reviewProduct(p4, Rating.THREE_STARTS, "Ok");
        pm.printReport(p4);

        Product p5 = pm.createProduct(105, "Hot Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED);
        p5 = pm.reviewProduct(p5, Rating.FOUR_STARTS, "Tasty!");
        p5 = pm.reviewProduct(p5, Rating.FOUR_STARTS, "Not bad at all");
        pm.printReport(p5);
        
        Product p6 = pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED);
        p6 = pm.reviewProduct(p6, Rating.TWO_STARTS, "Too sweet");
        p6 = pm.reviewProduct(p6, Rating.THREE_STARTS, "Better than cookie");
        p6 = pm.reviewProduct(p6, Rating.TWO_STARTS, "Too bitter");
        p6 = pm.reviewProduct(p6, Rating.ONE_START, "I don't get it!");
        pm.printReport(p6);

    }

}
