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
package practice061.labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import practice061.labs.pm.data.Drink;
import practice061.labs.pm.data.Food;
import practice061.labs.pm.data.Product;
import practice061.labs.pm.data.Rating;


/**
 * {@code Shop} class represents an application that manages Products
 * @author daniel.casique
 * 
 * Refers: Practice 5-3: Make Product Objects Immutable			
 */
public class shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Product p1 = new Product(101, "Tea", BigDecimal.valueOf(1.99));
       Product p2 = new Drink(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STARTS);
       Product p3 = new Food(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STARTS, LocalDate.now().plusDays(2));
       Product p4 = new Product();
       Product p5 = p3.applyRating(Rating.THREE_STARTS);
       System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getRating().getStarts());
       System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getRating().getStarts());
       System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getRating().getStarts());
       System.out.println(p4.getId() + " " + p4.getName() + " " + p4.getPrice() + " " + p4.getRating().getStarts());
       System.out.println(p5.getId() + " " + p5.getName() + " " + p5.getPrice() + " " + p5.getRating().getStarts());
    }
    
}
