package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class SummeIterativ {

  public static int summe(int n) {
    int erg = 0;
    for (int i = 1; i <= n; i++) {
      erg = i + erg;
    }
    return erg;
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(summe(n)); // 28
  }
}
