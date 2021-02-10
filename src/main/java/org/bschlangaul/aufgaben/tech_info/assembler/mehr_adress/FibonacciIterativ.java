package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class FibonacciIterativ {

  static int fib(int n) {
    if (n == 0)
      return 0;
    else if (n == 1)
      return 1;

    int vorletzte = 0;

    int letzte = 1;

    int erg = 0;

    int i = 1;
    while (i < n) {
      erg = letzte + vorletzte;
      vorletzte = letzte;
      letzte = erg;
      i++;
    }
    return erg;
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(fib(n)); // 13
  }
}
