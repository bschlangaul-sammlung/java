package org.bschlangaul.aufgaben.oomup.pu_3.bankverwaltung;

public class Girokonto extends Konto {
  /**
   * Die Unterklasse muss auch auf das Attribut zugreifen können.
   */
  protected double dispokredit;

  public Girokonto(int kontonummer) {
    super(kontonummer);
    dispokredit = 2000;
  }

  /**
   * Die Methode abheben() kann direkt von der Oberklasse GIROKONTO und die
   * Methoden einzahlen() und kontostandGeben() von der Oberklasse KONTO genutzt
   * werden.
   *
   * Überschreiben der Methode abheben() der Klasse KONTO
   * Ist genügend Geld auf dem Konto bzw. reicht der Dispokredit aus...
   *  ...dann darf man den gewünschten Betrag abheben, sonst nicht.
   */
  public void abheben(double betrag) {//
    if (kontostandGeben() + dispokredit >= betrag) {
      super.abheben(betrag);
    }
  }

}
