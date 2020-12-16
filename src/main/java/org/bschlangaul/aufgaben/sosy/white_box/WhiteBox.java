package org.bschlangaul.aufgaben.sosy.white_box;

public class WhiteBox {
  public int ggT(int a, int b) {
    int result = 1;
    for (int i = 1; i <= Math.min(a, b); i++) {
      if((a % i == 0) & (b % i == 0)) {
        result = i;
      }
    }
    System.out.println(result);
    return result;
  }

  public static void main(String[] args) {
    WhiteBox w = new WhiteBox();
    w.ggT(1,2); // 1
    w.ggT(10,2); // 2
    w.ggT(4,7); // 1
    w.ggT(6,2); // 1
    w.ggT(5,2); // 1
    w.ggT(101,27); // 1
    w.ggT(12,27); // 3
    w.ggT(10,20); // 10
    w.ggT(-1,-2); // 1
  }
}
