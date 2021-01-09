package org.bschlangaul.examen.examen_46115.jahr_2017.herbst;

public class InsertionSort {

  static void sortierenDurchEinfuegen(String[] a) {
    // Hilfsvariable:
    String tmp;
    for (int i = 1; i < a.length; i++) {
      tmp = a[i];
      int j = i;
      while (j > 0 && a[j - 1].compareTo(tmp) >= 1) {
        a[j] = a[j - 1];
        j = j - 1;
      }
      a[j] = tmp;
    }
  }

  public static void main(String[] args) {
    String[] a = { "L", "A", "B", "F", "A", "B" };
    sortierenDurchEinfuegen(a);
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
  }
}
