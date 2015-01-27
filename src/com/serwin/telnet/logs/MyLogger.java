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
package com.serwin.telnet.logs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Klasa do logowania błędów i komunikatów
 * @author TheDamianAbel <damian.abel.serwin@gmail.com>
 */
public class MyLogger {

    public static boolean on = false;

    private MyLogger() {

    }
    
    /**
     * Wpisywanie komunikatu z datą na strumień err.
     * @param message 
     */
    public static void log(String message) {
        if (on) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String date = dateFormat.format(cal.getTime());
            System.err.println("TelnetLibrary-" + date + ": " + message);
        }
    }
}
