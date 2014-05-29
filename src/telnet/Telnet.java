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
package telnet;

import connection.ConnectionBuffer;
import connection.ConnectionGiver;
import observer.TelnetObservable;
import observer.TelnetObserver;
import telnet.options.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import logs.MyLogger;

/**
 * Główna klasa telnet.
 * <p>
 * Przykładowe, a zarazem podstawowe użycie klasy w Kontrolerze.
 * <p>
 * <pre>
 * {@code
 * telnet = new Telnet(host, port);
 * telnet.registerObserver(this);
 * telnet.handshake();
 * while (loop) {
 *  telnet.read();
 * }
 * }
 * </pre>
 * 
 * Pełna biblioteka Telnet-u korzysta z modelu <b>Model-Widok-Kontroller</b>. 
 * Do poprawnego z niej korzystania korzystne są odpowiedni <b>Widok</b> oraz <b>Kontroler</b>.
 * 
 * <b>Kontroler</b> musi implementować {@link observer.TelnetObserver} oraz {@link observer.ViewObserver}. 
 * Tworzyć nową instancję Telnetu i następnie rejestrować ją jako obserwatora. 
 * Klasa <b>Widoku</b> musi implementować {@link observer.ViewObservable} i 
 * również powinna być zarejestrowana jako obserwator w <b>Kontrolerze</b>
 * 
 * Zastosowanie tego wzorca projektowego umożliwia i usprawnia korzystanie z biblioteki 
 * przy wykorzystaniu wszelakich interfejsów graficznych implementujących odpowiednie interfejsy.
 * 
 * @author TheDamianAbel <damian.abel.serwin@gmail.com>
 */
public class Telnet implements TelnetObservable {
    /**
     * Tablica obserwatorów klasy telnet.
     */
    private final ArrayList<TelnetObserver> observers = new ArrayList<>();

    /**
     * Bufor połączenia z którego czytamy i do którego piszemy.
     */
    private final ConnectionBuffer buffer;

    /**
     * Konstruktor klasy Telnet. <br>
     * Pobiera za pomocą statycznej klasy ConnectionGiver odpowiedni bufor zapisu i odczytu.
     * @param hostname Adres ip serwera.
     * @param port Port na który jest wykonywane połączenie.
     * @throws UnknownHostException
     * @throws IOException 
     */
    public Telnet(String hostname, int port) throws UnknownHostException, IOException {
        this.buffer = ConnectionGiver.returnBuffer(port, hostname);
    }

