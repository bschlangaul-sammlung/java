package org.bschlangaul.aufgaben.sosy.ab_8;

public class WpKalkuel {
  public int f(int x, int y) {
    /* P */ x = 2 * x + 1 + x * x;
    y += 7;
    if (x > 196) {
      y = 2 * y;
    } else {
      y -= 8;
      x *= 2;
    } /* Q */
    return x + y;
  }

  public static boolean fMitNachbedingung(int x, int y) {
    /* P */ x = 2 * x + 1 + x * x;
    y += 7;
    if (x > 196) {
      y = 2 * y;
    } else {
      y -= 8;
      x *= 2;
    } /* Q */
    return testeNachbedingung(x, y);
  }

  public static boolean testeNachbedingung(int x, int y) {
    if (x >= 8 && y % 2 == 1)
      return true;
    else
      return false;
  }

  public static void testeY(int y) {
    for (int x = -30; x <= 30; x++) {
      boolean ergebnis = fMitNachbedingung(x, y);
      System.out.println(String.format("x: %d y: %s -> %s", x, y, ergebnis));
    }
  }

  public static void main(String[] args) {
    testeY(2);
    testeY(0);
    testeY(-2);

    System.out.println(0 % 2);
  }
}
