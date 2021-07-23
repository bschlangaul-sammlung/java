package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Insertionsort-Algorithmus in einer
 * rekursiven Art und Weise. (nach <a href=
 * "https://www.geeksforgeeks.org/recursive-insertion-sort/">geeksforgeeks.org</a>)
 */
public class InsertionRekursiv extends Sortieralgorithmus {

/**
 * @param i Wie viele Zahlen im Feld sortiert werden sollen.
 */
  private void sortiereRekursiv(int i) {
    // Abbruchsfall
    if (i <= 1)
      return;

    // Sortiere die ersten n-1 Elemente.
    sortiereRekursiv(i - 1);

    int markierung = zahlen[i - 1];
    int j = i - 2;

    while (j >= 0 && zahlen[j] > markierung) {
      zahlen[j + 1] = zahlen[j];
      j--;
    }
    zahlen[j + 1] = markierung;
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Insertionsort-Algorithmus in einer
   * rekursiven Art und Weise.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public int[] sortiere() {
    sortiereRekursiv(zahlen.length);
    return zahlen;
  }

  public static void main(String[] args) {
    new InsertionRekursiv().teste();
  }
}
