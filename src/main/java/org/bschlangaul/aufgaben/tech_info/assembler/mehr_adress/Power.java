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
  }

}
