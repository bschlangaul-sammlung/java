package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus.
 *
 * Der Algorithmus wählt zuerst das linke Element aus.
 */
public class SelectionLinksIterativ extends Sortieralgorithmus {

  public int[] sortiere() {
    int markierung = 0;
    while (markierung < zahlen.length - 1) {
      int min = markierung;
      for (int i = markierung; i < zahlen.length; i++) {
        if (zahlen[i] < zahlen[min]) {
          min = i;
        }
      }
      vertausche(markierung, min);
      markierung++;
    }
    return zahlen;
  }

  public static void main(String[] args) {
    new SelectionLinksIterativ().teste();
  }
}
