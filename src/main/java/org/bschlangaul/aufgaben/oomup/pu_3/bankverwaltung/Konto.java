package org.bschlangaul.aufgaben.oomup.pu_3.bankverwaltung;

public abstract class Konto {
  private double kontostand;
  @SuppressWarnings("unused")
  private int kontonummer;

  public Konto(int kontonummer) {
    this.kontonummer = kontonummer;
    kontostand = 0;
  }

  public void einzahlen(double betrag) {
    kontostand = kontostand + betrag;
  }

  public void abheben(double betrag) {
    // Ob abgehoben werden darf, entscheidet die Methode der jeweiligen Unterklasse.
    kontostand = kontostand - betrag;
  }

  public double kontostandGeben() {
    return kontostand;
  }
}
