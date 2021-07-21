package org.bschlangaul.sortier;

/**
 * Minimales Codebeispiel des Selectionsorts. Zum Auswendiglernen gedacht, um
 * den Code schnell im Examen reproduzieren zu können.
 */
public class SelectionMinimal extends Sortieralgorithmus {

  /**
   * Sortiere mit Hilfe des Insertionsort-Algorithmus.
   *
   * <p>
   * <strong>Weitere Abkürzungen</strong>
   * </p>
   *
   * <ul>
   * <li>m: markierung
   * </ul>
   *
   * @param a Ein Feld mit Zahlen, das sortiert werden soll.
   */
  public int[] sortiere() {
    int m = zahlen.length - 1;
    while (m >= 0) {
      int max = 0;
      for (int i = 1; i <= m; i++) {
        if (zahlen[i] > zahlen[max]) {
          max = i;
        }
      }
      vertausche(m, max);
      m--;
    }
    return zahlen;
  }

  public static void main(String[] args) {
    new SelectionMinimal().teste();
  }
}
