# TelnetLibrary
Jest to biblioteka napisana w języku Java służąca do łączenia się z serwerem Telnet.

Implementacja została dokonywana z uwzględnieniem [RFC854](https://tools.ietf.org/html/rfc854) jednakże nie wszystko zostało zaimplementowane. Wystarcza to jednak do sprawnego wykonania połączenia z serwerem.

## Użycie

Najpierw należy stworzyć instancję klasy Telnet. Argumentami jest host oraz port serwera.
`telnet = new Telnet(host, port);`

Następnie należy zarejestrować kontroler jako obserwator w tej klasie.
`telnet.registerObserver(controller);`

Przed wysyłaniem i odbieraniem danych należy nawiązać połączenie z serwerem i ustalić z nim pewne wspólne ustawienia. Służy do tego funkcja:
`telnet.handshake();`

Jest możliwa wcześniejsza zmiana ustawień za pomocą klasy *Settings*. Obecnie jedynym zaimplementowanym ustawieniem, który jest warty zmian to typ terminala.
`Settings.setTerminal_type("xterm");`

Bardzo ważnym aspektem jest implementacja przez kontroler oraz widok odpowienich interfejsów. Dla kontrolera jest to *TelnetObserver* i *ViewObserver*, dla widoku *ViewObservable*.

Po udanym ustaleniu ustawień i nawiązanym połaczeniu z serwerem możemy wysyłać dane i odbierać je od serwera za pomocą dwóch metod.
`telnet.write("some text");`
`telnet.read();`

Metoda *read()* powiadamia kontroler o odebranych danych. A napisany przez nas kontroler robi z nimi co chce.

---

# TelnetLibrary
This is library written in Java. It can connect to telnet server, send and receive some messages.

It is written in accordance with [RFC854](https://tools.ietf.org/html/rfc854) but not all options and telnet commands are implemented. It is enough to connect to most telnet servers.

## Usage

Firstly we must create object Telnet. It has two arguments: host nad port.
`telnet = new Telnet(host, port);`

Secondly we register our controller which must implements *TelnetObserver* and *ViewObserver* interface. View class must implement *ViewObserver*.
`telnet.registerObserver(controller);`
Each time we call `telnet.read();` received data will be passed to our controller.

Before we start send and receive data we shold negotiate telnet settings. We can set our settings using static class *Settings*. Form example to set terminal type we call `Settings.setTerminal_type("xterm");`

After we set our settings we can negotiate it. `telnet.handshake();`

To send data to server we call `telnet.write("foobar");`

