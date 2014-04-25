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
 * Contains options code, and options objects.
 * <p>
 * Only implemented options are commented.
 *
 * @author TheDamianAbel
 */
public class Options {

    // ###IMPLEMENTED START###
    public static final int BINARY = 0;

    /**
     * Telnet Echo Option @see <a href =
     * "http://tools.ietf.org/html/rfc857">RFC857</a>
     */
    public static final int ECHO = 1;
    public static final int PREPARE_TO_RECONNECT = 2;

    /**
     * Telnet Suppress-Go-Ahead @see <a
     * href = "http://tools.ietf.org/html/rfc858">RFC858</a>
     */
    public static final int SUPPRESS_GO_AHEAD = 3;
    public static final int APPROXIMATE_MESSAGE_SIZE = 4;
    public static final int STATUS = 5;
    public static final int TIMING_MARK = 6;
    public static final int REMOTE_CONTROLLED_TRANSMISSION = 7;
    public static final int NEGOTIATE_OUTPUT_LINE_WIDTH = 8;
    public static final int NEGOTIATE_OUTPUT_PAGE_SIZE = 9;
    public static final int NEGOTIATE_CARRIAGE_RETURN = 10;
    public static final int NEGOTIATE_HORIZONTAL_TAB_STOP = 11;
    public static final int NEGOTIATE_HORIZONTAL_TAB = 12;
    public static final int NEGOTIATE_FORMFEED = 13;
    public static final int NEGOTIATE_VERTICAL_TAB_STOP = 14;
    public static final int NEGOTIATE_VERTICAL_TAB = 15;
    public static final int NEGOTIATE_LINEFEED = 16;
    public static final int EXTENDED_ASCII = 17;
    public static final int FORCE_LOGOUT = 18;
    public static final int BYTE_MACRO = 19;
    public static final int DATA_ENTRY_TERMINAL = 20;
    public static final int SUPDUP = 21;
    public static final int SUPDUP_OUTPUT = 22;
    public static final int SEND_LOCATION = 23;

    /**
     * Telnet Terminal-Type RFC1091 see <http://tools.ietf.org/html/rfc1091>
     */
    public static final int TERMINAL_TYPE = 24;
    public static final int END_OF_RECORD = 25;
    public static final int TACACS_USER_IDENTIFICATION = 26;
    public static final int OUTPUT_MARKING = 27;
    public static final int TERMINAL_LOCATION_NUMBER = 28;
    public static final int REGIME_3270 = 29;
    public static final int X3_PAD = 30;
    public static final int WINDOW_SIZE = 31;
    public static final int TERMINAL_SPEED = 32;
    public static final int REMOTE_FLOW_CONTROL = 33;
    public static final int LINEMODE = 34;
    public static final int X_DISPLAY_LOCATION = 35;
    public static final int OLD_ENVIRONMENT_VARIABLES = 36;
    public static final int AUTHENTICATION = 37;
    public static final int ENCRYPTION = 38;
    public static final int NEW_ENVIRONMENT_VARIABLES = 39;
    public static final int EXTENDED_OPTIONS_LIST = 255;
    // ###IMPLEMENTED END###
    /*public static final int[] IMPLEMENTED = {
     BINARY, ECHO, PREPARE_TO_RECONNECT, SUPPRESS_GO_AHEAD,
     APPROXIMATE_MESSAGE_SIZE, STATUS, TIMING_MARK,
     REMOTE_CONTROLLED_TRANSMISSION, NEGOTIATE_OUTPUT_LINE_WIDTH,
     NEGOTIATE_OUTPUT_LINE_WIDTH, NEGOTIATE_OUTPUT_PAGE_SIZE,
     NEGOTIATE_CARRIAGE_RETURN, NEGOTIATE_HORIZONTAL_TAB_STOP,
     NEGOTIATE_HORIZONTAL_TAB, NEGOTIATE_FORMFEED, NEGOTIATE_VERTICAL_TAB_STOP,
     NEGOTIATE_VERTICAL_TAB, NEGOTIATE_LINEFEED, EXTENDED_ASCII,
     FORCE_LOGOUT, BYTE_MACRO, DATA_ENTRY_TERMINAL, SUPDUP, SUPDUP_OUTPUT,
     SEND_LOCATION, TERMINAL_TYPE, END_OF_RECORD, TACACS_USER_IDENTIFICATION,
     OUTPUT_MARKING, TERMINAL_LOCATION_NUMBER, REGIME_3270, X3_PAD, WINDOW_SIZE,
     TERMINAL_SPEED, REMOTE_FLOW_CONTROL, LINEMODE, X_DISPLAY_LOCATION,
     OLD_ENVIRONMENT_VARIABLES, AUTHENTICATION, ENCRYPTION,
     NEW_ENVIRONMENT_VARIABLES, EXTENDED_OPTIONS_LIST   
     };*/

    /**
     * Contains all implemented options.
     */
    public static final int[] IMPLEMENTED = {
        TERMINAL_TYPE, SUPPRESS_GO_AHEAD, ECHO
    };

}
