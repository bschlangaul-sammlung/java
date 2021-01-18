package org.bschlangaul.aufgaben.tech_info.client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Der {@link Server} stellt einen Papagei mit dem Namen Polly dar. Polly ist
 * sehr stur und will immer nur Kekse essen, ganz egal was man ihr auch sagt.
 * Aber wenigstens geht sie auf Kommando („nerv nicht“) schlafen.
 */
public class Server {

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
