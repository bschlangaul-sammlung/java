package org.bschlangaul.aufgaben.oomup.klassendiagramm.hunde;

public class Bernhardiner extends Hund {
  private boolean glocke;

  public Bernhardiner(String n, int a, double g, boolean gl) {
    super(n, a, g);
    glocke = gl;
  }

  public void bellen() {
    System.out.println("WAUWAU");
  }

  public void fressen(double futter) {
    if (futter > 0 && futter < 0.5)
      super.fressen(futter);
  }

  public void fressen() {
    super.gewicht = super.gewicht + 0.5;
  }

  public void gassiGehen() {
    super.gewicht = super.gewicht - 0.2;
  }

  public boolean getGlocke() {
    return glocke;
  }

  public void setGlocke(boolean g) {
    glocke = g;
  }
}
