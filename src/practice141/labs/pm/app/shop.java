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
package practice141.labs.pm.app;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.Predicate;
import practice141.labs.pm.data.ProductManager;
import practice141.labs.pm.data.Rating;
import practice141.labs.pm.data.Product;

/**
 * {@code Shop} class represents an application that manages Products
 *
 * @author daniel.casique
 *
 * Refers: Practice 14-1: Redesign ProductManager as a Singleton			
 */
public class shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductManager pm = ProductManager.getInstance();
        pm.printReport(101, "en-GB");
        pm.printReport(101, "es-CO");
    }

}
