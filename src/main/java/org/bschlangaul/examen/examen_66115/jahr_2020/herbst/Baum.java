package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

public class Baum {
  public static int[] A;

  public static boolean magic(int i, int n) {
    System.out.println(String.format("i: %s n: %s", i, n));
    if (i > (n - 2) / 2) {
      return true;
    }

    if (A[i] <= A[2 * i + 1] && A[i] <= A[2 * i + 2] && magic(2 * i + 1, n) && magic(2 * i + 2, n)) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    A = new int[] { 2, 4, 6, 14 };
    System.out.println(magic(0, 3));

    A = new int[] { 2, 4, 6, 14, 12, 10, 8, 22, 20, 18, 16 };
    System.out.println(magic(0, 10));
  }
}
