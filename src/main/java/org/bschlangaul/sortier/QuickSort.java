package org.bschlangaul.sortier;

/**
 * Nach Saake Seite 138
 */
public class QuickSort {

  // Hilfsmethode zum Zerlegen der Folge
  static int zerlege(int[] zahlen, int untereGrenze, int obereGrenze, int pivotPosition) {
    int pivotPositionNeu = untereGrenze;
    int pivotWert = zahlen[pivotPosition];
    // Pivot-Element an das Ende verschieben
    Helfer.vertausche(zahlen, pivotPosition, obereGrenze);
    for (int i = untereGrenze; i < obereGrenze; i++) {
      if (zahlen[i] <= pivotWert) {
        Helfer.vertausche(zahlen, pivotPositionNeu++, i);
      }
    }
    // Pivot-Element an die richtige Position kopieren
    Helfer.vertausche(zahlen, obereGrenze, pivotPositionNeu);
    // neue Pivot-Position zurückgeben
    return pivotPositionNeu;
  }

  // Hilfsmethode zum rekursiven Sortieren
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
    //int[] zahlen = { 42, 23, 1, 7, 5, 3, 12, 78, 15 };
    int[] zahlen = { 8, 2, 1, 5, 9, 7, 3 };

    sortiere(zahlen);

    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
