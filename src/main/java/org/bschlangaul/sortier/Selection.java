package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus. (Nach Saake
 * Seite 128)
 */
public class Selection extends Sortieralgorithmus {

  public int[] sortiere() {
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
      vertausche(markierung, max);
      // Die Markierung um eins nach vorne verlegen.
      markierung--;
    }
    return zahlen;
  }

  public static void main(String[] args) {

  }
}
