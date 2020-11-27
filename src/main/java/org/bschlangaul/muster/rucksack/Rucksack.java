package org.bschlangaul.muster.rucksack;

import org.bschlangaul.helfer.Konsole;

/**
 * Die Lösung des Rucksack-Problems naiv (rekursiv) oder mit Dynamischer
 * Programmierung. Quelle: <a href=
 * "https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/">geeksforgeeks.org</a>
 */
class Rucksack {

  /**
   * Helfer-Methode, die größere Zahl zurückgibt.
   *
   * @param a Die Zahl a.
   * @param b Die Zahl b
   *
   * @return Die größere Zahl.
   */
  static int max(int a, int b) {
    return (a > b) ? a : b;
  }

  /**
   * Lösung des Rucksackproblems mit der Hilfe eines naiven, rekursiven Ansatzes.
   *
   * @param kapazität Die noch verhandene Kapazität des Rucksacks an Gewichten.
   * @param gewichte  Die Gewichte der einzelnen Gegenstände.
   * @param werte     Die Werte der einzelnen Gegenstände.
   * @param n         Die Anzahl der Gegenstände, die sich noch nicht im Rucksack
   *                  befinden.
   *
   * @return Die Summe der Werte der Gegenstände, die in den Rucksack gepackt
   *         werden konnten.
   */
  static int rucksackNaiv(int kapazität, int gewichte[], int werte[], int n) {
    if (n == 0 || kapazität == 0)
      return 0;

    // Wenn das Gewicht des n-ten Gegenstand größer als die Kapazität des Rucksacks
    // ist, dann kann der Gegenstand nicht in die optimale Lösung mit einbezogen
    // werden.
    if (gewichte[n - 1] > kapazität)
      return rucksackNaiv(kapazität, gewichte, werte, n - 1);

    // Gib das Maxium dieser zwei Möglichkeiten zurück:
    // (1) der n-te Gegenstand passt in den Rucksack.
    // (2) passt nicht in den Rucksack.
    else
      return max(werte[n - 1] + rucksackNaiv(kapazität - gewichte[n - 1], gewichte, werte, n - 1),
          rucksackNaiv(kapazität, gewichte, werte, n - 1));
  }

  /**
   * Lösung des Rucksackproblems mit der Hilfe der Dynamischen Programmierung.
   *
   * @param kapazität Die noch verhandene Kapazität des Rucksacks an Gewichten.
   * @param gewichte  Die Gewichte der einzelnen Gegenstände.
   * @param werte     Die Werte der einzelnen Gegenstände.
   * @param n         Die Anzahl der Gegenstände, die sich noch nicht im Rucksack
   *                  befinden.
   *
   * @return Die Summe der Werte der Gegenstände, die in den Rucksack gepackt
   *         werden konnten.
   */
  static int rucksackDP(int kapazität, int gewichte[], int werte[], int n) {
    int i, w;
    int k[][] = new int[n + 1][kapazität + 1];

    for (i = 0; i <= n; i++) {
      for (w = 0; w <= kapazität; w++) {
        if (i == 0 || w == 0)
          k[i][w] = 0;
        else if (gewichte[i - 1] <= w)
          k[i][w] = max(werte[i - 1] + k[i - 1][w - gewichte[i - 1]], k[i - 1][w]);
        else
          k[i][w] = k[i - 1][w];
      }
    }

    Konsole.zeige2DIntFeld(k);

    return k[n][kapazität];
  }

  public static void main(String args[]) {
    int werte[] = new int[] { 60, 100, 120 };
    int gewichte[] = new int[] { 10, 20, 30 };
    int kapazität = 50;
    int n = werte.length;
    System.out.println(rucksackNaiv(kapazität, gewichte, werte, n));
    System.out.println(rucksackDP(kapazität, gewichte, werte, n));
  }
}
