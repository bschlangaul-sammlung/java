package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

public class Girokonto extends Konto {
  private float kreditRahmen;
  private float jahresGebühr;

  public Girokonto(int kontoNummer, float kontoStand, Besitzer besitzer, float kreditRahmen, float jahresGebühr) {
    super(kontoNummer, kontoStand, besitzer);
    this.kreditRahmen = kreditRahmen;
    this.jahresGebühr = jahresGebühr;
  }

  public boolean hebeAb(float betrag) {
    if (kontoStand - betrag >= kreditRahmen) {
      kontoStand -= betrag;
      return true;
    } else {
      return false;
    }
  }

  public void rechneAb() {
    kontoStand -= jahresGebühr;
  }
}
