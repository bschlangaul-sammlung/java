package org.bschlangaul.aufgaben.tech_info.client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("unused")
public class Server {

  public static int DEFAULT_PORT = 7569;

  private boolean isRunning;

  private ServerSocket serverSocket;

  /**
   * Im Konstruktor müssen alle Attribute initialisiert werden ({@link isRunning}
   * auf true) und am Ende die Methode listen() aufgerufen werden.
   *
   * @param port
   */
  public Server(int port) {
    isRunning = true;
    try {
      serverSocket = new ServerSocket();
    } catch (IOException e) {
      e.printStackTrace();
    }
    listen();
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
  }

  /**
   * In der Methode {@link serverBeenden} soll {@link isRunning} auf false gesetzt
   * werden.
   */
  public void serverBeenden() {
    isRunning = false;
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
   * {@link client} gespeichert wurde, soll ein neuer {@link ClientHandler} mit
   * diesem gespeicherten Socket client erzeugt werden.
   *
   * <p>
   * Anschließend soll der {@link ClientHandler} mithilfe eines Threads parallel
   * gestartet werden.
   */
  private void listen() {
    while (isRunning) {
      try {
        if (serverSocket.isBound()) {
          Socket client = serverSocket.accept();
          ClientHandler clientHandler = new ClientHandler(client);
          new Thread(clientHandler).start();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
