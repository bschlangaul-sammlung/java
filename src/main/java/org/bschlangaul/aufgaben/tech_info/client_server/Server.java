package org.bschlangaul.aufgaben.tech_info.client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Der {@link Server} stellt einen Papagei mit dem Namen Polly dar. Polly ist
 * sehr stur und will immer nur Kekse essen, ganz egal was man ihr auch sagt.
 * Aber wenigstens geht sie auf Kommando („nerv nicht“) schlafen.
 */
public class Server {

  /**
   * In der Klasse Server müssen nun die angefragten Verbindungen von den Clients
   * angenommen und abgearbeitet werden.
   *
   * <p>
   * Die Abarbeitung der Clientanfragen muss parallelisiert erfolgen, da sonst
   * immer nur ein Client gleichzeitig mit dem Server kommunizieren kann.
   *
   * <p>
   * Für die Abarbeitung der Anfragen ist die Klasse ClientHandler zuständig, die
   * als „Hilfsklasse“ in der Klasse vom Server angelegt werden kann.
   * ClientHandler implementiert das Interface Runnable. Implementiere das
   * Grundgerüst!
   */
  public class ClientHandler implements Runnable {

    private Scanner in;
    private boolean istWach;
    private PrintWriter out;
    private Socket socket;

    /**
     * Im Konstruktor sollen alle Attribute des ClientHandlers initialisiert werden:
     *
     * Dem Referenzattribut {@code socket} wird der Übergabeparameter zugewiesen.
     * {@code istWach} ist {@code true}, da Polly jetzt Kekse haben will. {@code in}
     * und {@code out} wird genauso wie beim Client initialisiert:
     *
     * @param socket Der Client-Socket.
     */
    public ClientHandler(Socket socket) {
      this.socket = socket;
      istWach = true;
      try {
        in = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    /**
     * Der Dienst des Servers (also die Aufgabe des ClientHandlers) ist es einen
     * nervigen Papagei Polly darzustellen, der immer nur Kekse haben möchte.
     *
     * <p>
     * Durch das Starten des Threads in der listen()-Methode, wird die run()-Methode
     * des ClientHandlers ausgeführt (und Polly ist wach):
     *
     * <ul>
     * <li>Sende als Erstes eine Nachricht an den Client: "Polly ist wach und will
     * einen Keks“
     * <li>Definiere dir für die Antwort an den Client eine lokale Variable antwort
     * <li>Solange Polly wach ist, soll der Client mit ihr kommunizieren können:
     * Dauerschleife (Inhalt der Dauerschleife siehe nächste Folie)
     * <li>Nach der Schleife soll die Verbindung zum Client getrennt werden.
     * </ul>
     *
     * In der Dauerschleife der run()-Methode soll folgendes implementiert werden:
     *
     * <ul>
     * <li>Falls der Scanner eine neue Zeile lesen kann – hasNextLine()
     * <li>Dann soll die nächste Zeile eingelesen werden – nextLine() – und der
     * Methode pollysReaktion(...) übergeben werden
     * <li>Die Rückgabe der Reaktionsmethode soll in der lokalen Variable antwort
     * gespeichert werden.
     * <li>Anschließend soll die antwort versendet werden
     * </ul>
     */
    public void run() {
      sendeNachricht("Polly ist wach und will einen Keks");
      String antwort;
      while (istWach) {
        if (in.hasNextLine()) {
          antwort = pollysReaktion(in.nextLine());
          sendeNachricht(antwort);
        }
      }
      clientTrennen();
    }

    /**
     * Implementiere in beiden Klassen {@link Client} und {@link ClientHandler} die
     * Methoden serverTrennen() und clientTrennen(). Beide Methoden erledigen
     * dieselben Schritte:
     *
     * <ul>
     * <li>Zunächst sollen die beiden Methoden den {@link Scanner} und den
     * {@link PrintWriter} schließen
     * <li>Anschließend soll der {@link Socket} geschlossen werden
     * </ul>
     *
     * <p>
     * So werden zunächst die In- und Out-Kanäle und anschließend die Verbindung
     * selbst zwischen Client und ClientHandler geschlossen.
     */
    private void clientTrennen() {
      in.close();
      out.close();
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    /**
     * Polly ist sehr stur und will immer nur Kekse essen, ganz egal was man ihr
     * auch sagt. Aber wenigstens geht sie auf Kommando („nerv nicht“) schlafen.
     *
     * <p>
     * Falls die übergebene Nachricht an die Methode pollysReaktion() „nerv nicht“
     * ist:
     * <ul>
     * <li>Dann soll Polly nicht mehr wach sein und als Antwort "Okay Polly geht
     * schlafen!“ zurückgeben.
     * <li>Ansonsten antwortet Polly "Polly will noch einen Keks!“
     * </ul>
     *
     * <p>
     * Tipp: Nutze hierfür die Methode equalsIgnoreCase(...), um die Groß- und
     * Kleinschreibung nicht zu beachten!
     *
     * @param msg Inhalt der Nachricht, auf die Polly reagieren soll.
     *
     * @return Inhalt der Nachricht, mit der Polly reagiert.
     */
    private String pollysReaktion(String msg) {
      if (msg.equalsIgnoreCase("nerv nicht")) {
        istWach = false;
        return "Okay Polly geht schlafen!";
      }
      return "Polly will noch einen Keks!";
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
     * @param msg Inhalt der Nachricht, die geschickt werden soll.
     */
    private void sendeNachricht(String msg) {
      out.println(msg);
      out.flush();
    }

  }

  public static int DEFAULT_PORT = 7569;

  private boolean isRunning;

  private ServerSocket serverSocket;

  /**
   * Im Konstruktor müssen alle Attribute initialisiert werden ({@link isRunning}
   * auf true) und am Ende die Methode listen() aufgerufen werden.
   *
   * @param port Der Port, auf dem der Server lauschen soll.
   */
  public Server(int port) {
    isRunning = true;
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
    listen();
  }

  /**
   * Solange der Server läuft ({@link isRunning}) soll der Server jede eingehende
   * Verbindung annehmen:
   *
   * <ul>
   * <li>Der Server soll in der Methode {@link listen} dauerhaft aktiv sein:
   * Dauerschleife mittels {@link isRunning}
   *
   * <li>Falls beim serverSocket eine Verbindunganfrage gestellt wird, soll diese
   * akzeptiert werden und in einer lokalen Variable „client“ vom Datentyp Socket
   * gespeichert werden.
   *
   * <li>Falls der Server nicht mehr laufen soll, muss nach der Dauerschleife der
   * serverSocket geschlossen werden.
   * </ul>
   *
   * <p>
   * Nachdem die Verbindungsanfrage akzeptiert und in der lokalen Variable
   * {@code client} gespeichert wurde, soll ein neuer {@link ClientHandler} mit
   * diesem gespeicherten Socket client erzeugt werden.
   *
   * <p>
   * Anschließend soll der {@link ClientHandler} mithilfe eines Threads parallel
   * gestartet werden.
   */
  private void listen() {
    while (isRunning) {
      try {
        Socket client = serverSocket.accept();
        ClientHandler clientHandler = new ClientHandler(client);
        new Thread(clientHandler).start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    serverTrennen();
  }

  private void serverTrennen() {
    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * In der Methode {@link serverBeenden} soll {@link isRunning} auf false gesetzt
   * werden.
   */
  public void serverBeenden() {
    isRunning = false;
  }

  /**
   * In der main-Methode soll ein neuer Server mit einem beliebigen Port größer
   * als 1024 (z. B. 7569) erstellt werden
   *
   * @param args Kommandozeilen-Argumenten, die hier nicht genutzt werden.
   */
  public static void main(String[] args) {
    new Server(Server.DEFAULT_PORT);
  }
}
