package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
 *
 * (nach Pseudo-Code auf
 * <a href="https://de.wikipedia.org/wiki/Quicksort">Wikipediaseite zum
 * Quicksort-Algortihmus</a> und Code-Beispiels auf <a href=
 * "https://javabeginners.de/Algorithmen/Sortieralgorithmen/Quicksort.php">javabeginners.de</a>)
 *
 * Ohne Hin- und Herkopieren des Pivot-Elements wie bei Saake. Der Pivot-Wert
 * wird in der „zerlegen“-Methode festgelegt.
 */
public class QuickSortDoWhile {

  /**
   * Zerlege das Zahlen-Feld.
   *
   * @param zahlen       Ein Feld mit Zahlen, das zerlegt werden soll.
   * @param untereGrenze Die Index-Nummer ab dem das Zahlen-Feld zerlegt werden
   *                     soll.
   * @param obereGrenze  Die Index-Nummer bis zu dem das Zahlen-Feld zerlegt
   *                     werden soll.
   *
   * @return Die Index-Nummer, an dem das Feld zerlegt werden soll.
   */
  static int zerlege(int[] zahlen, int untereGrenze, int obereGrenze) {
    int i, j;
    int pivotWert = zahlen[(untereGrenze + obereGrenze) / 2];
    i = untereGrenze - 1;
    j = obereGrenze + 1;
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
   * @param zahlen       Ein Feld mit Zahlen, das sortiert werden soll.
   * @param untereGrenze Die Index-Nummer ab dem das Zahlen-Feld sortiert werden
   *                     soll.
   * @param obereGrenze  Die Index-Nummer bis zu dem das Zahlen-Feld sortiert
   *                     werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  static int[] sortiereRekursiv(int[] zahlen, int untereGrenze, int obereGrenze) {
    int pivotPositionNeu;
    if (untereGrenze < obereGrenze) {
      pivotPositionNeu = zerlege(zahlen, untereGrenze, obereGrenze);
      sortiereRekursiv(zahlen, untereGrenze, pivotPositionNeu);
      sortiereRekursiv(zahlen, pivotPositionNeu + 1, obereGrenze);
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
    int[] zahlen = { 8, 2, 1, 5, 9, 7, 3 };

    // int[] zahlen = { 16, 23, 14, 7, 21, 20, 6, 1, 17, 13, 12, 9, 3, 19 };
    sortiere(zahlen);
    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
