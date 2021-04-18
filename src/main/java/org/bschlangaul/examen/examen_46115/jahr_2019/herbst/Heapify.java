package org.bschlangaul.examen.examen_46115.jahr_2019.herbst;

import org.bschlangaul.helfer.Konsole;

/**
 * Nach Pseudocode nach
 * https://www.oreilly.com/library/view/algorithms-in-a/9780596516246/ch04s06.html
 */
public class Heapify {

  public static void buildHeap(int a[]) {
    int n = a.length;
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(a, i, n);
    }
  }

  public static void heapify(int a[], int index, int max) {
    int left = 2 * index + 1;
    int right = 2 * index + 2;
    int smallest;

    if (left < max && a[left] < a[index]) {
      smallest = left;
    } else {
      smallest = index;
    }

    if (right < max && a[right] < a[smallest]) {
      smallest = right;
    }

    if (smallest != index) {
      int tmp = a[index];
      a[index] = a[smallest];
      a[smallest] = tmp;
      heapify(a, smallest, max);
    }
  }

  public static void main(String[] args) {
    int[] a = new int[] { 5, 3, 16, 2, 10, 14 };
    buildHeap(a);
    Konsole.zeigeZahlenFeld(a); // 2 3 14 5 10 16
  }

}
