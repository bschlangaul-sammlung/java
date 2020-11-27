package org.bschlangaul.aufgaben.aud.pu_3;

public class RekursivesBacktracking {

  public static boolean fill(int n, int[] a) {
    if (n <= 0) {
      return true;
    }
    for (int i = 0; i < a.length - n - 1; i++) {
      int j = i + n + 1; // Zwischen i und j muessen genau n andere Zahlen sein
      if (a[i] == 0 && a[j] == 0) {
        a[i] = a[j] = n;
        if (fill(n - 1, a)) {
          return true;
        }
        a[i] = a[j] = 0;
      }
    }
    return false;
  }

  public static void executeFill(int n) {
    int[] a = new int[n * 2];
    boolean result = fill(n, a);
    System.out.print("fill(" + n + ", []): ");
    if (result) {
      for (int i = 0; i < a.length; i++) {
        System.out.print(a[i] + " ");
      }
    } else {
      System.out.print("false");
    }

    System.out.println();
  }

  public static void main(String[] args) {
    executeFill(0);
    executeFill(1);
    executeFill(2);
    executeFill(3);
    executeFill(4);
    executeFill(5);
    executeFill(6);
    executeFill(7);
    executeFill(8);
    executeFill(9);
    executeFill(10);
    executeFill(11);
  }
}
