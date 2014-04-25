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

package telnet.options;

import connection.ConnectionBuffer;
import java.io.IOException;

/**
 *
 * @author TheDamianAbel <damian.abel.serwin@gmail.com>
 */
public abstract class Option {
    
    /**
     * Code of the option.
     */
    protected int code;
    /**
     * Negotiation of the option with server.
     */
    protected boolean negotiated;
    /**
     * True if will was sent from client.
     */
    protected boolean sentRequest;
    /**
     * True if do was sent from client.
     */
    protected boolean sentDo;
    
    protected String setting;
    
    protected Option(){
        code = 0;
        negotiated = false;
        sentRequest = false;
        sentDo = false;
    }
    
    public static Option getInstance(int code){
        Option instance = null;
        switch(code){
            case 3:
                instance = SuppressGoAhead.getInstance();
                break;
            case 24:
                instance = TerminalType.getInstance();
                break;
            case 1:
                instance = Echo.getInstance();
                break;
        }
        return instance;
    }
    
    
    public boolean isNegotiated(){
        return negotiated;
    }
    
    public boolean isRequest(){
        return sentRequest;
    }
    
    public boolean isDo(){
        return sentDo;
    }
    
    public int getCode(){
        return code;
    }
    
    public void setNegotiated(boolean negotiated){
        this.negotiated = negotiated;
    }
    
    public void setRequest(boolean sentRequest){
        this.sentRequest = sentRequest;
    }
    
    public void setDo(boolean sentDo){
        this.sentDo = sentDo;
    }
    
    public void setSetting(String setting){
        this.setting = setting;
    }

    public abstract void will(ConnectionBuffer buffer) throws IOException;
    public abstract void doo(ConnectionBuffer buffer) throws IOException;
    public abstract void sentSetting(ConnectionBuffer buffer) throws IOException;
    
}
