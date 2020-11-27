package org.bschlangaul.sortier;

/**
 * Wie Pseudo-Code auf https://de.wikipedia.org/wiki/Quicksort
 * https://javabeginners.de/Algorithmen/Sortieralgorithmen/Quicksort.php
 */
public class QuickSortMitte {

  static int zerlege(int[] zahlen, int links, int rechts) {
    int i, j;
    int pivotWert = zahlen[(links + rechts) / 2];
    i = links - 1;
    j = rechts + 1;
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

  static int[] sortiereRekursiv(int[] zahlen, int links, int rechts) {
    int pivotPositionNeu;
    if (links < rechts) {
      pivotPositionNeu = zerlege(zahlen, links, rechts);
      sortiereRekursiv(zahlen, links, pivotPositionNeu);
      sortiereRekursiv(zahlen, pivotPositionNeu + 1, rechts);
    }
    return zahlen;
  }

  static int[] sortiere(int[] zahlen) {
    sortiereRekursiv(zahlen, 0, zahlen.length - 1);
    return zahlen;
  }

  public static void main(String[] args) {
    int[] zahlen = { 16, 23, 14, 7, 21, 20, 6, 1, 17, 13, 12, 9, 3, 19 };
    sortiere(zahlen);
    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
  }
}
