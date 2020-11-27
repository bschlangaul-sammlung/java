package org.bschlangaul.aufgaben.sosy.examen_46116_2017_09;

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
