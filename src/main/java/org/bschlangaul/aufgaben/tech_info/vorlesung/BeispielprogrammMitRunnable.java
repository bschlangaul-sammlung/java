package org.bschlangaul.aufgaben.tech_info.vorlesung;

public class BeispielprogrammMitRunnable {

  public static void main(String[] args) {
    // Standard-Thread-Klasse von Java
    Thread[] t = new Thread[10];
    for (int i = 0; i < t.length; i++) {
      // Arbeitspaket wird übergeben
      t[i] = new Thread(new MeinRunnable(i));
    }
    for (int i = 0; i < t.length; i++) {
      t[i].start();
    }
    for (int i = 0; i < t.length; i++) {
      try {
        t[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class MeinRunnable implements Runnable {
  int nummer;

  public MeinRunnable(int nummer) {
    this.nummer = nummer;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Ich bin Thread " + nummer);
    }
  }
}
