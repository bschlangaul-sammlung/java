package org.bschlangaul.sortier;

/**
 * Minimales Codebeispiel des Selectionsorts mit kurzen Variablennamen.
 *
 * Zum Auswendiglernen gedacht, um den Code schnell im Examen reproduzieren zu
 * können. (Siehe Klasse {@link SelectionIterativ}, nach Saake Seite 128)
 */
public class SelectionMinimal extends SortieralgorithmusMinimal {

  /**
   * Sortiere mit Hilfe des Insertionsort-Algorithmus.
   *
   * <p>
   * <strong>Abkürzungen:</strong>
   * </p>
   *
   * <ul>
   * <li>m: markierung
   * </ul>
   */
  public int[] sortiere() {
    int m = a.length - 1;
    while (m >= 0) {
      int max = 0;
      for (int i = 1; i <= m; i++) {
        if (a[i] > a[max]) {
          max = i;
        }
      }
      vertausche(m, max);
      m--;
    }
    return a;
  }

  public static void main(String[] args) {
    new SelectionMinimal().teste();
  }
}
