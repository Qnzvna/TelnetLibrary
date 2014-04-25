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
 *
 * @author TheDamianAbel
 */
public class TerminalType extends Option {
    
    private static TerminalType instance;
    
    private TerminalType(){
        super();
        code = Options.TERMINAL_TYPE;
        setting = "xterm";
    }
    
    public static TerminalType getInstance(){
        if(instance == null){
            instance = new TerminalType();
        }
        return instance;
    }

    @Override
    public void will(ConnectionBuffer buffer) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        if(sentDo){
            list.add(Commands.IAC);
            list.add(Commands.SB);
            list.add(code);
            list.add(1);
            list.add(Commands.IAC);
            list.add(Commands.SE);
            buffer.write(list);
        }else{
            list.add(Commands.IAC);
            list.add(Commands.DO);
            list.add(code);
            buffer.write(list);
            sentDo = true;
        }
    }

    @Override
    public void doo(ConnectionBuffer buffer) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Commands.IAC);
        list.add(Commands.WILL);
        list.add(code);
        buffer.write(list);
        sentRequest = true;
    }
    
    @Override
    public void sentSetting(ConnectionBuffer buffer) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Commands.IAC);
        list.add(Commands.SB);
        list.add(Options.TERMINAL_TYPE);
        list.add(0);
        int tmp;
        for(int i = 0;i<setting.length();i++){
            tmp = (int) setting.charAt(i);
            list.add(tmp);
        }
        list.add(Commands.IAC);
        list.add(Commands.SE);
        
        buffer.write(list);
        negotiated = true;
    }
    
}
