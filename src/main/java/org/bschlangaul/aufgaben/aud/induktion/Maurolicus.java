package org.bschlangaul.aufgaben.aud.induktion;

/**
 * Sum of odd numbers / Summe ungerader Zahlen
 */
public class Maurolicus {

  public static int oddSum(int n) {
    if (n <= 0) {
      return 0;
    }
    return 2 * n - 1 + oddSum(n - 1);
  }
}
