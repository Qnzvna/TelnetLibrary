/*
 * Copyright (C) 2014 TheDamianAbel
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

package com.serwin.telnet;


/**
 * Klasa do ustawień klienta serwera.
 * @author TheDamianAbel <damian.abel.serwin@gmail.com>
 */
public class Settings {
    
    /**
     * Ustawienia Terminal_Type
     */
    private static String terminal_type;

    /**
     * @return the terminal_type
     */
    public static String getTerminal_type() {
        return terminal_type;
    }

    /**
     * @param terminal_type the terminal_type to set
     */
    public static void setTerminal_type(String terminal_type) {
        Settings.terminal_type = terminal_type;
    }
    
    
}
