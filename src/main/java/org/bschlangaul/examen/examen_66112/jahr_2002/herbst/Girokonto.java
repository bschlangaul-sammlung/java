package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

public class Girokonto extends Konto {
  private float kreditrahmen;
  private float jahresgebuehr;

  public Girokonto(int knr, float kst, Besitzer b, float kr, float jg) {
    super(knr, kst, b);
    kreditrahmen = kr;
    jahresgebuehr = jg;
  }

  public boolean abheben(float betrag) {
    if (kontostand - betrag >= kreditrahmen) {
      kontostand -= betrag;
      return true;
    } else {
      return false;
    }
  }

  public void abrechnung() {
    kontostand -= jahresgebuehr;
  }
}
