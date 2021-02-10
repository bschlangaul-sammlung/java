package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class SummeRekursiv {
  public static int summe(int n) {
    if (n > 0) return n + summe(n - 1);
    else return 0;
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(summe(n)); // 28

    System.out.println(summe(0)); // 0
    System.out.println(summe(1)); // 1
    System.out.println(summe(2)); // 3
    System.out.println(summe(3)); // 6
    System.out.println(summe(4)); // 10
    System.out.println(summe(5)); // 15
    System.out.println(summe(6)); // 21
    System.out.println(summe(7)); // 28
    System.out.println(summe(8)); // 36
    System.out.println(summe(9)); // 45
    System.out.println(summe(10)); // 55
  }
}
