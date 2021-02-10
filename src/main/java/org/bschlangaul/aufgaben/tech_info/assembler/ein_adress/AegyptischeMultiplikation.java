package org.bschlangaul.aufgaben.tech_info.assembler.ein_adress;

public class AegyptischeMultiplikation {

  public static int multipliziereIterativ(int z1, int z2) {
    int ergebnis = 0;
    while (z1 > 0) {
      if (z1 % 2 == 1) {
        ergebnis = ergebnis + z2;
      }
      z1 = z1 / 2;
      z2 = z2 * 2;
    }
    return ergebnis;
  }

  public static String formatiereZahl(int zahl) {
    return String.format("%3d ", zahl);
  }

  public static int multipliziereIterativAusgabe(int z1, int z2) {
    System.out.println(String.format("multipliziere %dx%d", z1, z2));
    int ergebnis = 0;
    System.out.print(formatiereZahl(z1));
    System.out.print(formatiereZahl(z2));

    while (z1 > 0) {
      if (z1 % 2 == 1) {
        System.out.println(formatiereZahl(z2));
        ergebnis = ergebnis + z2;
      } else {
        System.out.println("  x ");
      }
      z1 = z1 / 2;
      z2 = z2 * 2;
      if (z1 > 0) {
        System.out.print(formatiereZahl(z1));
        System.out.print(formatiereZahl(z2));
      }
    }
    System.out.println("-".repeat(12));
    System.out.println(String.format("%11d", ergebnis));
    return ergebnis;
  }

  public static int multipliziereRekursiv(int z1, int z2) {
    if (z1 == 1)
      return z2;
    int z1Alt = z1;
    int z2Alt = z2;

    z1 = z1 / 2;
    z2 = z2 * 2;

    if (z1Alt % 2 == 1)
      return z2Alt + multipliziereRekursiv(z1, z2);
    else
      return multipliziereRekursiv(z1, z2);
  }

  public static void main(String[] args) {
    multipliziereIterativAusgabe(13, 5);
  }
}
