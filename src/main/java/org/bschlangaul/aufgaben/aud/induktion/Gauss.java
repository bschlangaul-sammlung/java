package org.bschlangaul.aufgaben.aud.induktion;

/**
 * gaussian sum formula
 */
public class Gauss {

  public static int sum(int n) {
    if (n <= 0) {
      return 0;
    }

    return n + sum(n - 1);
  }

}
