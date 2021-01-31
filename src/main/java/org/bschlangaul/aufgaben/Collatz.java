package org.bschlangaul.aufgaben;

public class Collatz {
  public static void main(String[] args) {
    int n = 7;
    while (n != 1) {
      if (n % 2 == 0) {
        n = n / 2;
      } else {
        n = 3 * n + 1;
      }
      System.out.println(n);
    }
  }
}
