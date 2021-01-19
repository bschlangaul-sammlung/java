package org.bschlangaul.aufgaben.tech_info.merge_sort;

import java.util.Arrays;

public class MergeSortImpl implements MergeSort {
  // Verbessert nach Email vom Tue, 19 Jan 2021 16:04:09 +0100
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

  public class ParallelMergeSort extends Thread {
    int[] arr;
    int threadCount;

    public ParallelMergeSort(int[] arr, int threadCount) {
      this.arr = arr;
      this.threadCount = threadCount;
    }

    public void run() {
      parallelMergeSort(arr, threadCount);
    }
  }

  private boolean istZweierPotzenz(int anzahl) {
    if (anzahl == 0)
      return false;

    while (anzahl != 1) {
      if (anzahl % 2 != 0)
        return false;
      anzahl = anzahl / 2;
    }
    return true;
  }

  /**
   * @param arr         Das zu sortierende Array.
   * @param threadCount Anzahl der Threads; bei Initialaufruf eine Zweierpotenz
   */
  public void parallelMergeSort(int[] arr, int threadCount) {
    if (!istZweierPotzenz(threadCount)) {
      System.out.println("Bitte gib eine 2er Potenz als Threadanzahl an!");
      return;
    }
    int mitte = arr.length / 2;
    int[] links = Arrays.copyOfRange(arr, 0, mitte);
    int[] rechts = Arrays.copyOfRange(arr, mitte, arr.length);

    if (threadCount > 1) {
      try {
        Thread threadLinks = new ParallelMergeSort(links, threadCount / 2);
        Thread threadRechts = new ParallelMergeSort(rechts, threadCount / 2);
        threadLinks.start();
        threadRechts.start();
        threadLinks.join();
        threadRechts.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      merge(links, rechts, arr);
    } else {
      seqMergeSort(arr);
    }
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
