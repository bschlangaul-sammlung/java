package org.bschlangaul.sortier;

/**
 * Minimales Codebeispiel des Mergesorts mit kurzen Variablennamen.
 *
 * Zum Auswendiglernen gedacht, um den Code schnell im Examen reproduzieren zu
 * können. (Siehe Klasse {@link Merge}, nach Saake Seite 134)
 */
public class MergeMinimal extends SortieralgorithmusMinimal {

  /**
   * Eine Hilfsmethode für rekursives Sortieren durch Mischen des
   * Mergesort-Algorithmus.
   *
   * <p><strong>Weitere Abkürzungen</strong></p>
   *
   * <ul>
   * <li>i: Index links
   * <li>j: Index rechts
   * <li>k: Index
   * <li>m: Index-Nummer der Mitte
   * </ul>
   *
   * @param l Die linke Grenze.Die Index-Nummer, ab der das Zahlen-Feld sortiert
   *          werden soll.
   * @param r Die rechte Grenze. Die Index-Nummer, bis zu der das Zahlen-Feld
   *          sortiert werden soll.
   * @param h Eine Hilfsfeld, in dem die Zahlen temporär zwischengespeichert
   *          werden.
   */
  private void mischen(int l, int r, int[] h) {
    if (r <= l)
      return;
    int i, j, k;
    int m = (r + l) / 2;
    mischen(l, m, h);
    mischen(m + 1, r, h);
    for (k = l; k <= m; k++) {
      h[k] = a[k];
    }
    for (k = m; k < r; k++) {
      h[r + m - k] = a[k + 1];
    }
    i = l;
    j = r;
    for (k = l; k <= r; k++) {
      if (h[i] < h[j]) {
        a[k] = h[i++];
      } else {
        a[k] = h[j--];
      }
    }
  }

  public int[] sortiere() {
    int h[] = new int[a.length];
    mischen(0, a.length - 1, h);
    return a;
  }

  public static void main(String[] args) {
    new MergeMinimal().teste();
  }
}
