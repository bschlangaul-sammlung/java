package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus.
 *
 * Wähle zuerst das linke Element aus.
 */
public class SelectionLinksIterativ extends Sortieralgorithmus {

  public int[] sortiere() {
    int m = 0;
    while (m < zahlen.length - 1) {
      int min = zahlen.length - 1;
      for (int i = m; i < zahlen.length; i++) {
        if (zahlen[i] < zahlen[min]) {
          min = i;
        }
      }
      vertausche(m, min);
      m++;
    }
    return zahlen;
  }

  public static void main(String[] args) {
    new SelectionLinksIterativ().teste();
  }
}
