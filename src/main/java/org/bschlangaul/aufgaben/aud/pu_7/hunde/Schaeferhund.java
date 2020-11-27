package org.bschlangaul.aufgaben.aud.pu_7.hunde;

public class Schaeferhund extends Hund {
  // (spezifische) Attribute
  private boolean blindenhund;

  // Konstruktor
  public Schaeferhund(String n, int a, double g, boolean bl) {
    super(n, a, g);
    blindenhund = bl;
  }

  // Methoden
  public void bellen() {
    System.out.println("wauwau");
  }

  public void fressen(double futter) {
    if (futter > 0 && futter < 0.3)
      super.fressen(futter);
  }

  public void fressen() {
    super.gewicht = super.gewicht + 0.3;
  }

  public void gassiGehen() {
    super.gewicht = super.gewicht - 0.15;
  }

  // Getter & Setter aus Teilaufgabe d)
  public boolean getBlind() {
    return blindenhund;
  }

  public void setBlind(boolean b) {
    blindenhund = b;
  }
}
