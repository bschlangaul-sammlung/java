package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

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
