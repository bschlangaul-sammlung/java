package org.bschlangaul.aufgaben.aud.ab_2;

/**
 * Vorlage für verschiedene Sortieralgorithmen.
 *
 * <a href="https://www.studon.fau.de/file2506781_download.html">Angabe: AB_2
 * Rekursion_Sortieren_Komplexitaet.pdf</a>
 * <a href="https://www.studon.fau.de/file2506784_download.html">Lösung: AB_2
 * Rekursion_Sortieren_Komplexitaet_Lsg.pdf</a>
 */
public class Sortieralgorithmen {
  private final Eingabefenster fenster;

  /**
   * Konstruktor: Erzeugt ein neues Objekt der Klasse Sortieralgorithmen und macht
   * es mit dem zugehörigen Eingabefenster bekannt.
   *
   * @param fenster Das Eingabefenster
   */
  public Sortieralgorithmen(Eingabefenster fenster) {
    this.fenster = fenster;
  }

  /**
   * Hilfsmethode zum Vertauschen zweier Elemente eines Arrays.
   *
   * @param array  Ein Feld mit Zahlen.
   * @param index1 Index 1.
   * @param index2 Index 2.
   */
  private void swap(int[] array, int index1, int index2) {
    int zwischenspeicher = array[index1];
    array[index1] = array[index2];
    array[index2] = zwischenspeicher;
  }

  /**
   * InsertionSort: Sortieren durch Einfügen.
   *
   * @param array Ein Feld mit Zahlen.
   *
   * @return Ein sortiertes Feld mit Zahlen.
   */
  public int[] insertionSort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      fenster.schreibeZeile(i + ". Runde:");
      fenster.schreibe("Anfang dieser Runde:     ");
      fenster.schreibeArray(array);

      // Zähler für die innere Schleife.
      // Er beginnt beim Wert von i und zählt abwärts:
      int j = i;

      // Wiederhole, solange die Zahl an der Position j
      // kleiner als ihr linker Nachbar ist:
      while (j > 0 && array[j] < array[j - 1]) {
        // Vertausche diese beiden Zahlen:
        swap(array, j, j - 1);

        fenster.schreibe("Innere Schleife (j = " + j + "): ");
        fenster.schreibeArray(array);

        // Verringere den Zähler j um 1:
        j--;
      }

      fenster.schreibe("Ergebnis dieser Runde:   ");
      fenster.schreibeArray(array);
      fenster.schreibeZeile("");
    }

    return array;
  }

  /**
   * SelectionSort: Sortieren durch Selektion.
   *
   * @param array Ein Feld mit Zahlen.
   *
   * @return Ein sortiertes Feld mit Zahlen.
   */
  public int[] selectionSort(int[] array) {
    fenster.schreibeZeile("\nFühre die Methode selectionSort() aus:");
    fenster.schreibe("Sortiere dieses Feld: ");
    fenster.schreibeArray(array);
    fenster.schreibeZeile("");
    int marker = array.length - 1;
    while (marker >= 0) {
      int max = 0;
      for (int i = 0; i <= marker; i++) {
        if (array[i] > array[max]) {
          max = i;
        }
      }
      fenster.schreibeZeile("Der Marker liegt bei: " + marker);
      fenster.schreibeZeile("Das Maximum liegt bei: " + max);
      fenster.schreibeZeile("Diese beiden Elemente werden nun vertauscht.");
      swap(array, marker, max);
      fenster.schreibe("Ergebnis dieser Runde: ");
      fenster.schreibeArray(array);
      fenster.schreibeZeile("");
      marker--;
    }
    return array;
  }

  /**
   * BubbleSort: Sortieren durch Vertauschen.
   *
   * @param array Ein Feld mit Zahlen.
   *
   * @return Ein sortiertes Feld mit Zahlen.
   */
  public int[] bubbleSort(int[] array) {
    fenster.schreibeZeile("\nFühre die Methode bubbleSort() aus:");
    fenster.schreibe("Sortiere dieses Feld: ");
    fenster.schreibeArray(array);
    fenster.schreibeZeile("");
    boolean swapped;
    do {
      swapped = false;
      for (int i = 0; i < array.length - 1; i++) {
        if (array[i] > array[i + 1]) {
          fenster.schreibe("Das Element an der Stelle " + i);
          fenster.schreibeZeile(" ist größer als sein Nachfolger.");
          fenster.schreibeZeile("Diese beiden werden nun vertauscht.");
          swap(array, i, i + 1);
          fenster.schreibe("Ergebnis dieser Runde: ");
          fenster.schreibeArray(array);
          fenster.schreibeZeile(" ");
          swapped = true;
        }
      }
    } while (swapped);
    return array;
  }

  /**
   * MergeSort: Sortieren durch Mischen.
   *
   * @param array Ein Feld mit Zahlen.
   *
   * @return Ein sortiertes Feld mit Zahlen.
   */
  public int[] mergeSort(int[] array) {
    ///// Bitte hier vervollständigen /////
    return array;
  }

  /**
   * QuickSort.
   *
   * @param array Ein Feld mit Zahlen.
   *
   * @return Ein sortiertes Feld mit Zahlen.
   */
  public int[] quickSort(int[] array) {
    ///// Bitte hier vervollständigen /////
    return array;
  }

  public static void main(String[] args) {
    Eingabefenster fenster = new Eingabefenster();
    Sortieralgorithmen sortieren = new Sortieralgorithmen(fenster);

    sortieren.selectionSort(new int[] { 5, 3, 17, 7, 42, 23 });
    sortieren.bubbleSort(new int[] { 5, 3, 17, 7, 42, 23 });
  }
}
