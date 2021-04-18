package org.bschlangaul.examen.examen_46115.jahr_2017.herbst;

/**
 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 2, 3, 5, 7, 11, 13, 17, 19, 23, 29
 */
public class PrimzahlDP {

  static long pKR(int n) {
    long p = 2;
    if (n >= 2) {
      p = pKR(n - 1); // beginne die Suche bei der vorhergehenden Primzahl
      int i = 0;
      do {
        p++; // pruefe, ob die jeweils naechste Zahl prim ist, d.h. ...
        for (i = 1; i < n && p % pKR(i) != 0; i++) {
        } // pruefe, ob unter den kleineren Primzahlen ein Teiler ist
      } while (i != n); // ... bis nur noch 1 und p Teiler von p sind
    }

    return p;
  }

  private long pLR(int n, long[] ps) {
    ps[1] = 2;
    return ps[0];
  }

  static void debug(int n) {
    System.out.println(String.format("%d. Primzahl: %d", n, pKR(n)));
  }

  public static void main(String[] args) {
    for (int i = 1; i <= 10; i++) {
      debug(i);
    }
  }
}
