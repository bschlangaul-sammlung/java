package org.bschlangaul.sortier;

/**
 * (nach <a href=
 * "https://www.geeksforgeeks.org/recursive-selection-sort/">geeksforgeeks.org</a>)
 */
public class SelectionSortIterativ extends Sortieralgorithmus {

  /**
   * Gib die Indexposition im Zahlenfeld zurück, das mit der kleinsten Zahl belegt
   * ist.
   *
   * @param i Die Index-Nummer der einen Zahlen, mit der verglichen werden soll.
   * @param j Die Index-Nummer der anderen Zahlen, mit der verglichen werden soll.
   *
   * @return Die Indexposition im Zahlenfeld zurück, das mit der kleinsten Zahl
   *         belegt.
   */
  private int gibMinIndex(int i, int j) {
    if (i == j) {
      return i;
    }
    int k = gibMinIndex(i + 1, j);
    return (zahlen[i] < zahlen[k]) ? i : k;
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus in einer
   * rekursiven Art und Weise.
   *
   * @param index  Ab welcher Index-Nummer im Zahlen-Feld sortiert werden soll.
   * @param anzahl Wie viele Zahlen aber der gegeben Index-Nummer sortiert werden
   *               sollen.
   */
  private void sortiereRekursiv(int index, int anzahl) {
    if (index == anzahl)
      return;
    int k = gibMinIndex(index, anzahl - 1);
    if (k != index) {
      vertausche(k, index);
    }
    sortiereRekursiv(index + 1, anzahl);
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Selectionsort-Algorithmus in einer
   * rekursiven Art und Weise.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public int[] sortiere() {
    sortiereRekursiv(0, zahlen.length);
    return zahlen;
  }

  public static void main(String[] args) {
    new SelectionSortIterativ().teste();
  }
}
