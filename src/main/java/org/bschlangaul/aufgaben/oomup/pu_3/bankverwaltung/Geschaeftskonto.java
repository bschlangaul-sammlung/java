package org.bschlangaul.aufgaben.oomup.pu_3.bankverwaltung;

public class Geschaeftskonto extends Girokonto {

  /**
   * Für das Geschäftskonto wird der Dispo individuell festgelegt.
   *
   * @param kontonummer Die Kontonummer
   * @param dispo Der maximale Rahme des Dispokredits.
   */
  public Geschaeftskonto(int kontonummer, double dispo) {
    super(kontonummer);
    dispokredit = dispo;
  }

  public void dispokreditSetzen(double dispokreditNeu) {
    dispokredit = dispokreditNeu;
  }
}
