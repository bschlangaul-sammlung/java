package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Mergesort-Algorithmus. (Nach Saake
 * Seite 134)
 */
public class MergeSort {

  /**
   * Hilfsmethode für rekursives Sortieren durch Mischen
   *
   * @param zahlen       Ein Feld mit Zahlen, das sortiert werden soll.
   * @param linkeGrenze  Die Index-Nummer, ab der das Zahlen-Feld sortiert werden
   *                     soll.
   * @param rechteGrenze Die Index-Nummer, bis zu der das Zahlen-Feld sortiert
   *                     werden soll.
   * @param hilfsFeld    Eine Hilfsfeld, das benötigt wird.
   */
  private static void sortiereRekursiv(int[] zahlen, int linkeGrenze, int rechteGrenze, int[] hilfsFeld) {
    // Wenn die rechte Grenze gleich (oder sogar kleiner) als die linke Grenze ist,
    // tue nichts.
    if (rechteGrenze <= linkeGrenze)
      return;

    // Zähler für diverse for-Schleifen deklarieren.
    int indexLinks, indexRechts, index;
    // zu sortierendes Feld teilen
    int mitte = (rechteGrenze + linkeGrenze) / 2;
    // Teilfelder sortieren
    sortiereRekursiv(zahlen, linkeGrenze, mitte, hilfsFeld);
    sortiereRekursiv(zahlen, mitte + 1, rechteGrenze, hilfsFeld);
    // Hilfsfeld aufbauen
    // Linke Hälfte übernehmen, z. B. aufsteigende Sortierung
    for (index = linkeGrenze; index <= mitte; index++) {
      // hilfsFeld[0-2]: 1 23 42
      hilfsFeld[index] = zahlen[index];
    }

    // Rechte Hälfte umdrehen, z. B. absteigende Sortierung
    for (index = mitte; index < rechteGrenze; index++) {
      // hilfsFeld[3-4]: 8 7
      hilfsFeld[rechteGrenze + mitte - index] = zahlen[index + 1];
    }

    // Ergebnisse mischen über Hilfsfeld
    indexLinks = linkeGrenze;
    indexRechts = rechteGrenze;
    // Beispiel:
    // indexLinks = 0, indexRechts = 4
    // hilfsFeld: 1 23 42 7 8
    // index = 0: if(1<8): zahlen[0] = 1 -> indexLinks++ -> indexLinks = 1
    // index = 1: if(23<8): zahlen[1] = 8 -> indexRechts-- -> indexRechts = 3
    // index = 2: if(23<7): zahlen[2] = 7 -> indexRechts-- -> indexRechts = 2
    // index = 3: if(23<42): zahlen[3] = 23 -> indexLinks++ -> indexLinks = 2
    // index = 4: if(42>42): zahlen[4] = 42 -> indexRechts-- -> indexRechts = 1
    for (index = linkeGrenze; index <= rechteGrenze; index++) {
      if (hilfsFeld[indexLinks] < hilfsFeld[indexRechts]) {
        zahlen[index] = hilfsFeld[indexLinks++];
      } else {
        zahlen[index] = hilfsFeld[indexRechts--];
      }
    }
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Mergesort-Algorithmus.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public static int[] sortiere(int[] zahlen) {
    int hilfsFeld[] = new int[zahlen.length];
    sortiereRekursiv(zahlen, 0, zahlen.length - 1, hilfsFeld);
    return zahlen;
  }

  /**
   * Kleine Hilfsmethode um den Algorithmus besser zu verstehen.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   */
  private static void zeigeZahlen(int[] zahlen) {
    String output = "";
    for (int i = 0; i < zahlen.length; i++) {
      output = output + zahlen[i] + " ";
    }
    System.out.println(output);
  }

  /**
   * Der Vereinigungsprozess in einer eigenen Funktion ausgelagert. Um den
   * Vereinigungsprozess (merge) besser verstehen zu können.
   *
   * @param hilfsFeld Die Zahlen müssen in der linken Hälfte anderes sortiert
   *                  sein, wie in der rechten Hälfte z.B.: [13, 12, 78, 15], [23,
   *                  42, 1], [1, 23, 42, 7, 5], [2, 4, 3, 1]
   *
   * @return Das sortierte Zahlenfeld.
   */
  private static int[] vereinigen(int[] hilfsFeld) {
    int[] zahlen = new int[hilfsFeld.length];
    int i = 0;
    int j = hilfsFeld.length - 1;
    for (int k = 0; k < hilfsFeld.length; k++) {
      if (hilfsFeld[i] < hilfsFeld[j]) {
        zahlen[k] = hilfsFeld[i++];
      } else {
        zahlen[k] = hilfsFeld[j--];
      }
    }
    return zahlen;
  }

  public static void main(String[] args) {
    int[] zahlen = { 42, 23, 1, 7, 5, 3, 12, 78, 15 };
    sortiere(zahlen);
    zeigeZahlen(zahlen);

    // 13, 12, 78, 15
    // 23, 42, 1
    // 1, 23, 42, 7, 5,
    // 2, 4, 3, 1
    zeigeZahlen(vereinigen(new int[] { 2, 4, 3, 1 }));
  }
}
