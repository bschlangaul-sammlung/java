package org.bschlangaul.aufgaben.tech_info.schreibtischlauf;

import java.util.concurrent.atomic.*;

/**
 * Schreibtischlauf
 *
 * <p>
 * In dieser Aufgabe soll das aufgeführte parallele Programm
 * {@link ThreadSafety} analysiert werden. Ihr könnt davon ausgehen, dass die
 * Programmausführung in der {@code execute}-Methode beginnt und zu Beginn alle
 * Variablen die initial zugewiesenen Werte enthalten. Gib deine Lösung in einer
 * Datei Schreibtischlauf.pdf ab.
 *
 * <ol>
 * <li>Welche Ausgaben sind für v möglich? Begründe deine Antwort:
 *
 * <p>
 * Meistens 2, in seltenen Fällen 1. {@link v} ist zwar mit {@code volatile}
 * versehen worden, d. h. Änderungen werden auch für andere Threads sichtbar,
 * jedoch kann es vorkommen, dass die beiden Threads gleichzeitig zu der Zeile
 * {@code v++;} kommen und beide von 0 aus hochzählen und zweimal 1 setzen.
 *
 * <li>Welche Ausgaben sind für a möglich? Begründe deine Antwort:
 *
 * <p>
 * Für das Attribut {@link a} ist die Ausgabe 1 oder 2 möglich. {@link a} ist
 * eine Instanze von {@code AtomicInteger}, d. h. die Klassen {@link T1} und
 * {@link T2} können nicht gleichzeitig {@link a} hochzählen. Eine Klasse
 * erhält den Vorrang. Hat {@link a} den Wert 1 erreicht, verhindert die
 * Bedingung {@code a.get() < 1}, dass weiter hochgezählt wird. Es kann jedoch
 * sein, dass die beiden Threads gleichzeitig das Innere der if-Bedingung (jetzt
 * hätte ich beinahe Schleife geschrieben ...) erreichen, und dann nacheinander
 * auf 2 hochzählen.
 *
 * <li>Welche Ausgaben sind für x möglich? Begründe deine Antwort.
 *
 * <p>
 * Für das Attribut {@link x} ist nur die Ausgabe 4 möglich. Die beiden Klassen
 * {@link T1} und {@link T2} enthalten eine Wiederholung mit fester Anzahl, die
 * {@link x} zwei mal hochzählen. Das Hochzählen von {@link x} ist in beiden
 * Klassen in einem mit synchronized geschützen Codebereich eingebunden, sodass
 * der Hochzählprozess exklusiv von einem Thread abgearbeitet werden kann, der
 * andere Thread aufgrund des Locks warten muss. Es ist unerheblich, ob die
 * Wiederholung mit fester Anzahl den synchronized-Codeblock nur umschließt oder
 * in einen synchronized-Block eingebunden ist. Entscheidend ist, dass sich die
 * Zeile {@code x++;} im synchronized-Codeblock befindet.
 *
 * <li>Welche Ausgaben sind für y möglich? Begründe deine Antwort.
 *
 * <p>
 * Für das Attribut {@code y} ist nur die Ausgabe 42 möglich. Die run-Methode
 * der Klasse {@link T1} setzt das Attribut {@link z} auf den Wert 40. Aufgrund
 * der „Happens Before Guarantee“
 * (http://tutorials.jenkov.com/java-concurrency/volatile.html) von Java wirkt
 * sich das {@code volatile} vor dem Attribut {@code b} auch auf das Attribut
 * {@link z} aus. Das Attribut {@code z} wird erst nachdem {@code b} auf true
 * gesetzt wird, aktualisiert - solange hängt die run-Methode der Klasse
 * {@link T2} in dem while-Loop fest. Aufgrund des volatile-Schlüsselworts vor
 * {@link b} erfährt {@link T2} von der Wertänderung und verlässt die Schleife
 * und wegen der „Happens Before Guarantee“ ist {@link z} auch volatile. In der
 * Zeile {@code y = x + 2;} wird {@link y} schließlich auf 42 gesetzt
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
    final Thread t1 = new T1();
    final Thread t2 = new T2();
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    // System.out.println("v = " + v);
    // System.out.println("a = " + a.get());
    // System.out.println("x = " + x);
    // System.out.println("y = " + y);
    if (v != 2)
      System.out.println("v = " + v); // 1 oder 2
    // if (a.get() != 1) System.out.println("a = " + a.get()); // 1 oder 2 (16x a )
    // if (x != 4) System.out.println("x = " + x);
    // if (y != 42) System.out.println("y = " + y); // immer 42
  }

  public static void main(String[] args) throws Exception {
    ThreadSafety ts = new ThreadSafety();
    ts.execute();
    for (int i = 0; i < 1000000; i++) {
      ts = new ThreadSafety();
      ts.execute();
    }
  }

}
