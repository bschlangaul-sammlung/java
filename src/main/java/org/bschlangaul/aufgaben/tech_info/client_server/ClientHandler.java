package org.bschlangaul.aufgaben.tech_info.client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
@SuppressWarnings("unused")
public class ClientHandler {

  private Scanner in;
  private boolean istWach;
  private PrintWriter out;
  private Socket socket;

  /**
   * Im Konstruktor sollen alle Attribute des ClientHandlers initialisiert werden:
   *
   * Dem Referenzattribut socket wird der Übergabeparameter zugewiesen. istWach
   * ist true, da Polly jetzt Kekse haben will in und out wird genauso wie beim
   * Client initialisiert:
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
   * <li>Solange Polly wach ist, soll der Client mit ihr kommunizieren können ->
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
  private void clientTrennen() {

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
   * @param msg
   */
  private void pollysReaktion(String msg) {

  }

  private void sendeNachricht(String msg) {

  }

}
