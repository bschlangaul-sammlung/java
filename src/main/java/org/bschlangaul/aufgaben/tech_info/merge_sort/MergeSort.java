package org.bschlangaul.aufgaben.tech_info.merge_sort;

public interface MergeSort {

  /**
   * @param arr Eingabearray, welches rekursiv mittels des mergeSort-Algorithmus
   *            sortiert werden soll. Die Sortierung muss NICHT in place sein; es
   *            darf also die copyOfRange-Methode von Arrays verwendet werden!
   */
  public void seqMergeSort(int[] arr);

  /**
   * @param arr         zu sortierendes Eingabearray
   * @param threadCount Anzahl der verfügbaren Threads
   */
  public void parallelMergeSort(int[] arr, int threadCount);

  /**
   * @param left  Eingabearray
   * @param right Eingabearray
   * @param arr   Ausgabearray mit passender Laenge
   */
  public void merge(int[] left, int[] right, int[] arr);

}
