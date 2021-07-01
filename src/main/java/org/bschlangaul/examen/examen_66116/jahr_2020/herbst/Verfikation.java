package org.bschlangaul.examen.examen_66116.jahr_2020.herbst;

public class Verfikation {

  public static void methode(int n, int m) {
    int[] a = new int[n];

    a[4] = 10;
    int i = -1;
    // (1)
    int j = 0;
    // (2)
    while (i == -1 && j < n) // (3)
    { // (4)
      if (a[j] == m) {
        // (5)
        i = j;
        // (6)
      } else {
        // (7)
        j = j + 1;
        // (8)
      }
      // (9)
      System.out.println(j);
    }
  }

  public static void main(String[] args) {
    methode(10, 10);
  }
}
