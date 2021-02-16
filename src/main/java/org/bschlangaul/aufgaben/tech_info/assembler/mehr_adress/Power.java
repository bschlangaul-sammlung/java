package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class Power {

  public static int power(int a, int n) {
    if (n == 0) {
      return 1;
    } else {
      return a * power(a, n - 1);
    }
  }

  public static void main(String args[]) {
    int a = 3;
    int n = 4;
    System.out.println(power(a, n));

    System.out.println(power(3, 0)); // 1
    System.out.println(power(3, 1)); // 3
    System.out.println(power(3, 2)); // 9
    System.out.println(power(3, 3)); // 27
    System.out.println(power(3, 4)); // 81
    System.out.println(power(3, 6)); // 729
    System.out.println(power(3, 7)); // 2187
  }

}
