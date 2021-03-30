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
package practice072.labs.pm.data;

/**
 *
 * @author daniel.casique
 */
@FunctionalInterface
public interface Rateable<T> {
    
    public static final Rating DEFAULT_RATING = Rating.NOT_RATED;    
    
    T applyRating(Rating rating);
    
    public default T applyRating(int starts){
        return applyRating(convert(starts));
    }
    
    public default Rating getRating(){
        return DEFAULT_RATING;
    }
    
    public static Rating convert(int starts){
        return (starts>=0 && starts<=5)? Rating.values()[starts] : DEFAULT_RATING;
    }
    
}
