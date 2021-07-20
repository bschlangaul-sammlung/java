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
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * (nach Pseudo-Code auf
   * <a href="https://de.wikipedia.org/wiki/Quicksort">Wikipediaseite zum
   * Quicksort-Algortihmus</a> und Code-Beispiels auf <a href=
   * "https://javabeginners.de/Algorithmen/Sortieralgorithmen/Quicksort.php">javabeginners.de</a>)
   *
   * Ohne Hin- und Herkopieren des Pivot-Elements wie bei Saake. Der Pivot-Wert
   * wird in der „zerlegen“-Methode festgelegt.
   */

  /**
   * Zerlege das Zahlen-Feld.
   *
   * @param zahlen       Ein Feld mit Zahlen, das zerlegt werden soll.
   * @param untereGrenze Die Index-Nummer ab dem das Zahlen-Feld zerlegt werden
   *                     soll.
   * @param obereGrenze  Die Index-Nummer bis zu dem das Zahlen-Feld zerlegt
   *                     werden soll.
   *
   * @return Die Index-Nummer, an dem das Feld zerlegt werden soll.
   */
  static int zerlege(int[] zahlen, int untereGrenze, int obereGrenze) {
    int i, j;
    int pivotWert = zahlen[(untereGrenze + obereGrenze) / 2];
    i = untereGrenze - 1;
    j = obereGrenze + 1;
    while (true) {
      do {
        i++;
      } while (zahlen[i] < pivotWert);

      do {
        j--;
      } while (zahlen[j] > pivotWert);

      if (i < j) {
        Helfer.vertausche(zahlen, i, j);
      } else {
        return j;
      }
    }
  }


  /**
   * Hilfsmethode zum Zerlegen der Folge. Diese Methode heißt im Englischen auch
   * oft „partition“.
   *
   * @param zahlen     Ein Feld mit Zahlen, das sortiert werden soll.
   * @param links      Die Index-Nummer der unteren Grenze.
   * @param rechts     Die Index-Nummer der oberen Grenze.
   * @param pivotIndex Die Index-Nummer des ausgewählten Pivot-Elements.
   *
   * @return Die endgültige Index-Nummer des Pivot-Elements.
   */
  static int zerlege(int[] zahlen, int links, int rechts, int pivotIndex) {
    int pivotIndexEndgültig = links;
    int pivotWert = zahlen[pivotIndex];
    // Pivot-Element an das Ende verschieben
    Helfer.vertausche(zahlen, pivotIndex, rechts);
    for (int i = links; i < rechts; i++) {
      if (zahlen[i] <= pivotWert) {
        Helfer.vertausche(zahlen, pivotIndexEndgültig, i);
        pivotIndexEndgültig++;
      }
    }
    // Pivot-Element an die richtige Position kopieren
    Helfer.vertausche(zahlen, rechts, pivotIndexEndgültig);
    // neue Pivot-Position zurückgeben
    return pivotIndexEndgültig;
  }

  /**
   * Hilfsmethode zum rekursiven Sortieren
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   * @param links  Die Index-Nummer der unteren Grenze.
   * @param rechts Die Index-Nummer der oberen Grenze.
   */
  static void sortiereRekursiv(int[] zahlen, int links, int rechts) {
    // Pivot-Element bestimmen
    int pivotIndex = (links + rechts) / 2;
    if (rechts > links) {
      // Feld zerlegen
      int pivotIndexEndgültig = zerlege(zahlen, links, rechts, pivotIndex);
      // und zerlegeen sortieren
      sortiereRekursiv(zahlen, links, pivotIndexEndgültig - 1);
      sortiereRekursiv(zahlen, pivotIndexEndgültig + 1, rechts);
    }
  }

  /**
   * Sortiere ein Zahlen-Feld mit Hilfe des Quicksort-Algorithmus.
   *
   * @param zahlen Ein Feld mit Zahlen, das sortiert werden soll.
   *
   * @return Das sortierte Zahlenfeld.
   */
  public static int[] sortiere(int[] zahlen) {
    sortiereRekursiv(zahlen, 0, zahlen.length - 1);
    return zahlen;
  }

  /**
   * Nach dem Pseudo Code Staatsexamen/66115/2019/09/Thema-1/Aufgabe-5.tex
   * @param zahlen
   * @param links
   * @param rechts
   * @return
   */
  private static int[] sortiereIterativ(int[] zahlen, int links, int rechts) {
    int i = links;
    int j = rechts;
    if (j > i) {
      int x = zahlen[links];
      do {
        while (zahlen[i] < x) {
          i++;
        }
        while (zahlen[j] > x) {
          j--;
        }
        if (i <= j) {
          Helfer.vertausche(zahlen, i, j);
          i++;
          j--;
        }
      } while (i <= j);
      sortiereIterativ(zahlen, links, j);
      sortiereIterativ(zahlen, i, rechts);
    }
    return zahlen;
  }

  public static int[] sortiereIterativ(int[] zahlen) {
    return sortiereIterativ(zahlen, 0, zahlen.length - 1);
  }

  static void zeigeZwischenstand(int pivotWert, int links, int rechts) {
    System.out.println(String.format("Pivot-Wert: %s; untere Grenze: %s; obere Grenze: %s", pivotWert, links, rechts));
  }

  static void zeigeZahlen(int[] zahlen, int links, int rechts) {
    for (int i = links; i <= rechts; i++) {
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
