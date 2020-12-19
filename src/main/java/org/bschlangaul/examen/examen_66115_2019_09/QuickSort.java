package org.bschlangaul.examen.examen_66115_2019_09;

import org.bschlangaul.helfer.Konsole;

public class QuickSort {
  public static void quicksort(int[] A, int links, int rechts) {
    System.out.println("quick");
    int i = links;
    int j = rechts;
    if (j > i) {
      int x = A[links];
      do {
        while (A[i] < x) {
          i = i + 1;
        }
        while (A[j] > x) {
          j = j - 1;
        }
        if (i <= j) {
          int tmp = A[i];
          A[i] = A[j];
          A[j] = tmp;
          i = i + 1;
          j = j - 1;
        }
        // Java verfügt über keine do-until Schleife.
        // Wir verwenden eine do-while-Schelfe mit einem umgedrehten Test
        // unit i > j -> while (i <= j)
      } while (i <= j);
      quicksort(A, links, j);
      quicksort(A, i, rechts);
    }
  }

  public static void main(String[] args) {
    //int[] A = new int[] { 27, 32, 3, 6, 17, 44, 42 };
    //int[] A = new int[] { 1, 2, 3, 4, 5, 6, 7 };
    //int[] A = new int[] { 1, 2, 3, 4 }; // 7
    //int[] A = new int[] { 4, 3, 2, 1 }; // 7
    int[] A = new int[] { 3, 4, 1, 2 }; // 7
    Konsole.zeigeZahlenFeld(A);
    quicksort(A, 0, 3);
    Konsole.zeigeZahlenFeld(A);
  }
}
