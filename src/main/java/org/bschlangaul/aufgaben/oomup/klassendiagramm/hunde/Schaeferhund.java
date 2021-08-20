package org.bschlangaul.aufgaben.oomup.klassendiagramm.hunde;

public class Schaeferhund extends Hund {
  private boolean blindenhund;

  public Schaeferhund(String n, int a, double g, boolean bl) {
    super(n, a, g);
    blindenhund = bl;
  }

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

  public boolean getBlind() {
    return blindenhund;
  }

  public void setBlind(boolean b) {
    blindenhund = b;
  }
}
