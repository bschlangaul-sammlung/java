package org.bschlangaul.aufgaben.tech_info.vorlesung;

public class BeispielprogrammMitThreads {

  public static void main(String[] args) {
    MeinThread[] t = new MeinThread[10];
    for (int i = 0; i < t.length; i++) {
      t[i] = new MeinThread(i); // Daten, die der Thread für die Berechnungen benötigt, müssen übergeben werden.
    }
    for (int i = 0; i < t.length; i++) {
      t[i].start(); // Die Threads werden gestartet und parallel ausgeführt.
    }
    for (int i = 0; i < t.length; i++) {
      try {
        t[i].join(); // Der Main-Thread wartet bis die gestarteten Threads fertig gearbeitet haben.
                     // join() darf nie derselben Schleife wie start() stehen!
      } catch (InterruptedException e) { // Notwendig damit das Programm nicht "abstürzt", falls ein Thread
                                         // interrupted/unterbrochen wird.
        e.printStackTrace();
      }
    }
    // Ende des Programms
  }
}

class MeinThread extends Thread {
  int nummer;

  public MeinThread(int nummer) { // Die übergebenen Daten müssen in Attributen "gespeichert" werden
    this.nummer = nummer;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Ich bin Thread " + nummer);
    }
  }

}
