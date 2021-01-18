package org.bschlangaul.aufgaben.tech_info.client_server;

// https://www.cs.hs-rm.de/~knauf/SWTProjekt2008/sockets/

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
  public static void main(String[] args) {
    try {
      // Warte auf Anfragen auf Port 13000:
      ServerSocket serverSocket = new ServerSocket(13000);

      // Eine einzige Anfrage entgegennehmen:
      Socket clientSocket = serverSocket.accept();

      // Die Rückgabe in einen Ausgabestream schreiben:
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      // Senden eines Newline sorgt dafür, dass der PrintWriter die Ausgabe
      // "abschickt". Alternativ müsste "flush" aufgerufen werden.
      out.println("Serverantwort");
      System.out.println("quit");
      serverSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
