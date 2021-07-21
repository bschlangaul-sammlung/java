package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Insertionsort-Algorithmus. (Nach Saake
 * Seite 127)
 */
public class InsertionIterativ extends Sortieralgorithmus {

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Insertionsort-Algorithmus.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public int[] sortiere() {
    for (int i = 1; i < zahlen.length; i++) {
      // Links von der Markierung sind die Zahlen sortiert,
      // rechts davon unsortiert.
      int markierung = zahlen[i];
      int j = i;
      // Für alle Zahlen links von der Markierung.
      while (j >= 1 && zahlen[j - 1] > markierung) {
        // Die Zahl eins weiter nach rechts setzen
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

  public static void main(String[] args) {
    new InsertionIterativ().teste();
  }
}
