package org.bschlangaul.aufgaben.tech_info.assembler.mehr_adress;

public class Fakultaet {

  private static int fak(int n) {
    if (n == 1) {
      return 1;
    } else {
      return n * fak(n - 1);
    }
  }

  public static void main(String[] args) {
    int n = 7;
    System.out.println(fak(n));
  }
}
