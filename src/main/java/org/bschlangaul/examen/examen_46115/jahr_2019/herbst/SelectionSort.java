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

  public static void main(String[] args) {
    selectionSort(new int[] { 27, 32, 3, 6, 17, 44, 42, 29, 8, 14 });
  }

}