    /**
     * Handshake Telnetu. <p>
     * Tutaj zawiązywane jest początkowe połącznie, ustalane są wszelkie parametry połączenia.
     * Metoda jest konieczna do wywołania przed metodą {@link telnet.Telnet#read() }.
     * @throws IOException 
     */
    public void handshake() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Commands.IAC);
        list.add(Commands.DO);
        list.add(Options.SUPPRESS_GO_AHEAD);
        list.add(Commands.IAC);
        list.add(Commands.ARE_YOU_THERE);
        buffer.write(list);
        Option option = Option.getInstance(Options.SUPPRESS_GO_AHEAD);
        option.setDo(true);

        list.clear();
        option = Option.getInstance(Options.ECHO);
        while (!option.isNegotiated()) {
            list = buffer.read(1);
            try {
                list.get(0);
            } catch (IndexOutOfBoundsException ex) {
                MyLogger.log(ex.toString());
            }
            if (list.get(0) == Commands.IAC) {
                CommandProcess(list);
            } else {
                read();
            }
        }
    }

    /**
     * Pisanie do serwera. Wysłanie tekstu do terminala serwera.
     * @param text
     * Tekst do wpisany do terminala.
     * @throws IOException 
     */
    public void write(String text) throws IOException {
        Option option = Option.getInstance(Options.ECHO);
        if (option.isNegotiated()) {
            char[] textChar = text.toCharArray();
            ArrayList<Integer> list = new ArrayList<>();
            for (char c : textChar) {
                list.add((int) c);
            }
            list.add(13);
            buffer.write(list);
            //read();
            //Sciaganie odpowiedzi, bo nie wspieramy NVT Terminal
            list = buffer.read(list.size() + 1);
            StringBuilder textCheck = new StringBuilder();
            for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
                int i = it.next();
                textCheck.append((char) i);
            }
            MyLogger.log("Check: " + textCheck.toString());
        } else {
            MyLogger.log("Error, error, write and not echo negotiated.");
        }
    }

    /**
     * Odczytywanie informacji z terminala serwera i wywołanie wyświetlenia jej na odpowiedni Widok aplikacji.
     * @throws IOException 
     */
    public void read() throws IOException {
        Option option = Option.getInstance(Options.ECHO);
        ArrayList<Integer> list = buffer.read();
        StringBuilder text = new StringBuilder();
        for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
            int i = it.next();
            text.append((char) i);
        }
        MyLogger.log("Message: " + text.toString());
        notifyTelnet(text.toString());
    }

    /**
     * Wysłanie pojedyńczej komendy do serwera Telnet.
     * @param command
     * Kod liczbowy komendy.
     * @throws IOException 
     */
    public void sendCommand(int command) throws IOException{
        ArrayList<Integer> list = new ArrayList<>();
        for (int c : Commands.COMMANDS) {
            if (c == command) {
                list.add(Commands.IAC);
                list.add(command);
                buffer.write(list);
            }
        }
    }

    /**
     * Przetwarzanie komendy i wykonanie specjalnej odpowiedzi.
     * @param list
     * Odebrane dane do przetworzenia w postaci listy.
     * @throws IOException 
     */
    private void CommandProcess(ArrayList<Integer> list) throws IOException {
        int iterator = 0;
        int code = 0;
        boolean implemented = false;
        int tmp = 0;
        ArrayList<Integer> write = new ArrayList<>();

        list.addAll(buffer.read(1));
        iterator++;
        int one = list.get(iterator);
        switch (one) {
            case Commands.DO:
                doCommand(list, iterator, tmp, code, implemented, write);
                break;

            case Commands.WILL:
                willCommand(list, iterator, tmp, code, implemented, write);
                break;

            case Commands.SB:
                sbCommand(list, iterator, tmp, code, implemented);
                break;
            case Commands.ARE_YOU_THERE:
                write("Yes, I am here.");
                break;
        }
    }

    /**
     * Sprawdzenie czy komenda jest zaimplementowana.
     * @param code
     * Kod liczbowy komendy.
     * @return 
     */
    private boolean isImplemented(int code) {
        boolean implemented = false;
        for (int i : Options.IMPLEMENTED) {
            if (i == code) {
                implemented = true; //option is implemented
            }
        }
        return implemented;
    }

    /**
     * Zarejestrowanie obserwatora.
     * @param o 
     * Obserwator
     */
    @Override
    public void registerObserver(TelnetObserver o) {
        observers.add(o);
    }

    /**
     * Usunięcie obserwatora.
     * @param o 
     * Obserwator
     */
    @Override
    public void removeObserver(TelnetObserver o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    /**
     * Powiadomienie Kontrolera o informacji do wyświetlenia na Modelu. <br>
     * Przekazanie tej informacji w formie argumentu funkcji.
     * @param text 
     */
    @Override
    public void notifyTelnet(String text) {
        for (TelnetObserver o : observers) {
            o.updateTelnet(text);
        }
    }
    
    /**
     * Wykonanie instrukcji dla komendy, przy odebraniu instrukcji DO
     * @param list
     * Lista danych.
     * @param iterator
     * Iterator
     * @param tmp
     * @param code
     * @param implemented
     * @param write
     * @throws IOException 
     */
    private void doCommand(ArrayList<Integer> list, int iterator, int tmp, int code, boolean implemented, ArrayList<Integer> write) throws IOException {
        list.addAll(buffer.read(1));
        iterator++;
        tmp = list.get(iterator);
        code = tmp;
        implemented = isImplemented(code);
        if (implemented) {
            Option option = Option.getInstance(code);
            option.doo(buffer);
        } else {
            write.add(Commands.IAC);
            write.add(Commands.WONT);
            write.add(code);
            buffer.write(write);
        }
    }

    /**
     * Wykonanie instrukcji dla komendy, przy odebraniu instrukcji WILL
     * @param list
     * @param iterator
     * @param tmp
     * @param code
     * @param implemented
     * @param write
     * @throws IOException 
     */
    private void willCommand(ArrayList<Integer> list, int iterator, int tmp, int code, boolean implemented, ArrayList write) throws IOException {
        list.addAll(buffer.read(1));
        iterator++;
        tmp = list.get(iterator);
        code = tmp;
        implemented = isImplemented(code);
        if (implemented) {
            Option option = Option.getInstance(code);
            option.will(buffer);
        } else {
            write.add(Commands.IAC);
            write.add(Commands.DONT);
            write.add(code);
            buffer.write(write);
        }
    }

    /**
     * Wykonanie instrukcji dla komendy, przy odebraniu instrukcji SB
     * @param list
     * @param iterator
     * @param tmp
     * @param code
     * @param implemented
     * @throws IOException 
     */
    private void sbCommand(ArrayList<Integer> list, int iterator, int tmp, int code, boolean implemented) throws IOException {
        list.addAll(buffer.read(1));
        iterator++;
        tmp = list.get(iterator);
        code = tmp;
        implemented = isImplemented(code);
        if (implemented) {
            list.addAll(buffer.read(1));
            iterator++;
            tmp = list.get(iterator);
            if (tmp == 0) { //IS
                StringBuilder setting = null;
                list.addAll(buffer.read(1));
                while (true) {
                    list.addAll(buffer.read(1));
                    iterator++;
                    tmp = list.get(iterator);
                    if (tmp == Commands.IAC) {
                        list.addAll(buffer.read(1));
                        iterator++;
                        int next = list.get(iterator);
                        if (next == Commands.SE) {
                            break;
                        }
                    }
                    setting.append(tmp);
                }
                String settingText;
                settingText = setting.toString();
                Option option = Option.getInstance(code);
                option.setSetting(settingText);
                option.setNegotiated(true);
            } else if (tmp == 1) {//SEND
                Option option = Option.getInstance(code);
                option.sentSetting(buffer);
            }
        } else {
            //nothing too
        }
    }

}
