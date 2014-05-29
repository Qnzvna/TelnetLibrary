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

package connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Klasa statyczna wydająca bufer do konkretnego połączenia.<br>
 * Implementuje para-wzorzec Fabryka.
 * @author abel
 */
public class ConnectionGiver {
    
    /**
     * Tworzy i zwraca ConnectionBuffer.
     * <p>
     * Implementuje meta-wzorzec Fabryka.
     * @param port Port
     * @param hostname Hostname
     * @return ConnectionBuffer
     * @throws UnknownHostException
     * @throws IOException 
     */
    public static ConnectionBuffer returnBuffer(int port, String hostname) throws UnknownHostException, IOException{
        Socket socket = new Socket(hostname, port);
        BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream writer = new BufferedOutputStream(socket.getOutputStream());
        ConnectionBuffer buffer = new ConnectionBuffer(reader, writer, socket);
        return buffer;
    }
}
