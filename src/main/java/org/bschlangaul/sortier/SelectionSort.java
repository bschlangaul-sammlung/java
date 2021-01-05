package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus. (Nach Saake
 * Seite 128)
 */
public class SelectionSort {

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus. (Nach Saake
   * Seite 128)
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public static int[] sortiereIterativ(int[] zahlen) {
    // Am Anfang ist die Markierung das letzte Element im Zahlen-Array.
    int markierung = zahlen.length - 1;
    while (markierung >= 0) {
      // Bestimme das größtes Element.
      // max ist der Index des größten Elements.
      int max = 0;
      // Wir vergleichen zuerst die Zahlen mit der Index-Number
      // 0 und 1, dann 1 und 2, etc. bis zur Markierung
      for (int i = 1; i <= markierung; i++) {
        if (zahlen[i] > zahlen[max]) {
          max = i;
        }
      }

      // Tausche zahlen[markierung] mit dem gefundenem Element.
      Helfer.vertausche(zahlen, markierung, max);
      // Die Markierung um eins nach vorne verlegen.
      markierung--;
    }
    return zahlen;
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus in einer
   * halbrekursiven Art und Weise. (nach <a href=
   * "https://www.techiedelight.com/selection-sort-iterative-recursive/">techiedelight.com/</a>
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   * @param index  Ab welcher Index-Nummer im Zahlen-Feld sortiert werden soll.
   * @param anzahl Wie viele Zahlen aber der gegeben Index-Nummer sortiert werden
   *               sollen.
   */
  private static void sortiereHalbRekursiv(int[] zahlen, int index, int anzahl) {
    if (anzahl <= 0)
      return;
    int minimum = index;
    for (int i = index + 1; i < anzahl; i++) {
      if (zahlen[i] < zahlen[minimum]) {
        minimum = i;
      }
    }
    Helfer.vertausche(zahlen, minimum, index);
    if (index + 1 < anzahl) {
      sortiereHalbRekursiv(zahlen, index + 1, anzahl);
    }
  }


  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus in einer
   * halbrekursiven Art und Weise. (nach <a href=
   * "https://www.techiedelight.com/selection-sort-iterative-recursive/">techiedelight.com/</a>
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public static int[] sortiereHalbRekursiv(int[] zahlen) {
    sortiereHalbRekursiv(zahlen, 0, zahlen.length);
    return zahlen;
  }

  /**
   * Gib die Indexposition im Zahlenfeld zurück, das mit der kleinsten Zahl belegt
   * ist. (nach <a href=
   * "https://www.geeksforgeeks.org/recursive-selection-sort/">geeksforgeeks.org</a>)
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   * @param i      Die Index-Nummer der einen Zahlen, mit der verglichen werden
   *               soll.
   * @param j      Die Index-Nummer der anderen Zahlen, mit der verglichen werden
   *               soll.
   *
   * @return Die Indexposition im Zahlenfeld zurück, das mit der kleinsten Zahl
   *         belegt.
   */
  private static int gibMinIndex(int zahlen[], int i, int j) {
    if (i == j)
      return i;
    int k = gibMinIndex(zahlen, i + 1, j);
    return (zahlen[i] < zahlen[k]) ? i : k;
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus in einer
   * rekursiven Art und Weise.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   * @param index  Ab welcher Index-Nummer im Zahlen-Feld sortiert werden soll.
   * @param anzahl Wie viele Zahlen aber der gegeben Index-Nummer sortiert werden
   *               sollen.
   */
  private static void sortiereRekursiv(int zahlen[], int index, int anzahl) {
    if (index == anzahl)
      return;
    int k = gibMinIndex(zahlen, index, anzahl - 1);
    if (k != index) {
      Helfer.vertausche(zahlen, k, index);
    }
    sortiereRekursiv(zahlen, index + 1, anzahl);
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus in einer
   * rekursiven Art und Weise.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public static int[] sortiereRekursiv(int[] zahlen) {
    sortiereRekursiv(zahlen, 0, zahlen.length);
    return zahlen;
  }

  public static void main(String[] args) {
    // int[] zahlen = { 42, 23, 1, 7, 5, -3, 3, 12, 78, 15 };
    // int[] zahlen = { -74, -26, -5, -1, 0, 5, 8, 42, 78 };
    // int[] zahlen = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    // int[] zahlen = { 4 };
    int[] zahlen = {};

    sortiereIterativ(zahlen);
    // sortiereHalbRekursiv(zahlen);
    // sortiereRekursiv(zahlen);

    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
