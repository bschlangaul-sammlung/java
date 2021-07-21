package org.bschlangaul.sortier;

/**
 * Minimales Codebeispiel des Bubblesorts mit kurzen Variablennamen. Zum
 * Auswendiglernen gedacht, um den Code schnell im Examen reproduzieren zu
 * können. (Nach Saake Seite 130-131)
 */
public class BubbleMinimal extends SortieralgorithmusMinimal {

  /**
   * Sortiere mit Hilfe des Bubblesort-Algorithmus.
   *
   * <p><strong>Abkürzungen:</strong></p>
   *
   * <ul>
   * <li>t: getauscht
   * </ul>
   */
  public int[] sortiere() {
    boolean t;
    do {
      t = false;
      for (int i = 0; i < a.length - 1; i++) {
        if (a[i] > a[i + 1]) {
          vertausche(i, i + 1);
          t = true;
        }
      }
    } while (t);
    return a;
  }

  public static void main(String[] args) {
    new BubbleMinimal().teste();
  }
}
