package org.bschlangaul.sortier;

/**
 * Enthält die Hilfsmethode {@link vertausche}.
 */
public class Helfer {

  /**
   * Vertausche zwei Zahlen im einem Zahlen-Feld.
   *
   * @param zahlen Ein Feld mit Zahlen.
   * @param index1 Die Index-Nummer der ersten Zahl.
   * @param index2 Die Index-Nummer der zweiten Zahl.
   */
  static void vertausche(int[] zahlen, int index1, int index2) {
    int tmp = zahlen[index1];
    zahlen[index1] = zahlen[index2];
    zahlen[index2] = tmp;
  }
}
