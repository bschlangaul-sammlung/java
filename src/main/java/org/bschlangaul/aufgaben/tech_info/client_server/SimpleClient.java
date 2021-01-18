package org.bschlangaul.aufgaben.tech_info.client_server;

// https://www.cs.hs-rm.de/~knauf/SWTProjekt2008/sockets/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;

public class SimpleClient {
  public static void main(String[] args) {
    try {
      // Verbindung zu Port 13000 auf localhost aufbauen:
      Socket socket = new Socket("localhost", 13000);

      // Eine Zeile lesen:
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String serverResponse = in.readLine();
      System.out.println("Server-Antwort: " + serverResponse);

      // Socket dichtmachen:
      socket.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
