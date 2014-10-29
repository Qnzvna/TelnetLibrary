TelnetLibrary
===================
Jest to biblioteka napisana w języku Java służąca do łączenia się z serwerem Telnet.

Implementacja została dokonywana z uwzględnieniem [RFC854](https://tools.ietf.org/html/rfc854) jednakże nie wszystkie rzeczy są zaimplementowane. Wystarcza to jednak do sprawnego wykonania połączenia z serwerem.

Zastosowanie
------------

Najpierw należy stworzyć instancję klasy Telnet. Argumentami jest host oraz port serwera.
`telnet = new Telnet(host, port);`

Następnie należy zarejestrować kontroler jako obserwator w tej klasie.
`telnet.registerObserver(controller);`

Przed wysyłaniem i odbieraniem danych należy nawiązać połączenie z serwerem i ustalić z nim pewne wspólne ustawienia. Służy do tego funkcja:
`telnet.handshake();`

Jest możliwa wcześniejsza zmiana ustawień za pomocą klasy *Settings*. Obecnie jedynym zaimplementowanym ustawieniem, który jest warty zmian to typ terminala.
`Settings.setTerminal_type("xterm");`

Bardzo ważnym aspektem jest implementacja przez kontroler oraz widok odpowienich interfejsów. Dla kontrolera jest to *TelnetObserver* i *ViewObserver*, dla widoku *ViewObservable*.

Po udanym handshak'u i nawiązanym połaczeniu z serwerem możemy wysyłać dane i odbierać je od serwera.
`
telnet.write("some text");
telnet.read();
`

Metoda *read()* powiadamia kontroler o odebranych danych. A napisany przez nas kontroler robi z nimi co chce.

To Do
-----

* rozszerzyć readme
* implementacja dalszych opcji z RFC