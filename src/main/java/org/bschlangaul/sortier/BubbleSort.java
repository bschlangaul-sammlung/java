package org.bschlangaul.sortier;

/**
 * Nach Saake Seite 130-131
 */
public class BubbleSort {

  static int[] sortiere(int[] zahlen) {
    boolean getauscht;
    do {
      getauscht = false;
      for (int i = 0; i < zahlen.length - 1; i++) {
        if (zahlen[i] > zahlen[i + 1]) {
          // Elemente vertauschen
          Helfer.vertausche(zahlen, i, i + 1);
          getauscht = true;
        }
      }
      // solange Vertauschung auftritt
    } while (getauscht);
    return zahlen;
  }

  /**
   * https://www.geeksforgeeks.org/recursive-bubble-sort/
   *
   * @param zahlen
   * @param letztes Index-Nummer des Elements, bis zu dem sortiert werden soll.
   */
  private static void sortiereRekursiv(int[] zahlen, int letztes) {
    // Bei einem leeren Feld kann die Variable letztes -1 annehmen.
    // Wenn letztes 0 ist, dann sind die Zahlen sortiert.
    if (letztes <= 0)
      return;

    // Ein Durchgang der Blasensortierung. Nach diesem Durchgang befindet sich das
    // größte Element am Ende des Felds.
    for (int i = 0; i < letztes; i++)
      if (zahlen[i] > zahlen[i + 1]) {
        Helfer.vertausche(zahlen, i, i + 1);
      }

    // Sortiere jetzt die restlichen Elemente.
    sortiereRekursiv(zahlen, letztes - 1);
  }

  public static int[] sortiereRekursiv(int[] zahlen) {
    sortiereRekursiv(zahlen, zahlen.length - 1);
    return zahlen;
  }

  public static void main(String[] args) {
    int[] zahlen = { 42, 23, 1, 7, 5, 3, 12, 78, 15 };
    sortiere(zahlen);

    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
