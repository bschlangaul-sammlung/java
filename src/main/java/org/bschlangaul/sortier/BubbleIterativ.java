package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Bubblesort-Algorithmus. (Nach Saake
 * Seite 130-131)
 */
public class BubbleIterativ extends Sortieralgorithmus {

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Bubblesort-Algorithmus.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public int[] sortiere() {
    int durchlaufNr = 0;
    boolean getauscht;
    do {
      durchlaufNr++;
      berichte.feld("Durchlauf Nr. " + durchlaufNr);
      getauscht = false;
      for (int i = 0; i < zahlen.length - 1; i++) {
        if (zahlen[i] > zahlen[i + 1]) {
          // Elemente vertauschen
          vertausche(i, i + 1);
          getauscht = true;
        }
      }
      // solange Vertauschung auftritt
    } while (getauscht);
    return zahlen;
  }

  public static void main(String[] args) {
    new BubbleIterativ().teste();
  }
}
