package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

public abstract class Konto {
  protected int kontoNummer;
  protected float kontoStand;
  protected Besitzer besitzer;

  public Konto(int kontoNummer, float kontoStand, Besitzer besitzer) {
    this.kontoNummer = kontoNummer;
    this.kontoStand = kontoStand;
    this.besitzer = besitzer;
  }

  public void zahleEin(float betrag) {
    kontoStand += betrag;
  }

  public Besitzer gibBesitzer() {
    return besitzer;
  }

  public abstract boolean hebeAb(float betrag);

  public abstract void rechneAb();

}
