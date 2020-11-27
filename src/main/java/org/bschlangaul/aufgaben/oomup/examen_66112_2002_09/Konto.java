package org.bschlangaul.aufgaben.oomup.examen_66112_2002_09;

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
