package org.bschlangaul.examen.examen_46116.jahr_2017.herbst;

public class BubbleSort {
  void bubblesort(int[] array, int len) {
    for (int i = 0; i < len - 1; i++) {   // 1
      for (int j = 0; j < len - 1; j++) { // 2
        if (array[j] > array[j + 1]) {    // 3
          int temp = array[j];            // 4
          array[j] = array[j + 1];        // 5
          array[j + 1] = temp;            // 6
        }
      }
    }
  }
}
