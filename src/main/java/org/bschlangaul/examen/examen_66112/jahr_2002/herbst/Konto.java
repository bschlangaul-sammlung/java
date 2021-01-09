package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

public abstract class Konto {
  protected int kontonr;
  protected float kontostand;
  protected Besitzer hatBesitzer;

  public Konto(int knr, float kst, Besitzer b) {
    kontonr = knr;
    kontostand = kst;
    hatBesitzer = b;

  }

  public void einzahlen(float betrag) {
    kontostand += betrag;
  }

  public Besitzer getHatBesitzer() {
    return hatBesitzer;
  }

  public abstract boolean abheben(float betrag);

  public abstract void abrechnung();

}
