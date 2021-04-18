package org.bschlangaul.examen.examen_46115.jahr_2017.herbst;

/**
 * Berechne die n-te Primzahl.
 *
 * <ul>
 * <li>1. Primzahl: 2
 * <li>2. Primzahl: 3
 * <li>3. Primzahl: 5
 * <li>4. Primzahl: 7
 * <li>5. Primzahl: 11
 * <li>6. Primzahl: 13
 * <li>7. Primzahl: 17
 * <li>8. Primzahl: 19
 * <li>9. Primzahl: 23
 * <li>10. Primzahl: 29
 * </ul>
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
