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
package com.serwin.telnet.options;

import java.io.IOException;
import java.util.ArrayList;

import com.serwin.telnet.Commands;
import com.serwin.telnet.Options;
import com.serwin.telnet.connection.ConnectionBuffer;

/**
 * Opcja Suppress Go Ahead <a href="http://www.ietf.org/rfc/rfc858.txt">RFC858</a>
 * @author TheDamianAbel
 */
public class SuppressGoAhead extends Option{
    
    private static SuppressGoAhead instance;
    
    private SuppressGoAhead(){
        super();
        code = Options.SUPPRESS_GO_AHEAD;
    }
    
    public static SuppressGoAhead getInstance(){
        if(instance == null){
            instance = new SuppressGoAhead();
        }
        return instance;
    }

    @Override
    public void will(ConnectionBuffer buffer) throws IOException{
        if(sentDo){
            negotiated = true;
        }
    }

    @Override
    public void doo(ConnectionBuffer buffer) throws IOException{
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Commands.IAC);
        list.add(Commands.WILL);
        list.add(code);
        buffer.write(list);
        negotiated = true;
    }

    @Override
    public void sentSetting(ConnectionBuffer buffer) throws IOException {
    }

}
