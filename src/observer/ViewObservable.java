/*
 * Copyright (C) 2014 TheDamianAbel <damian.abel.serwin@gmail.com>
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

package observer;

import java.io.IOException;

/**
 *
 * @author TheDamianAbel <damian.abel.serwin@gmail.com>
 */
public interface ViewObservable {
    
    public void registerObserver(ViewObserver o);
    public void removeObserver(ViewObserver o);
    public void notifyView(String text) throws IOException;
    
}
