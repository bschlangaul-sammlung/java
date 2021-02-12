package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class Fakultaet {

  public static int fak(int n) {
    if (n <= 1) {
      return 1;
    } else {
      return n * fak(n - 1);
    }
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(fak(n));

    System.out.println(fak(0)); // 1
    System.out.println(fak(1)); // 1
    System.out.println(fak(2)); // 2
    System.out.println(fak(3)); // 6
    System.out.println(fak(4)); // 24
    System.out.println(fak(5)); // 120
    System.out.println(fak(6)); // 720
    System.out.println(fak(7)); // 5040
    System.out.println(fak(8)); // 40320
    System.out.println(fak(9)); // 362880
    System.out.println(fak(10)); // 3628800
  }
}
