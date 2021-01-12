package org.bschlangaul.aufgaben.tech_info.schreibtischlauf;

import java.util.concurrent.atomic.*;

/**
 * Schreibtischlauf
 *
 * <p>
 * In dieser Aufgabe soll das aufgeführte parallele Programm
 * {@link ThreadSafety} analysiert werden. Ihr könnt davon ausgehen, dass die
 * Programmausführung in der {@code execute}-Methode beginnt und zu Beginn alle
 * Variablen die initial zugewiesenen Werte enthalten. Gib deine Lösung in
 * einer Datei Schreibtischlauf.pdf ab.
 *
 * <ol>
 * <li>Welche Ausgaben sind für v möglich? Begründe deine Antwort.
 * <li>Welche Ausgaben sind für a möglich? Begründe deine Antwort.
 * <li>Welche Ausgaben sind für x möglich? Begründe deine Antwort.
 * <li>Welche Ausgaben sind für y möglich? Begründe deine Antwort.
 * </ol>
 */
public class ThreadSafety {
  volatile int v = 0;
  AtomicInteger a = new AtomicInteger(0);
  int x = 0;
  int y = 0;
  int z = 0;

  volatile boolean b = false;

  class T1 extends Thread {
    public void run() {
      v++;

      if (a.get() < 1) {
        a.incrementAndGet();
      }

      for (int i = 0; i < 2; ++i) {
        synchronized (Object.class) {
          x++;
        }
      }

      z = 40;
      b = true;
    }
  }

  class T2 extends Thread {
    public void run() {
      v++;

      if (a.get() < 1) {
        a.incrementAndGet();
      }

      synchronized (Object.class) {
        for (int i = 0; i < 2; i++) {
          x++;
        }

      }

      while (!b) {
        /* Wait */}
      y = z + 2;

    }
  }

  public void execute() throws Exception {
    final Thread tl = new T1();
    final Thread t2 = new T2();
    tl.start();
    t2.start();
    tl.join();
    t2.join();

    System.out.println("v = " + v);
    System.out.println("a = " + a.get());
    System.out.println("x = " + x);
    System.out.println("y = " + y);

  }

  public static void main(String[] args) throws Exception {
    ThreadSafety ts = new ThreadSafety();
    ts.execute();
  }

}
