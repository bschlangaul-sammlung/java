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

  static void selectionsortMin(int[] a) {
    int m = 0;
    while (m < a.length - 1) {
      int min = m;
      for (int i = m; i < a.length; i++) {
        if (a[i] < a[min]) {
          min = i;
        }
      }
      vertausche(a, m, min);
      m++;
    }
  }

  static void selectionsortFor(int[] a) {
    for (int m = 0; m < a.length - 1; m++) {
      int min = m;
      for (int i = m; i < a.length; i++) {
        if (a[i] < a[min]) {
          min = i;
        }
      }
      vertausche(a, m, min);
    }
  }
}
