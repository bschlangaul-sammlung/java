package org.bschlangaul.aufgaben.tech_info.client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  private InetSocketAddress address;
  private Scanner in;
  private boolean isRunning;
  private PrintWriter out;
  private Socket socket;
  private Scanner stdIn;

  /**
   * Im Konstruktor sollen alle Attribute des Clients initialisiert werden:
   *
   * <ul>
   * <li>stdIn soll Konsoleneingaben einlesen und damit muss der Scanner auf
   * System.in lauschen
   * <li>Die Internetsocketadresse address soll mit dem hostname und dem port
   * erzeugt werden.
   * <li>Anschließend soll die Methode stelleVerbindungHer() aufgerufen werden.
   * (Diese initialisiert dann die Attribute socket, in und out).
   * <li>isRunning ist nach der Verbindungsherstellung true
   * <li>Danach soll die Methode sprichMitPolly() aufgerufen werden. Diese Methode
   * kommuniziert mit dem Server/Polly
   * </ul>
   */
  public Client(String hostname, int port) {
    stdIn = new Scanner(System.in);
    address = new InetSocketAddress(hostname, port);
    stelleVerbindungHer();
    isRunning = true;
    sprichMitPolly();
  }

  /**
   * In der Methode stelleVerbindungHer() werden die restlichen Attribute
   * initialisiert:
   *
   * <ul>
   * <li>Das Attribut socket bekommt einen Standard-Socket zugewiesen
   * (Standardkontruktor).
   * <li>Anschließend soll der Socket sich mit der Internetadresse address
   * verbinden.
   * <li>Der Scanner in und der PrintWriter out werden mit dem InputStream und dem
   * OutputStream des Socket folgendermaßen initialisiert:
   * </ul>
   *
   * <h2>Erklärung zur Initialisierung des In-/Out-Kanals</h2>
   *
   * <p>
   * Jeder Socket besitzt einen InputStream und einen OutputStream. Wenn sich zwei
   * Socket verbunden haben, dann werden die Daten/Nachrichten über den Output des
   * einen Sockets versendet und landet beim beim anderen Socket im Input und auch
   * umgekehrt. (siehe Bild)
   *
   * <p>
   * Der InputStream und OutputStream arbeiten auf Bitebene. Das heißt, es werden
   * beim InputStream Bytes gelesen/empfangen und beim OutputStream Bytes
   * geschrieben/versendet.
   *
   *
   * <p>
   * Die Klassen InputStreamReader und OutputStreamWriter ermöglichen es, dass man
   * die Daten zeichenweise (Buchstaben, Zahlen und Sonderzeichen nach einem
   * spezifizierten Zeichensatz) lesen/schreiben kann.
   *
   * <p>
   * Die Klasse BufferedReader kann einen zeichenbasierten Stream lesen und
   * puffert/speichert die gelesenen Zeichen zwischen, sodass man anschließend
   * z.B. zeilenweise die Daten/Texte lesen kann.
   *
   * <p>
   * Die Klasse BufferedWriter puffert Zeichen(-folgen) (also Text), um
   * effizientes Schreiben in einen zeichenbasierten Outputstream zu ermöglichen.
   *
   * <p>
   * Der Scanner kann die eingelesenen Daten/Texte passend „zerteilen“ und für die
   * Weiterverarbeitung umwandeln (z. B. Text in einer Zeile “5“ (Datentyp String)
   * zu einer Zahl 5 (Datentyp int)). (Stichwort: Parser/Parsing)
   *
   * <p>
   * Die Klasse PrintWriter bietet die print(...)- bzw. println(...)-Methoden für
   * primitive Datentypen und Strings an. Diese Methoden nutzen dabei automatisch
   * die für das Betriebssystem passenden Zeilenumbruchszeichen (\n vs. \r\n).
   * Zudem wirft die Klasse PrintWriter keine I/O-Exceptions (Input/Output).
   *
   */
  private void stelleVerbindungHer() {
    socket = new Socket();
    try {
      socket.connect(address);
      in = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Zuletzt muss noch die Kommunikation vom Client mit Polly fertiggestellt
   * werden. Die Methode sprichMitPolly() wird nach der Verbindungsherstellung mit
   * dem Server im Konstruktor aufgerufen.
   *
   * <p>
   * Der Client soll ermöglichen, dass du über die Konsole direkt mit Polly
   * sprechen kannst. Also soll der Client die Antworten von Polly auf der Konsole
   * ausgeben, deine Eingaben auf der Konsole einlesen und an den Server
   * verschicken und anschließend wieder die Antwort von Polly wieder empfangen
   * und ausgeben.
   *
   * <p>
   * Hierfür werden zwei lokale Variablen consolenInput und pollysAntwort vom
   * Datentyp String benötigt
   *
   * <p>
   * Der Ablauf der Methode ist visuell auf der nächsten Folie dargestellt
   *
   * <ul>
   * <li>Falls man beim InputStream eine neue Zeile lesen kann, dann
   * <ul>
   * <li>Zeile einlesen und der Variablen pollysAntwort zuweisen
   * <li>Pollys Antwort ausgeben in der Form: [Server] Pollys Antwort:
   * “+pollysAntwort
   * </ul>
   *
   * <li>Ausgabe auf Konsole in der Form: [Client] Was willst du Polly sagen?
   *
   * <li>Falls man eine neue Zeile von der Konsole einlesen kann (hasNextLine()
   * auf stdIn), dann
   *
   * <ul>
   * <li>Dann soll die Variable consolenInput (für die Nachricht an Polly) mit dem
   * Input des Benutzers eingelesen werden (nextLine() auf stdIn)
   * <li>Die gerade eingelesene Nachricht soll versendet werden
   * </ul>
   *
   * <li>Falls man beim InputStream über den Scanner in eine neue Zeile einlesen
   * kann, dann
   *
   * <ul>
   * <li>Dann soll diese Antwort von Polly in der Variable pollysAntwort
   * gespeichert werden
   * <li>Pollys Antwort ausgeben in der Form: „[Client] Pollys Antwort:
   * “+pollysAntwort
   * <li>Falls Pollys Antwort „Okay Polly geht schlafen!“ entspricht -> isRunning
   * auf false
   * </ul>
   *
   * </ul>
   */
  public void sprichMitPolly() {
    String consolenInput;
    String pollysAntwort;
    if (in.hasNextLine()) {
      pollysAntwort = in.nextLine();
      System.out.println("[Server] Pollys Antwort: " + pollysAntwort);
    }

    while (isRunning) {
      System.out.println("[Client] Was willst du Polly sagen?");
      if (stdIn.hasNextLine()) {
        consolenInput = stdIn.nextLine();
        sendeNachricht(consolenInput);
      }

      if (in.hasNextLine()) {
        pollysAntwort = in.nextLine();
        System.out.println("[Client] Pollys Antwort: " + pollysAntwort);
        if (pollysAntwort.equalsIgnoreCase("Okay Polly geht schlafen!")) {
          isRunning = false;
        }
      }
    }

    serverTrennen();
  }

  /**
   * Implementiere in beiden Klassen {@link Client} und {@link ClientHandler} die
   * Methode sendeNachricht(String msg):
   *
   * <p>
   * Die Nachricht msg soll mit der Methode println(...) vom PrintWriter in den
   * Puffer geschrieben werden.
   *
   * <p>
   * Anschließend soll mit der Methode flush() vom PrintWriter die Daten in den
   * OutputStream geschrieben und damit „abgeschickt“ werden.
   *
   * @param msg
   */
  private void sendeNachricht(String msg) {
    out.println(msg);
    out.flush();
  }

  /**
   * Implementiere in beiden Klassen Client und ClientHandler die Methoden
   * serverTrennen() und clientTrennen(). Beide Methoden erledigen dieselben
   * Schritte:
   *
   * <ul>
   * <li>Zunächst sollen die beiden Methoden den Scanner und den PrintWriter
   * schließen
   * <li>Anschließend soll der Socket geschlossen werden
   * </ul>
   * <p>
   * So werden zunächst die In- und Out-Kanäle und anschließend die Verbindung
   * selbst zwischen Client und ClientHandler geschlossen.
   */
  private void serverTrennen() {
    in.close();
    out.close();
    try {
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * In der main-Methode soll ein neuer {@link Client} mit der IP-Adresse des
   * gerade programmierten Servers und mit dem gewählten Port des Servers (z.B.
   * 7569) erstellt werden.
   *
   * Falls der Server auf demselben Rechner läuft wie der Client, kann die
   * IP-Adresse „127.0.0.1“ oder „localhost“ gewählt werden.
   */
  public static void main(String[] args) {
    new Client("localhost", Server.DEFAULT_PORT);
  }

}
