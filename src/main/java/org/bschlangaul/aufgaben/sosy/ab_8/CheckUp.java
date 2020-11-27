package org.bschlangaul.aufgaben.sosy.ab_8;

public class CheckUp {
  static int mul(int x, int y) {
    /* P */
    int z = 0, i = 0;
    while (i++ != x)
      z += y;
    /* Q */
    return z;
  }

  static int mul2(int x, int y) {
    /* P */
    int z = 0, i = 0;
    while (i < x) {
      i = i + 1;
      z = z + y;
    }
    /* Q */
    return z;
  }

  static int mulMitInvariante1(int x, int y) {
    /* P */
    int z = 0, i = 0;
    invariante1(i, x, y, z);
    while (i < x) {
      invariante1(i, x, y, z);
      i = i + 1;
      z = z + y;
      invariante1(i, x, y, z);
    }
    invariante1(i, x, y, z);
    /* Q */
    return z;
  }

  static int mulMitInvariante3(int x, int y) {
    /* P */
    int z = 0, i = 0;
    invariante3(i, x, y, z);
    while (i < x) {
      invariante3(i, x, y, z);
      i = i + 1;
      z = z + y;
      invariante3(i, x, y, z);
    }
    invariante3(i, x, y, z);
    /* Q */
    return z;
  }

  public static void invariante1(int i, int x, int y, int z) {
    if (z + i * y == x * y) {
      System.out.println("wahr");
    } else {
      System.out.println("falsch");
    }
  }

  public static void invariante3(int i, int x, int y, int z) {
    if (z + (x - i) * y == x * y) {
      System.out.println("wahr");
    } else {
      System.out.println("falsch");
    }
  }

  public static void main(String[] args) {
    System.out.println(mul(3, 4));
    System.out.println(mulMitInvariante1(2, 4));
    System.out.println(mulMitInvariante3(3, 4));
  }
}
