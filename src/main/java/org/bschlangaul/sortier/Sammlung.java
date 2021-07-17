package org.bschlangaul.sortier;

/**
 * Eine Sammlung von Sortier-Algorithmen, die in einer Klasse zusammengefügt
 * sind. Sie enhalten keine Kommentare. Kommentierte Code-Beispiele gibt es in
 * den einzelen Klassen, die auf einen Sortieralgorithmus spezialisiert sind. Es
 * werden Ein-Buchstaben-Variablennamen verwendet, damit man die Code-Beispiele
 * per Hand flüssig schreiben kann.
 *
 * <ul>
 * <li>a: array (Ein Feld mit Integer-Zahlen, das sortiert werden soll)
 * <li>i1, i2: index1, index2 (Eine Index-Nummer in einem Zahlen-Feld)
 * <li>m: merker oder markierung (Eine Zahl wird in einer Hilfsvariablen
 * bespeichert)
 * </ul>
 */
public class Sammlung {

  /**
   * Vertausche zwei Zahlen im einem Zahlen-Feld. Im Englischen heißt die Methode
   * auch oft „swap“.
   *
   * @param a  Ein Feld mit Zahlen.
   * @param i1 Die Index-Nummer der ersten Zahl.
   * @param i2 Die Index-Nummer der zweiten Zahl.
   */
  static void vertausche(int[] a, int i1, int i2) {
    int tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }

  /**
   * Sortiere mit Hilfe des Bubblesort-Algorithmus.
   *
   * <h2>Weitere Abkürzungen
   *
   * <ul>
   * <li>t: getauscht
   * </ul>
   *
   * @param a Ein Feld mit Zahlen, das sortiert werden soll.
   */
  static void bubblesort(int[] a) {
    boolean t;
    do {
      t = false;
      for (int i = 0; i < a.length - 1; i++) {
        if (a[i] > a[i + 1]) {
          vertausche(a, i, i + 1);
          t = true;
        }
      }
    } while (t);
  }

  static void insertionsort(int[] a) {
    for (int i = 1; i < a.length; i++) {
      int m = a[i];
      int j = i;
      while (j >= 1 && a[j - 1] > m) {
        a[j] = a[j - 1];
        j--;
      }
      a[j] = m;
    }
  }

  static void selectionsort(int[] a) {
    int m = a.length - 1;
    while (m >= 0) {
      int max = 0;
      for (int i = 1; i <= m; i++) {
        if (a[i] > a[max]) {
          max = i;
        }
      }
      vertausche(a, m, max);
      m--;
    }
  }

  /**
   * Hilfsmethode zum Zerlegen der Zahlen-Folge für den Quicksort-Algorithmus.
   * Diese Methode heißt im Englischen auch oft „partition“.
   *
   * <h2>Weitere Abkürzungen
   *
   * <ul>
   * <li>pn: pivotPositionNeu (Die Index-Nummer des neuen Pivot-Elements)
   * <li>pw: pivotWert (Der Wert des (alten) Pivot-Elements)
   * </ul>
   *
   * @param a Ein Feld mit Zahlen, das sortiert werden soll.
   * @param u Die Index-Nummer der unteren Grenze.
   * @param o Die Index-Nummer der oberen Grenze.
   * @param p Die Index-Nummer der Pivot-Position.
   *
   * @return Die Index-Nummer der neuen Pivot-Position
   */
  static int quicksortPartition(int[] a, int u, int o, int p) {
    int pn = u;
    int pw = a[p];
    vertausche(a, p, o);
    for (int i = u; i < o; i++) {
      if (a[i] <= pw) {
        vertausche(a, pn++, i);
      }
    }
    vertausche(a, o, pn);
    return pn;
  }

  static void quicksort(int[] a, int u, int o) {
    int p = (u + o) / 2;
    if (o > u) {
      int pn = quicksortPartition(a, u, o, p);
      quicksort(a, u, pn - 1);
      quicksort(a, pn + 1, o);
    }
  }

  static void quicksort(int[] a) {
    quicksort(a, 0, a.length - 1);
  }

  /**
   * Eine Hilfsmethode für rekursives Sortieren durch Mischen des
   * Mergesort-Algorithmus.
   *
   * <h2>Weitere Abkürzungen
   *
   * <ul>
   * <li>i: Index links
   * <li>j: Index rechts
   * <li>k: Index
   * <li>m: Index-Nummer der Mitte
   * </ul>
   *
   * @param a Ein Feld mit Zahlen, das sortiert werden soll.
   * @param l Die linke Grenze.Die Index-Nummer, ab der das Zahlen-Feld sortiert
   *          werden soll.
   * @param r Die rechte Grenze. Die Index-Nummer, bis zu der das Zahlen-Feld
   *          sortiert werden soll.
   * @param h Eine Hilfsfeld, in dem die Zahlen temporär zwischengespeichert
   *          werden.
   */
  private static void mergesort(int[] a, int l, int r, int[] h) {
    if (r <= l)
      return;
    int i, j, k;
    int m = (r + l) / 2;
    mergesort(a, l, m, h);
    mergesort(a, m + 1, r, h);
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

  public static void mergesort(int[] a) {
    int h[] = new int[a.length];
    mergesort(a, 0, a.length - 1, h);
  }
}
