package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
 *
 * (nach Pseudo-Code auf
 * <a href="https://de.wikipedia.org/wiki/Quicksort">Wikipediaseite zum
 * Quicksort-Algortihmus</a> und Code-Beispiels auf <a href=
 * "https://javabeginners.de/Algorithmen/Sortieralgorithmen/Quicksort.php">javabeginners.de</a>)
 *
 * Ohne Hin- und Herkopieren des Pivot-Elements wie bei Saake
 * {@link QuickSaake}. Die endgültige Position des Pivot-Elements wird
 * gefunden, indem die linke und rechte Grenze immer weiter in die Mitte
 * verschoben wird.
 */
public class QuickHorare extends Sortieralgorithmus {

  /**
   * Zerlege das Zahlen-Feld.
   *
   * @param links  Die Index-Nummer ab dem das Zahlen-Feld zerlegt werden soll.
   * @param rechts Die Index-Nummer bis zu dem das Zahlen-Feld zerlegt werden
   *               soll.
   *
   * @return Die Index-Nummer, an dem das Feld zerlegt werden soll.
   */
  private int zerlege(int links, int rechts) {
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
        vertausche(i, j);
      } else {
        return j;
      }
    }
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * @param links  Die Index-Nummer ab dem das Zahlen-Feld sortiert werden soll.
   * @param rechts Die Index-Nummer bis zu dem das Zahlen-Feld sortiert werden
   *               soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  private int[] sortiereRekursiv(int links, int rechts) {
    int pivotIndexEndgültig;
    if (links < rechts) {
      pivotIndexEndgültig = zerlege(links, rechts);
      sortiereRekursiv(links, pivotIndexEndgültig);
      sortiereRekursiv(pivotIndexEndgültig + 1, rechts);
    }
    return zahlen;
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public int[] sortiere() {
    sortiereRekursiv(0, zahlen.length - 1);
    return zahlen;
  }

  public static void main(String[] args) {
    new QuickHorare().teste();
  }
}
