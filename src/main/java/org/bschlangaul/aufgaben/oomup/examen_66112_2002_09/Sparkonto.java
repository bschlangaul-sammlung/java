package org.bschlangaul.aufgaben.oomup.examen_66112_2002_09;

public class Sparkonto extends Konto {
  private float zinssatz;

  public Sparkonto(int knr, float kst, Besitzer b, float zs) {
    super(knr, kst, b);
    zinssatz = zs;
  }

  public boolean abheben(float betrag) {
    if (kontostand - betrag >= 0) {
      kontostand -= betrag;
      return true;
    } else {
      return false;
    }
  }

  public void abrechnung() {
    kontostand += kontostand * (1 + zinssatz);
  }
}
