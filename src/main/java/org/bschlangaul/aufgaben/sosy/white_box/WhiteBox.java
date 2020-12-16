package org.bschlangaul.aufgaben.sosy.white_box;

public class WhiteBox {
  public int ggT(int a, int b) {
    int result = 1;
    for (int i = 1; i <= Math.min(a, b); i++) {
      if ((a % i == 0) & (b % i == 0)) {
        result = i;
      }
    }
    return result;
  }

  public static void debug(int a, int b) {
    WhiteBox w = new WhiteBox();
    System.out.println(w.ggT(a, b));
  }

  public static void main(String[] args) {
    debug(1, 2); // 1
    debug(10, 2); // 2
    debug(4, 7); // 1
    debug(6, 2); // 1
    debug(5, 2); // 1
    debug(101, 27); // 1
    debug(12, 27); // 3
    debug(10, 20); // 10
    debug(-1, -2); // 1
  }
}
