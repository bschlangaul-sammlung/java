package org.bschlangaul.aufgaben.aud.pu_7.hunde;

public class Chihuahua extends Hund {
  // Konstruktor
  public Chihuahua(String n, int a, double g) {
    super(n, a, g);

  }

  // Methoden
  public void bellen() {
    System.out.println("wuffwuff");
  }

  public void fressen(double futter) {
    if (futter > 0 && futter < 0.09)
      super.fressen(futter);
  }

  public void fressen() {
    super.gewicht = super.gewicht + 0.09;
  }

  public void gassiGehen() {
    super.gewicht = super.gewicht - 0.04;
  }
}
