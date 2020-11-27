package org.bschlangaul.entwurfsmuster.zustand.hauer;

class Freundin {
  // Setter zum Setzen des aktuellen Zustands.
  public void setAktuellerZustand(Zustand aktuellerZustand) {
    this.aktuellerZustand = aktuellerZustand;
  }

  // Defaultzustand Neutral im Konstruktor setzen.
  public Freundin() {
    setAktuellerZustand(new Neutral(this));
  }

  // Rest wie gehabt.
  private Zustand aktuellerZustand;

  public void unterhalten() {
    aktuellerZustand.unterhalten();
  }

  public void kussGeben() {
    aktuellerZustand.kussGeben();
  }

  public void verärgern() {
    aktuellerZustand.verärgern();
  }
}
