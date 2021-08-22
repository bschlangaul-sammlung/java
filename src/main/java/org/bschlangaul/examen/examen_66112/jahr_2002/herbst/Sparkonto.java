package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

public class Sparkonto extends Konto {
  private float zinssatz;

  public Sparkonto(int kontoNummer, float kontoStand, Besitzer besitzer, float zinssatz) {
    super(kontoNummer, kontoStand, besitzer);
    this.zinssatz = zinssatz;
  }

  public boolean hebeAb(float betrag) {
    if (kontoStand - betrag >= 0) {
      kontoStand -= betrag;
      return true;
    } else {
      return false;
    }
  }

  public void rechneAb() {
    kontoStand += kontoStand * (1 + zinssatz);
  }
}
