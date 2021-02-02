package org.bschlangaul.aufgaben.tech_info.assembler;

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
    System.out.println(multipliziereIterativ(9, 9));
    System.out.println(multipliziereRekursiv(9, 9));
  }
}
