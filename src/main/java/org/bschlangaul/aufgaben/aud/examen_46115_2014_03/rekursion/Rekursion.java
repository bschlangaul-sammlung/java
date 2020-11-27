package org.bschlangaul.aufgaben.aud.examen_46115_2014_03.rekursion;

/**
 *
 */
public class Rekursion {

  /**
   * Berechnet rekursiv den Binominalkoeffizienten „n über k“. Dabei muss gelten:
   * n &#x3E;= 0, k &#x3E;= 0 und n &#x3E;= k.
   *
   * @param n Ganzzahl n
   * @param k Ganzzahl k
   *
   * @return Eine Ganzzahl.
   */
  public static int binRek(int n, int k) {
    if (k == 0 || k == n) {
      return 1;
    } else {
      return binRek(n - 1, k - 1) + binRek(n - 1, k);
    }
  }

  /**
   * Berechnet iterativ den Binominalkoeffizienten „n über k“. Dabei muss gelten:
   * n &#x3E;= 0, k &#x3E;= 0 und n &#x3E;= k.
   *
   * @param n Ganzzahl n
   * @param k Ganzzahl k
   *
   * @return Eine Ganzzahl.
   */
  public static int binIt(int n, int k) {
    // Das Ergebnis wird als Kommazahl deklariert, da nicht alle
    // Zwischenergebnisse ganze Zahlen sind.
    double ergebnis = 1;
    while (k > 0) {
      ergebnis = ergebnis * n / k;
      n--;
      k--;
    }
    // Vor dem Zurückgeben kann das Ergebnis nun in eine ganze Zahl
    // umgewandelt werden.
    return (int) ergebnis;
  }
}
