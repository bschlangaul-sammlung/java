package org.bschlangaul.sortier;

/**
 * Minimales Codebeispiel des Insertionsorts mit kurzen Variablennamen.
 *
 * Zum Auswendiglernen gedacht, um den Code schnell im Examen reproduzieren zu
 * können. (Sie Klasse {@link InsertionIterativ}, nach Saake Seite 127)
 */
public class InsertionMinimal extends SortieralgorithmusMinimal {

  public int[] sortiere() {
    for (int i = 1; i < a.length; i++) {
      int m = a[i];
      int j = i;
      while (j >= 1 && a[j - 1] > m) {
        a[j] = a[j - 1];
        j--;
      }
      a[j] = m;
    }
    return a;
  }

  public static void main(String[] args) {
    new InsertionMinimal().teste();
  }
}
