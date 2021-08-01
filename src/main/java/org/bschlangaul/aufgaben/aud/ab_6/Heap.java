package org.bschlangaul.aufgaben.aud.ab_6;

public class Heap {

  static int NO_VALUE = Integer.MIN_VALUE;

  static void heapify(int a[], int i, int last) {
    int smallest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    if (l <= last && a[l] != NO_VALUE && a[l] < a[smallest]) {
      smallest = l;
    }

    if (r <= last && a[r] != NO_VALUE && a[r] < a[smallest]) {
      smallest = r;
    }

    if (smallest != i) {
      int swap = a[i];
      a[i] = a[smallest];
      a[smallest] = swap;
      heapify(a, smallest, last);
    }
  }

  static int removeMin(int a[]) {
    int result = a[0];
    int last = a.length - 1;
    while (last > 0 && a[last] == NO_VALUE) {
      last--;
    }
    a[0] = a[last];
    a[last] = NO_VALUE;
    heapify(a, 0, last);
    return result;
  }

  static void buildHeap(int a[]) {
    int start = (a.length / 2) - 1;
    for (int i = start; i >= 0; i--) {
      heapify(a, i, a.length - 1);
    }
  }

  static void printHeap(int a[]) {
    for (int i = 0; i < a.length; ++i) {
      if (a[i] != NO_VALUE) {
        System.out.print(a[i] + " ");
      }
    }
    System.out.println();
  }

  public static void main(String args[]) {
    int a[] = { 7, 9, 11, 4, 1 };
    buildHeap(a);

    printHeap(a);

    removeMin(a);
    printHeap(a);
    removeMin(a);
    printHeap(a);
    removeMin(a);
    printHeap(a);
    removeMin(a);
    printHeap(a);
    removeMin(a);
    printHeap(a);
  }
}
