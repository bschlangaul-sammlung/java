package org.bschlangaul.examen.examen_46115.jahr_2019.herbst;

import static org.bschlangaul.helfer.Konsole.zeigeZahlenFeld;

public class SelectionSort {

  public static void selectionSort(int[] A) {
    int smallest, tmp;

    for (int j = 0; j < A.length - 1; j++) {
      System.out.println("\nj = " + (j + 1));
      smallest = j;
      for (int i = j + 1; i < A.length; i++) {
        if (A[i] < A[smallest]) {
          smallest = i;
          System.out.println(smallest + 1);
        }
      }
      tmp = A[j];
      A[j] = A[smallest];
      A[smallest] = tmp;
      zeigeZahlenFeld(A);
    }
  }

  public static void rekSelectionSort(int[] A, int n, int index) {
    int k, tmp;

    if (index == n - 1) {
      return;
    }
    k = minIndex(A, index, n);
    if (k != index) {
      tmp = A[k];
      A[k] = A[index];
      A[index] = tmp;
    }
    rekSelectionSort(A, n, index + 1);
  }

  public static int minIndex(int[] A, int x, int y) {
    int smallest = x;
    for (int i = x; i < y; i++) {
      if (A[i] < A[smallest]) {
        smallest = i;
      }
    }
    return smallest;
  }

  public static void main(String[] args) {
    int[] A = new int[] { 27, 32, 3, 6, 17, 44, 42, 29, 8, 14 };
    selectionSort(A);

    A = new int[] { 27, 32, 3, 6, 17, 44, 42, 29, 8, 14 };
    rekSelectionSort(A, A.length, 0);
    zeigeZahlenFeld(A);
  }

}
