package org.bschlangaul.aufgaben.tech_info.vorlesung;

public class BeispielprogrammMitThreads {

  public static void main(String[] args) {
    MeinThread[] t = new MeinThread[10];
    for (int i = 0; i < t.length; i++) {
      // Daten, die der Thread für die Berechnungen benötigt, müssen übergeben werden.
      t[i] = new MeinThread(i);
    }
    for (int i = 0; i < t.length; i++) {
      // Die Threads werden gestartet und parallel ausgeführt.
      t[i].start();
    }
    for (int i = 0; i < t.length; i++) {
      try {
        // Der Main-Thread wartet bis die gestarteten Threads fertig gearbeitet haben.
        // join() darf nie derselben Schleife wie start() stehen!
        t[i].join();
        // Notwendig damit das Programm nicht "abstürzt", falls ein Thread
        // interrupted/unterbrochen wird.
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    // Ende des Programms
  }
}

class MeinThread extends Thread {
  int nummer;

  public MeinThread(int nummer) {
    // Die übergebenen Daten müssen in Attributen "gespeichert" werden
    this.nummer = nummer;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Ich bin Thread " + nummer);
    }
  }

}
