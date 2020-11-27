package org.bschlangaul.aufgaben.aud.pu_1;

public class Rekursion {

  public static int fak(int n) {
    if (n == 1) {
      return 1;
    }
    return n * fak(n - 1);
  }

  public static int fib(int n) {
    if (n <= 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {
    System.out.println(fak(6));
    System.out.println(fib(6));
  }
}
