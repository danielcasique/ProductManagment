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
package practice062.labs.pm.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import practice062.labs.pm.data.Drink;
import practice062.labs.pm.data.Food;
import practice062.labs.pm.data.Product;
import practice062.labs.pm.data.Rating;


/**
 * {@code Shop} class represents an application that manages Products
 * @author daniel.casique
 * 
 * refers: Practice 6-2: Override Methods and Use Polymorphism…Part 2			
 */
public class shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Object p1 = new Product(101, "Tea", BigDecimal.valueOf(1.99));
       Product p2 = new Drink(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_STARTS);
       Product p3 = new Food(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STARTS, LocalDate.now().plusDays(2));
       Product p4 = new Product();
       Product p5 = p3.applyRating(Rating.THREE_STARTS);
       Product p6 = new Drink(104, "Chocolate", BigDecimal.valueOf(1.99), Rating.FIVE_STARTS);
       Product p7 = new Food(104, "Chocolate", BigDecimal.valueOf(3.99), Rating.FIVE_STARTS, LocalDate.now().plusDays(2));
       System.out.println(p6.equals(p7));
       System.out.println(p1);
       System.out.println(p2);
       System.out.println(p3);
       System.out.println(p4);
       System.out.println(p5);
        
    }
    
}
