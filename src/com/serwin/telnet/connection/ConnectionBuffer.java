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
package com.serwin.telnet.connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.serwin.telnet.logs.MyLogger;

/**
 * Bufor przechowujący potrzebne informacje do zarządzania połączenia oraz czytania i zapisywanie do odpowiednich strumieni.
 * @author abel
 */
public class ConnectionBuffer {

    private final BufferedInputStream reader;
    private final BufferedOutputStream writer;
    private final Socket socket;

    ConnectionBuffer(BufferedInputStream reader, BufferedOutputStream writer, Socket socket) {
        this.reader = reader;
        this.writer = writer;
        this.socket = socket;
    }

    /**
     * Return specified by length numbers of integers from connection.
     *
     * @param length
     * @return ArrayList of read integers
     * @throws IOException
     */
    public ArrayList<Integer> read(int length) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        int tmp = 0;
        while (tmp != -1 && length > 0) {
            tmp = reader.read();
            list.add(tmp);
            length--;
        }
        return list;
    }

    /**
     * Return all integers from connection.
     *
     * @return ArrayList of read integers
     * @throws IOException
     */
    public ArrayList<Integer> read() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        while (reader.available() > 0) {
            int tmp = reader.read();
            list.add(tmp);
        }
        return list;
    }

    /**
     * Write and flush to connection specified ArrayList.
     *
     * @param list
     * @throws IOException
     */
    public void write(ArrayList<Integer> list) throws IOException {
        for (int tmp : list) {
            writer.write(tmp);
        }
        writer.flush();
        MyLogger.log("Flushing...\n" + list.toString());
    }
    
    public void write(int i) throws IOException{
        writer.write(i);
        writer.flush();
        MyLogger.log("Flushing...\n" + (char) i);
    }

}
