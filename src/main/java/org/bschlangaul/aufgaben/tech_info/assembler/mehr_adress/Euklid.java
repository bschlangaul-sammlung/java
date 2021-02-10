package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class Euklid {

  public static int euklid(int a, int b) {
    if (a == 0)
      return b;
    while (b != 0) {
      if (a > b)
        a = a - b;
      else
        b = b - a;
    }
    return a;
  }

  public static void main(String[] args) {
    System.out.println(euklid(3780, 3528)); // 252
    System.out.println(euklid(12, 18)); // 6
    System.out.println(euklid(17, 1)); // 1
    System.out.println(euklid(1, 17)); // 1
    System.out.println(euklid(0, 3)); // 3
    System.out.println(euklid(3, 0)); // 3
  }

}
