package org.bschlangaul.aufgaben.tech_info.vorlesung;

import java.util.concurrent.*;

public class BeispielprogrammMitExecutorservice {

  public static void main(String[] args) {
    // neuer ExecutorService wird erstellt und zugewiesen
    ExecutorService e = Executors.newFixedThreadPool(10);

    // Hier können nun beliebig viele Arbeitspakete übergeben werden, die von den 10
    // Thread abgearbeitet werden.
    // Die Zuordnung - welcher Thread welches Arbeitspaket übernimmt - regelt der
    // ExecutorService selbst.
    for (int i = 0; i < 100; i++) {
      e.execute(new MeinRunnableExecutor(i)); // Ein Arbeitspaket wird dem ExecutorService zur Ausführung übergeben.
    }

    e.shutdown();
    // Es werden keine weiteren Tasks mehr angenommen. Alle bereits übergebenen
    // Runnable-Objekte werden
    // bis zu deren Ende bearbeitet (sowohl die bereits begonnenen als auch die noch
    // wartenden).

    // e.shutdownNow();
    // Es werden keine weiteren Tasks mehr angenommen. Laufende Tasks erhalten einen
    // interrupt().
    // (Die Unterbrechung kann abgefangen werden, sodass die run()-Methode weiter
    // arbeitet.)
    // Noch nicht begonnene Tasks werden unbearbeitet als return-Wert zurückgegeben.

    try {
      e.awaitTermination(60, TimeUnit.SECONDS);
      // Der Main-Thread wartet bis alle Threads fertig gearbeitet haben.
      // Kehrt zurück, wenn alle Tasks nach shutdown abgearbeitet sind oder die Zeit
      // (timeout unit) abgelaufen ist
      // oder wenn der aktuelle Ablauffaden per Interrupt unterbrochen wurde.
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
    // e.isTerminated();
    // true, genau dann wenn der Thread-Pool nichts mehr zu tun hat, d.h. alle
    // übergebenen Tasks bearbeitet sind.
  }
}

class MeinRunnableExecutor implements Runnable {
  int nummer;

  public MeinRunnableExecutor(int nummer) {
    this.nummer = nummer;
  }

  public void run() {
    for (int i = 0; i < 1; i++) {
      System.out.println("Ich bin Arbeitspaket " + nummer);
    }
  }
}
