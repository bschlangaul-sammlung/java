package org.bschlangaul.aufgaben.tech_info.assembler.ein_adress;

public class Pulverdose {

  public static void berechne() {
    int V = 32766;
    int y = V;
    int z = 1;
    int erg;

    while (y > z) {
      y = (y + z) / 2;
      z = V / y;
    }
    erg = y / 19;
    System.out.println(erg);
  }

  public static void main(String[] args) {
    berechne();
  }
}
