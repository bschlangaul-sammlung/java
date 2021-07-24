package org.bschlangaul.sortier;

/**
 * https://www.geeksforgeeks.org/recursive-bubble-sort/
 */
public class BubbleRekursiv extends Sortieralgorithmus {

  /**
   * @param letztes Index-Nummer des Elements, bis zu dem sortiert werden soll.
   */
  private void sortiereRekursiv(int letztes) {
    // Bei einem leeren Feld kann die Variable letztes -1 annehmen.
    // Wenn letztes 0 ist, dann sind die Zahlen sortiert.
    if (letztes <= 0) {
      return;
    }
    // Ein Durchgang der Blasensortierung. Nach diesem Durchgang befindet sich das
    // größte Element am Ende des Felds.
    for (int i = 0; i < letztes; i++) {
      if (zahlen[i] > zahlen[i + 1]) {
        vertausche(i, i + 1);
      }
    }
    // Sortiere jetzt die restlichen Elemente.
    sortiereRekursiv(letztes - 1);
  }

  public int[] sortiere() {
    sortiereRekursiv(zahlen.length - 1);
    return zahlen;
  }

  public static void main(String[] args) {
    new BubbleRekursiv().teste();
  }
}
