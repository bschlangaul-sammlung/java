package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
 *
 * (nach Pseudo-Code auf
 * <a href="https://de.wikipedia.org/wiki/Quicksort">Wikipediaseite zum
 * Quicksort-Algortihmus</a> und Code-Beispiels auf <a href=
 * "https://javabeginners.de/Algorithmen/Sortieralgorithmen/Quicksort.php">javabeginners.de</a>)
 */
public class QuickSortMitte {

  /**
   * Zerlege das Zahlen-Feld.
   *
   * @param zahlen Ein Feld mit Zahlen, das zerlegt werden soll.
   * @param links  Die Index-Nummer ab dem das Zahlen-Feld zerlegt werden soll.
   * @param rechts Die Index-Nummer bis zu dem das Zahlen-Feld zerlegt werden
   *               soll.
   *
   * @return Die Index-Nummer, an dem das Feld zerlegt werden soll.
   */
  static int zerlege(int[] zahlen, int links, int rechts) {
    int i, j;
    int pivotWert = zahlen[(links + rechts) / 2];
    i = links - 1;
    j = rechts + 1;
    while (true) {
      do {
        i++;
      } while (zahlen[i] < pivotWert);

      do {
        j--;
      } while (zahlen[j] > pivotWert);

      if (i < j) {
        Helfer.vertausche(zahlen, i, j);
      } else {
        return j;
      }
    }
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   * @param links  Die Index-Nummer ab dem das Zahlen-Feld sortiert werden soll.
   * @param rechts Die Index-Nummer bis zu dem das Zahlen-Feld sortiert werden
   *               soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  static int[] sortiereRekursiv(int[] zahlen, int links, int rechts) {
    int pivotPositionNeu;
    if (links < rechts) {
      pivotPositionNeu = zerlege(zahlen, links, rechts);
      sortiereRekursiv(zahlen, links, pivotPositionNeu);
      sortiereRekursiv(zahlen, pivotPositionNeu + 1, rechts);
    }
    return zahlen;
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  static int[] sortiere(int[] zahlen) {
    sortiereRekursiv(zahlen, 0, zahlen.length - 1);
    return zahlen;
  }

  public static void main(String[] args) {
    int[] zahlen = { 16, 23, 14, 7, 21, 20, 6, 1, 17, 13, 12, 9, 3, 19 };
    sortiere(zahlen);
    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
