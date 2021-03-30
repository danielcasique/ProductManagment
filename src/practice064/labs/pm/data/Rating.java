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
package practice064.labs.pm.data;

/**
 *
 * @author daniel.casique
 */
public enum Rating {
    
    NOT_RATED("\u2606\u2606\u2606\u2606\u2606"),
    ONE_START("\u2605\u2606\u2606\u2606\u2606"),
    TWO_STARTS("\u2605\u2605\u2606\u2606\u2606"),
    THREE_STARTS("\u2605\u2605\u2605\u2606\u2606"),
    FOUR_STARTS("\u2605\u2605\u2605\u2605\u2606"),
    FIVE_STARTS("\u2605\u2605\u2605\u2605\u2605");
    
    private String starts;

    private Rating(String starts){
        this.starts = starts;
    }

    public String getStarts() {
        return starts;
    }
    
}
