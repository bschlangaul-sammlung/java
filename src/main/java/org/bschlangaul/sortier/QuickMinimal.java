package org.bschlangaul.sortier;

/**
 * Minimales Codebeispiel des Quicksort-Algortihmus mit kurzen Variablennamen.
 *
 * Zum Auswendiglernen gedacht, um den Code schnell im Examen reproduzieren zu
 * können. (Siehe Klasse {@link QuickZerlegeLinksRechts})
 */
public class QuickMinimal extends SortieralgorithmusMinimal {

  /**
   * Hilfsmethode zum Zerlegen der Zahlen-Folge für den Quicksort-Algorithmus.
   * Diese Methode heißt im Englischen auch oft „partition“.
   *
   * <p>
   * <strong>Abkürzungen:</strong>
   * </p>
   *
   * <ul>
   * <li>pw: Pivot-Wert
   * </ul>
   *
   * @param l Die Index-Nummer ab dem das Zahlen-Feld zerlegt werden soll.
   * @param r Die Index-Nummer bis zu dem das Zahlen-Feld zerlegt werden soll.
   *
   * @return Die Index-Nummer der endgültigen Position des Pivot-Werts.
   */
  private int zerlege(int l, int r) {
    int i, j;
    int pw = a[(l + r) / 2];
    i = l - 1;
    j = r + 1;
    while (true) {
      do {
        i++;
      } while (a[i] < pw);

      do {
        j--;
      } while (a[j] > pw);

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
   * <p>
   * <strong>Abkürzungen:</strong>
   * </p>
   *
   * <ul>
   * <li>p: Die Index-Nummer der endgültigen Position der Pivot-Werts.
   * </ul>
   *
   * @param l Die Index-Nummer ab dem das Zahlen-Feld sortiert werden soll.
   * @param r Die Index-Nummer bis zu dem das Zahlen-Feld sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  private int[] sortiereRekursiv(int l, int r) {
    int p;
    if (l < r) {
      p = zerlege(l, r);
      sortiereRekursiv(l, p);
      sortiereRekursiv(p + 1, r);
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
    new SelectionMinimal().teste();
  }
}
