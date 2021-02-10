package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class FibonacciIterativ {

  static int fib(int n) {
    if (n <= 1)
      return n;

    int vorletzte = 0;
    int letzte = 1;
    int erg = 0;

    while (n > 1) {
      erg = letzte + vorletzte;
      vorletzte = letzte;
      letzte = erg;
      n--;
    }
    return erg;
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(fib(n)); // 13

    System.out.println(fib(0)); // 0
    System.out.println(fib(1)); // 1
    System.out.println(fib(2)); // 1
    System.out.println(fib(3)); // 2
    System.out.println(fib(4)); // 3
    System.out.println(fib(5)); // 5
    System.out.println(fib(6)); // 8
    System.out.println(fib(7)); // 13
    System.out.println(fib(8)); // 21
    System.out.println(fib(9)); // 34
    System.out.println(fib(10)); // 55
  }
}
