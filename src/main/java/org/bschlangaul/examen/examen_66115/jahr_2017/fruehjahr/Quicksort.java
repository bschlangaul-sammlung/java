package org.bschlangaul.examen.examen_66115.jahr_2017.fruehjahr;

public class Quicksort {

  public static void swap(String[] array, int index1, int index2) {
    String tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }

  public static int partition(String[] array, int first, int last) {
    int pivotIndex = (last + first) / 2;
    String pivotValue = array[pivotIndex];
    int pivotIndexFinal = first;
    swap(array, pivotIndex, last);
    for (int i = first; i < last; i++) {
      if (array[i].compareTo(pivotValue) < 0) {
        swap(array, i, pivotIndexFinal);
        pivotIndexFinal++;
      }
    }
    swap(array, last, pivotIndexFinal);
    return pivotIndexFinal;
  }

  public static void sort(String[] array, int first, int last) {
    if (first < last) {
      int pivotIndex = partition(array, first, last);
      sort(array, first, pivotIndex - 1);
      sort(array, pivotIndex + 1, last);
    }
  }

  public static void main(String[] args) {
    String[] array = new String[] { "de", "com", "uk", "org", "co", "net", "fr", "ee" };
    sort(array, 0, array.length - 1);
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
  }
}
