package org.bschlangaul.aufgaben.sosy.eklausur;

public class Kontrollflussgraph {
  public static int ggT(int a, int b) {
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
    ggT(1,2); // 1
    ggT(10,2); // 2
    ggT(4,7); // 1
    ggT(6,2); // 1
    ggT(5,2); // 1
    ggT(101,27); // 1
    ggT(12,27); // 3
    ggT(10,20); // 10
    ggT(-1,-2); // 1
  }
}
