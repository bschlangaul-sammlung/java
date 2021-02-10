package org.bschlangaul.aufgaben.tech_info.assembler.ein_adress;

public class Primzahl {

  public static boolean istPrimzahl(int k) {
    if (k == 1)
      return false;
    int a = 0;
    int t = 1;

    do {
      t++;
      if (k % t == 0) {
        a = 1;
      }
    } while (a == 0);

    if (t == k) {
      return true;
    } else {
      return false;
    }
  }

  public static void berichtePrimzahl(int k) {
    String keine = "";
    if (!istPrimzahl(k)) {
      keine = "k";
    }
    System.out.println(String.format("Die Zahl %d ist %seine Primzahl", k, keine));
  }

  public static void main(String[] args) {
    berichtePrimzahl(1);
    berichtePrimzahl(2);
    berichtePrimzahl(3);
    berichtePrimzahl(4);
    berichtePrimzahl(5);
    berichtePrimzahl(6);
    berichtePrimzahl(7);
    berichtePrimzahl(8);
    berichtePrimzahl(9);
    berichtePrimzahl(10);
    berichtePrimzahl(11);
    berichtePrimzahl(12);
    berichtePrimzahl(13);
  }
}
