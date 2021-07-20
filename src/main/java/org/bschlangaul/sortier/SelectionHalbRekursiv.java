package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus in einer
 * halbrekursiven Art und Weise. (nach <a href=
 * "https://www.techiedelight.com/selection-sort-iterative-recursive/">techiedelight.com/</a>
 */
public class SelectionHalbRekursiv extends Sortieralgorithmus {

  /**
   * @param index  Ab welcher Index-Nummer im Zahlen-Feld sortiert werden soll.
   * @param anzahl Wie viele Zahlen ab der gegebenen Index-Nummer sortiert werden
   *               sollen.
   */
  private void sortiereHalbRekursiv(int index, int anzahl) {
    if (anzahl <= 0) {
      return;
    }

    int minimum = index;
    for (int i = index + 1; i < anzahl; i++) {
      if (zahlen[i] < zahlen[minimum]) {
        minimum = i;
      }
    }
    vertausche(minimum, index);
    if (index + 1 < anzahl) {
      sortiereHalbRekursiv(index + 1, anzahl);
    }
  }

  public int[] sortiere() {
    sortiereHalbRekursiv(0, zahlen.length);
    return zahlen;
  }

  public static void main(String[] args) {
    new SelectionHalbRekursiv().teste();
  }
}
