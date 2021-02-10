package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class SummeRekursiv {
  public static int summe(int n) {
    if (n > 0) return n + summe(n - 1);
    else return 0;
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(summe(n)); // 28
  }
}
