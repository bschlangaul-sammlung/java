package org.bschlangaul.sortier;

/**
 * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus. (Nach Saake
 * Seite 138)
 *
 * Der Algorithmus wählt das mittlere Element aus. In der „zerlegen“ Methode wird
 * das Pivot-Element temporär an die obere Grenze verschoben.
 */
public class QuickSort {

  /**
   * Hilfsmethode zum Zerlegen der Folge. Diese Methode heißt im Englischen auch
   * oft „partition“.
   *
   * @param zahlen        Ein Feld mit Zahlen, das sortiert werden soll.
   * @param untereGrenze  Die Index-Nummer der unteren Grenze.
   * @param obereGrenze   Die Index-Nummer der oberen Grenze.
   * @param pivotPosition Die Index-Nummer der Pivot-Position.
   *
   * @return Die Index-Nummer der neuen Pivot-Position
   */
  static int zerlege(int[] zahlen, int untereGrenze, int obereGrenze, int pivotPosition) {
    int pivotPositionNeu = untereGrenze;
    int pivotWert = zahlen[pivotPosition];
    // Pivot-Element an das Ende verschieben
    Helfer.vertausche(zahlen, pivotPosition, obereGrenze);
    for (int i = untereGrenze; i < obereGrenze; i++) {
      if (zahlen[i] <= pivotWert) {
        Helfer.vertausche(zahlen, pivotPositionNeu, i);
        pivotPositionNeu++;
      }
    }
    // Pivot-Element an die richtige Position kopieren
    Helfer.vertausche(zahlen, obereGrenze, pivotPositionNeu);
    // neue Pivot-Position zurückgeben
    return pivotPositionNeu;
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
    int pivotPosition = (untereGrenze + obereGrenze) / 2;
    if (obereGrenze > untereGrenze) {
      // Feld zerlegen
      int pivotPositionNeu = zerlege(zahlen, untereGrenze, obereGrenze, pivotPosition);
      // und zerlegeen sortieren
      sortiereRekursiv(zahlen, untereGrenze, pivotPositionNeu - 1);
      sortiereRekursiv(zahlen, pivotPositionNeu + 1, obereGrenze);
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
