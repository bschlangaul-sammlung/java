package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class Fakultaet {

  private static int berechneFak(int n) {
    if (n == 1) {
      return 1;
    } else {
      return berechneFak(n - 1) * n;
    }
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(berechneFak(n));
  }
}
