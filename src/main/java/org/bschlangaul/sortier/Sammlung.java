package org.bschlangaul.sortier;

/**
 * Eine Sammlung von Sortier-Algorithmen in einer Klasse ohne Kommentare zum
 * lernen.
 */
public class Sammlung {

  static void vertausche(int[] zahlen, int index1, int index2) {
    int tmp = zahlen[index1];
    zahlen[index1] = zahlen[index2];
    zahlen[index2] = tmp;
  }

  static int[] bubblesort(int[] zahlen) {
    boolean getauscht;
    do {
      getauscht = false;
      for (int i = 0; i < zahlen.length - 1; i++) {
        if (zahlen[i] > zahlen[i + 1]) {
          vertausche(zahlen, i, i + 1);
          getauscht = true;
        }
      }
    } while (getauscht);
    return zahlen;
  }

  static int[] insertionsort(int[] zahlen) {
    for (int i = 1; i < zahlen.length; i++) {
      int markierung = zahlen[i];
      int j = i;
      while (j >= 1 && zahlen[j - 1] > markierung) {
        zahlen[j] = zahlen[j - 1];
        j--;
      }
      zahlen[j] = markierung;
    }
    return zahlen;
  }

  public static int[] selectionsort(int[] zahlen) {
    int markierung = zahlen.length - 1;
    while (markierung >= 0) {
      int max = 0;
      for (int i = 1; i <= markierung; i++) {
        if (zahlen[i] > zahlen[max]) {
          max = i;
        }
      }
      vertausche(zahlen, markierung, max);
      markierung--;
    }
    return zahlen;
  }

  static int quicksortZerlege(int[] zahlen, int untereGrenze, int obereGrenze, int pivotPosition) {
    int pivotPositionNeu = untereGrenze;
    int pivotWert = zahlen[pivotPosition];
    Helfer.vertausche(zahlen, pivotPosition, obereGrenze);
    for (int i = untereGrenze; i < obereGrenze; i++) {
      if (zahlen[i] <= pivotWert) {
        Helfer.vertausche(zahlen, pivotPositionNeu++, i);
      }
    }
    Helfer.vertausche(zahlen, obereGrenze, pivotPositionNeu);
    return pivotPositionNeu;
  }

  static void quicksortRekursiv(int[] zahlen, int untereGrenze, int obereGrenze) {
    int pivotPosition = (untereGrenze + obereGrenze) / 2;
    if (obereGrenze > untereGrenze) {
      int pivotPositionNeu = quicksortZerlege(zahlen, untereGrenze, obereGrenze, pivotPosition);
      quicksortRekursiv(zahlen, untereGrenze, pivotPositionNeu - 1);
      quicksortRekursiv(zahlen, pivotPositionNeu + 1, obereGrenze);
    }
  }

  static void quicksort(int[] zahlen) {
    quicksortRekursiv(zahlen, 0, zahlen.length - 1);
  }

  private static void mergesortRekursiv(int[] zahlen, int linkeGrenze, int rechteGrenze, int[] hilfsFeld) {
    if (rechteGrenze <= linkeGrenze)
      return;
    int indexLinks, indexRechts, index;
    int mitte = (rechteGrenze + linkeGrenze) / 2;
    mergesortRekursiv(zahlen, linkeGrenze, mitte, hilfsFeld);
    mergesortRekursiv(zahlen, mitte + 1, rechteGrenze, hilfsFeld);
    for (index = linkeGrenze; index <= mitte; index++) {
      hilfsFeld[index] = zahlen[index];
    }
    for (index = mitte; index < rechteGrenze; index++) {
      hilfsFeld[rechteGrenze + mitte - index] = zahlen[index + 1];
    }
    indexLinks = linkeGrenze;
    indexRechts = rechteGrenze;
    for (index = linkeGrenze; index <= rechteGrenze; index++) {
      if (hilfsFeld[indexLinks] < hilfsFeld[indexRechts]) {
        zahlen[index] = hilfsFeld[indexLinks++];
      } else {
        zahlen[index] = hilfsFeld[indexRechts--];
      }
    }
  }

  public static int[] mergesort(int[] zahlen) {
    int hilfsFeld[] = new int[zahlen.length];
    mergesortRekursiv(zahlen, 0, zahlen.length - 1, hilfsFeld);
    return zahlen;
  }
}
