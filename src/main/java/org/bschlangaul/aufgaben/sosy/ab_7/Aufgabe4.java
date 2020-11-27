package org.bschlangaul.aufgaben.sosy.ab_7;

public class Aufgabe4 {
  public static int[] sort(int[] array) {
    boolean swapped;
    int swapTmp;
    int[] newArray = (int[]) array.clone();
    do {
      swapped = false;
      for (int index = 0; index < newArray.length - 1; index++) {
        if (newArray[index] > newArray[index + 1]) {
          swapTmp = newArray[index];
          newArray[index] = newArray[index + 1];
          newArray[index + 1] = swapTmp;
          swapped = true;
        }
      }
    } while (swapped);
    return newArray;
  }
}
