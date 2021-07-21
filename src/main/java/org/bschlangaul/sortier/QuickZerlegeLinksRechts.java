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
public class QuickZerlegeLinksRechts extends Sortieralgorithmus {

  /**
   * Zerlege das Zahlen-Feld.
   *
   * @param untereGrenze Die Index-Nummer ab dem das Zahlen-Feld zerlegt werden
   *                     soll.
   * @param obereGrenze  Die Index-Nummer bis zu dem das Zahlen-Feld zerlegt
   *                     werden soll.
   *
   * @return Die Index-Nummer, an dem das Feld zerlegt werden soll.
   */
  private int zerlege(int untereGrenze, int obereGrenze) {
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
        vertausche(i, j);
      } else {
        return j;
      }
    }
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * @param untereGrenze Die Index-Nummer ab dem das Zahlen-Feld sortiert werden
   *                     soll.
   * @param obereGrenze  Die Index-Nummer bis zu dem das Zahlen-Feld sortiert
   *                     werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  private int[] sortiereRekursiv(int untereGrenze, int obereGrenze) {
    int pivotPositionNeu;
    if (untereGrenze < obereGrenze) {
      pivotPositionNeu = zerlege(untereGrenze, obereGrenze);
      sortiereRekursiv(untereGrenze, pivotPositionNeu);
      sortiereRekursiv(pivotPositionNeu + 1, obereGrenze);
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
  public int[] sortiere() {
    sortiereRekursiv(0, zahlen.length - 1);
    return zahlen;
  }

  public static void main(String[] args) {
    new QuickZerlegeLinksRechts().teste();
  }
}
