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
package telnet.options;

import connection.ConnectionBuffer;
import java.io.IOException;
import java.util.ArrayList;
import telnet.Commands;
import telnet.Options;

/**
 * Opcja Echo @see <a href =
 * "http://tools.ietf.org/html/rfc857">RFC857</a>
 * @author TheDamianAbel
 */
public class Echo extends Option {
    
    private static Echo instance;
    
    private Echo(){
        super();
        code = Options.ECHO;
    }
    
    /**
     * Zwrócenie danej instancji opcji. Implementacja wzorca Singleton.
     * @return 
     */
    public static Echo getInstance(){
        if(instance == null){
            instance = new Echo();
        }
        return instance;
    }

    /**
     * Wykonywanie odpowiedzi na wiadomość z serwera: WILL ECHO
     * @param buffer
     * @throws IOException 
     */
    @Override
    public void will(ConnectionBuffer buffer) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Commands.IAC);
        list.add(Commands.DO);
        list.add(code);
        buffer.write(list);
        negotiated = true;
    }

    /**
     * Wykonanie odpowiedzi na wiadomość z serwera: DO ECHO
     * @param buffer
     * @throws IOException 
     */
    @Override
    public void doo(ConnectionBuffer buffer) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Commands.IAC);
        list.add(Commands.WONT);
        list.add(code);
        buffer.write(list);
    }

    /**
     * Wysłanie poszczególnych ustawień związanych z opcją do serwera.<p>
     * brak specjalnych ustawień
     * @param buffer
     * @throws IOException 
     */
    @Override
    public void sentSetting(ConnectionBuffer buffer) throws IOException {
    }

}
