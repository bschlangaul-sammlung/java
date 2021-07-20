package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus. (Nach Saake
 * Seite 138)
 *
 * Der Algorithmus wählt das mittlere Element aus. In der „zerlegen“ Methode
 * wird das Pivot-Element temporär an die obere Grenze verschoben.
 */
public class QuickSort {

  /**
   * Hilfsmethode zum Zerlegen der Folge. Diese Methode heißt im Englischen auch
   * oft „partition“.
   *
   * @param zahlen       Ein Feld mit Zahlen, das sortiert werden soll.
   * @param untereGrenze Die Index-Nummer der unteren Grenze.
   * @param obereGrenze  Die Index-Nummer der oberen Grenze.
   * @param pivotIndex   Die Index-Nummer des ausgewählten Pivot-Elements.
   *
   * @return Die endgültige Index-Nummer des Pivot-Elements.
   */
  static int zerlege(int[] zahlen, int untereGrenze, int obereGrenze, int pivotIndex) {
    int pivotIndexEndgültig = untereGrenze;
    int pivotWert = zahlen[pivotIndex];
    // Pivot-Element an das Ende verschieben
    Helfer.vertausche(zahlen, pivotIndex, obereGrenze);
    for (int i = untereGrenze; i < obereGrenze; i++) {
      if (zahlen[i] <= pivotWert) {
        Helfer.vertausche(zahlen, pivotIndexEndgültig, i);
        pivotIndexEndgültig++;
      }
    }
    // Pivot-Element an die richtige Position kopieren
    Helfer.vertausche(zahlen, obereGrenze, pivotIndexEndgültig);
    // neue Pivot-Position zurückgeben
    return pivotIndexEndgültig;
  }

  /**
   * Hilfsmethode zum rekursiven Sortieren
   *
   * @param zahlen       Ein Feld mit Zahlen, das sortiert werden soll.
   * @param untereGrenze Die Index-Nummer der unteren Grenze.
   * @param obereGrenze  Die Index-Nummer der oberen Grenze.
   */
  static void sortiereRekursiv(int[] zahlen, int untereGrenze, int obereGrenze) {
    // Pivot-Element bestimmen
    int pivotIndex = (untereGrenze + obereGrenze) / 2;
    if (obereGrenze > untereGrenze) {
      // Feld zerlegen
      int pivotIndexEndgültig = zerlege(zahlen, untereGrenze, obereGrenze, pivotIndex);
      // und zerlegeen sortieren
      sortiereRekursiv(zahlen, untereGrenze, pivotIndexEndgültig - 1);
      sortiereRekursiv(zahlen, pivotIndexEndgültig + 1, obereGrenze);
    }
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  static int[] sortiere(int[] zahlen) {
    sortiereRekursiv(zahlen, 0, zahlen.length - 1);
    return zahlen;
  }

  static void zeigeZwischenstand(int pivotWert, int untereGrenze, int obereGrenze) {
    System.out.println(
        String.format("Pivot-Wert: %s; untere Grenze: %s; obere Grenze: %s", pivotWert, untereGrenze, obereGrenze));
  }

  static void zeigeZahlen(int[] zahlen, int untereGrenze, int obereGrenze) {
    for (int i = untereGrenze; i <= obereGrenze; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // In der grafischen Darstellung auf Seite 137 in Saake
    // wurden einige Zwischenschritte nicht visualisiert:
    // 8 2 1 5 9 7 3 <- 1. Zeile
    // 2 8 1 3 9 7 5
    // 2 1 8 3 9 7 5
    // 2 1 3 8 9 7 5 <- 2. Zeile
    // 2 1 3 5 9 7 8 <- 3. Zeile
    // int[] zahlen = { 42, 23, 1, 7, 5, 3, 12, 78, 15 };
    int[] zahlen = { 8, 2, 1, 5, 9, 7, 3 };

    sortiere(zahlen);

    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
