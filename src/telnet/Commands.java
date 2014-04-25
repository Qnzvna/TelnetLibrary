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
package telnet;

/**
 *
 * @author TheDamianAbel
 */
public class Commands {

    // ###COMMANDS START###
    //End of subnegotiation parameters.
    public static final int SE = 240;
    //No operation.
    public static final int NOP = 241;
    //The data stream portion of a Synch.
    public static final int DATA_MARK = 242;
    //NVT character BRK.
    public static final int BREAK = 243;
    //The function IP.
    public static final int INTERRUPT_PROCESS = 244;
    //The function AO.
    public static final int ABORT_OUTPUT = 245;
    //The function AYT.
    public static final int ARE_YOU_THERE = 246;
    //The function EC.
    public static final int EREASE_CHAR = 247;
    //The function EL.
    public static final int EREASE_LINE = 248;
    //The GA signal.
    public static final int GA = 249;
    //The SB signal. Suboption
    public static final int SB = 250;
    /*Indicates the desire to beginperforming, or confirmation that you are now 
     performing, theindicated option.*/
    public static final int WILL = 251;
    //Indicates the refusal to perform,or continue performing, the indicated option.
    public static final int WONT = 252;
    /*Indicates the request that the other party perform, or confirmation that
     you are expecting the other party to perform, the indicated option.*/
    public static final int DO = 253;
    /*Indicates the demand that theother party stop performing, or confirmation 
     that you are no longer expecting the other party to perform,
     the indicated option.*/
    public static final int DONT = 254;
    //Data Byte 255.
    public static final int IAC = 255;

    public static final int[] COMMANDS = {
        SE, NOP, DATA_MARK,
        BREAK, INTERRUPT_PROCESS, ABORT_OUTPUT, ARE_YOU_THERE, EREASE_CHAR,
        EREASE_LINE, GA, SB, WILL, WONT, DO, DONT, IAC,};
}
