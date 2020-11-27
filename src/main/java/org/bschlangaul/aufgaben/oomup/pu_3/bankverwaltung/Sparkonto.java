package org.bschlangaul.aufgaben.oomup.pu_3.bankverwaltung;

public class Sparkonto extends Konto {
  private double zinssatz;

  public Sparkonto(int kontonummer, double zinssatz) {
    super(kontonummer);
    // Aufruf des Konstruktors der Oberklasse
    this.zinssatz = zinssatz;
  }

  /**
   * Überschreiben der Methode abheben() aus der Oberklasse. Ist genügend Geld auf
   * dem Sparkonto...? dann darf man den gewünschten Betrag abheben, sonst nicht.
   */
  public void abheben(double betrag) {
    if (kontostandGeben() >= betrag) {
      super.abheben(betrag);
    }
  }

  /**
   * Der aktuelle Kontostand wird mit dem Zinssatz verrechnet und der sich daraus
   * ergebende Betrag (= Zinsen) dem Konto gutgeschrieben.
   */
  public void verzinsen() {
    einzahlen(kontostandGeben() * zinssatz);
  }
}
