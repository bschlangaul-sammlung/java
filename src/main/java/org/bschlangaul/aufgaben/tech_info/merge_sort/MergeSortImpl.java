package org.bschlangaul.aufgaben.tech_info.merge_sort;

import java.util.Arrays;

public class MergeSortImpl implements MergeSort {

  /**
   * @param arr Das zu sortierende Array.
   */
  public void seqMergeSort(int[] arr) {
    if (arr.length < 2) {
      return;
    }
    int mitte = arr.length / 2;
    int[] links = Arrays.copyOfRange(arr, 0, mitte);
    int[] rechts = Arrays.copyOfRange(arr, mitte, arr.length);

    seqMergeSort(links);
    seqMergeSort(rechts);
    merge(links, rechts, arr);
  }

  /**
   * @param arr         Das zu sortierende Array.
   * @param threadCount Anzahl der Threads; bei Initialaufruf eine Zweierpotenz
   */
  public void parallelMergeSort(int[] arr, int threadCount) {
    // TODO
  }

  /**
   * @param left  Eingabearray
   * @param right Eingabearray
   * @param arr   Ausgabearray mit passender Laenge
   */
  public void merge(int[] left, int[] right, int[] arr) {
    int i1 = 0;
    int i2 = 0;
    for (int i = 0; i < arr.length; i++) {
      if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
        arr[i] = left[i1];
        i1++;
      } else {
        arr[i] = right[i2];
        i2++;
      }
    }
  }
}
