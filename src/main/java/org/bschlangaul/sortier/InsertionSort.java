package org.bschlangaul.sortier;

/**
 * Nach Saake Seite 127
 */
public class InsertionSort {

  static int[] sortiere(int[] zahlen) {
    for (int i = 1; i < zahlen.length; i++) {
      // Links von der Markierung sind die Zahlen sortiert,
      // rechts davon unsortiert.
      int markierung = zahlen[i];
      int j = i;
      // Für alle Zahlen links von der Markierung.
      while (j >= 1 && zahlen[j - 1] > markierung) {
        // Die Zahl eins weiter nach rechts setzten
        // An der Position j - 1 und j stehen jetzt zweimal die gleichen
        // Zahlen
        zahlen[j] = zahlen[j - 1];
        j--;
      }
      // Die markierte Zahl an die richtige Stelle setzen.
      zahlen[j] = markierung;
    }
    return zahlen;
  }

  /**
   * https://www.geeksforgeeks.org/recursive-insertion-sort/
   *
   * @param zahlen
   * @param n
   */
  private static void sortiereRekursiv(int zahlen[], int n) {
    // Base case
    if (n <= 1)
      return;

    // Sort first n-1 elements
    sortiereRekursiv(zahlen, n - 1);

    // Insert last element at its correct position
    // in sorted array.
    int last = zahlen[n - 1];
    int j = n - 2;

    /*
     * Move elements of arr[0..i-1], that are greater than key, to one position
     * ahead of their current position
     */
    while (j >= 0 && zahlen[j] > last) {
      zahlen[j + 1] = zahlen[j];
      j--;
    }
    zahlen[j + 1] = last;
  }

  public static int[] sortiereRekursiv(int[] zahlen) {
    sortiereRekursiv(zahlen, zahlen.length);
    return zahlen;
  }

  public static void main(String[] args) {
    // int[] zahlen = { 42, 23, 1, 7, 5, 3, 12, 78, 15 };
    int[] zahlen = { 7, 4, 9, 2, 3 };
    sortiere(zahlen);

    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
