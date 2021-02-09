package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class Fibonacci {

  private static int fib(int n) {
    if (n == 1 || n == 2)
      return 1;
    else
      return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(fib(n));
  }
}
