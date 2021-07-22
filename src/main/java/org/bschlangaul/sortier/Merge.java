package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Mergesort-Algorithmus. (Nach Saake
 * Seite 134)
 */
public class Merge extends Sortieralgorithmus {

  /**
   * Hilfsmethode für rekursives Sortieren durch Mischen
   *
   * @param links     Die Index-Nummer, ab der das Zahlen-Feld sortiert werden
   *                  soll.
   * @param rechts    Die Index-Nummer, bis zu der das Zahlen-Feld sortiert werden
   *                  soll.
   * @param hilfsFeld Eine Hilfsfeld, in dem die Zahlen temporär
   *                  zwischengespeichert werden.
   */
  private void sortiereRekursiv(int links, int rechts, int[] hilfsFeld) {
    // Wenn die rechte Grenze gleich (oder sogar kleiner) als die linke Grenze ist,
    // tue nichts.
    if (rechts <= links)
      return;

    // Zähler für diverse for-Schleifen deklarieren.
    int indexLinks, indexRechts, index;
    // zu sortierendes Feld teilen
    int mitte = (rechts + links) / 2;
    // Teilfelder sortieren
    sortiereRekursiv(links, mitte, hilfsFeld);
    sortiereRekursiv(mitte + 1, rechts, hilfsFeld);
    // Hilfsfeld aufbauen
    // Linke Hälfte übernehmen, z. B. aufsteigende Sortierung
    for (index = links; index <= mitte; index++) {
      // hilfsFeld[0-2]: 1 23 42
      hilfsFeld[index] = zahlen[index];
    }

    // Rechte Hälfte umdrehen, z. B. absteigende Sortierung
    for (index = mitte; index < rechts; index++) {
      // hilfsFeld[3-4]: 8 7
      hilfsFeld[rechts + mitte - index] = zahlen[index + 1];
    }

    // Ergebnisse mischen über Hilfsfeld
    indexLinks = links;
    indexRechts = rechts;
    // Beispiel:
    // indexLinks = 0, indexRechts = 4
    // hilfsFeld: 1 23 42 7 8
    // index = 0: if(1<8): zahlen[0] = 1 -> indexLinks++ -> indexLinks = 1
    // index = 1: if(23<8): zahlen[1] = 8 -> indexRechts-- -> indexRechts = 3
    // index = 2: if(23<7): zahlen[2] = 7 -> indexRechts-- -> indexRechts = 2
    // index = 3: if(23<42): zahlen[3] = 23 -> indexLinks++ -> indexLinks = 2
    // index = 4: if(42>42): zahlen[4] = 42 -> indexRechts-- -> indexRechts = 1
    for (index = links; index <= rechts; index++) {
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
  public int[] sortiere() {
    int hilfsFeld[] = new int[zahlen.length];
    sortiereRekursiv(0, zahlen.length - 1, hilfsFeld);
    return zahlen;
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
  @SuppressWarnings({ "unused" })
  private int[] vereinigen(int[] hilfsFeld) {
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

  }
}
