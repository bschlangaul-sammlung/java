package org.bschlangaul.examen.examen_46115.jahr_2017.fruehjahr;

public class BubbleSort {

  public static void swap(int[] array, int index1, int index2) {
    int tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }

  public static void bubblesort(int[] array) {
    boolean swapped;
    do {
      swapped = false;
      for (int i = 0; i < array.length - 1; i++) {
        if (array[i] > array[i + 1]) {
          swap(array, i, i + 1);
          swapped = true;
        }
      }
    } while (swapped);
  }
}
