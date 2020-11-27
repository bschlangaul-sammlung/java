package org.bschlangaul.aufgaben.sosy.ab_8;

public class VollstaendigeInduktion {

  static double sumOfSquares(long n) {
    if (n == 0)
      return 0;
    else
      return n * n + sumOfSquares(n - 1);
  }

  static double sumOfSquaresReal(long n) {
    return (n * (n + 1) * (2 * n + 1)) / 6;
  }

  static double sumOfSquaresNPlus(long n) {
    return (2 * Math.pow(n, 3) + 9 * Math.pow(n, 2) + (13 * n) + 6) / 6;
  }

  public static void main(String[] args) {
    System.out.println(sumOfSquares(3));
    System.out.println(sumOfSquares(4));
    System.out.println(sumOfSquaresNPlus(2));
    System.out.println(sumOfSquaresNPlus(3));
  }
}
