package org.bschlangaul.aufgaben.sosy.ab_7;

public class Aufgabe3 {
  int log(int a) {
    int x = a;
    int z = 0;
    while (x > 1) {
      if (x % 2 == 0) {
        z++;
        x /= 2;
      } else {
        x--;
      }
    }
    return z;
  }

  public static final String RESET = "\u001B[0m";
  public static final String ROT = "\u001B[31m";
  public static final String GRÜN = "\u001B[32m";

  static int logNodes(int a) {
    System.out.println("\nInput: " + a);
    System.out.print("S");
    int x = a;
    int z = 0;
    System.out.print(1);
    System.out.print(2);
    while (x > 1) {
      System.out.print(3);
      if (x % 2 == 0) {
        System.out.print(ROT + 4 + RESET);
        z++;
        // System.out.println("\nX vor der Teilung mit 2: " + x);
        x /= 2; // x = x / 2; z. B.: 49 / 2 = 24
        // System.out.println("\nX nach der Teilung mit 2: " + x);
      } else {
        System.out.print(GRÜN + 5 + RESET);
        x--;
      }
      System.out.print(6);
      System.out.print(7);
      System.out.print(2);
    }
    System.out.println("E");
    System.out.println("Output: " + z);
    return z;
  }

  public static void main(String[] args) {
    for (int i = 0; i <= 100; i++) {
      logNodes(i);
    }
  }
}
