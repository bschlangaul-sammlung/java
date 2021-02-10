package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class SummeIterativ {

  public static int summe(int n) {
    int erg = 0;
    while (n > 0) {
      erg = n + erg;
      n--;
    }
    return erg;
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(summe(n)); // 28
  }
}
