package org.bschlangaul.aufgaben.tech_info.assembler;

public class Collatz {
  public static void main(String[] args) {
    int n = 7;
    int anzahl = 0;
    while (n != 1) {
      if (n % 2 == 0) {
        n = n / 2;
      } else {
        n = 3 * n + 1;
      }
      anzahl++;
      System.out.println(n);
    }
    System.out.println("Anzahl an Durchläufen " + anzahl);
  }
}
