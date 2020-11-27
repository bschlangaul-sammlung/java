package org.bschlangaul.liste.saake;

class Knoten {
  /**
   * Das Datenobjekt
   */
  Object datenObjekt;

  /**
   * Verweis auf Nachfolger.
   */
  Knoten nächstes;

  /**
   * Konstruktoren.
   *
   * @param datenObjekt Das Datenobjekt, das gespeichert werden soll.
   * @param knoten Der nächste Knoten
   */
  public Knoten(Object datenObjekt, Knoten knoten) {
    this.datenObjekt = datenObjekt;
    nächstes = knoten;
  }

  public Knoten() {
    datenObjekt = null;
    nächstes = null;
  }

  /**
   * Element neu belegen.
   *
   * @param datenObjekt Das Datenobjekt, das gespeichert werden soll.
   */
  public void setzeDaten(Object datenObjekt) {
    this.datenObjekt = datenObjekt;
  }

  /**
   * Zugriff auf das Datenobjekt.
   *
   * @return Ein Datenobjekt.
   */
  public Object gibDaten() {
    return datenObjekt;
  }

  /**
   * Nachfolger setzen.
   *
   * @param knoten Der Knoten, der als nächster Knoten gesetzt wird.
   */
  public void setzteNächstes(Knoten knoten) {
    nächstes = knoten;
  }

  /**
   * Zugriff auf Nachfolger
   *
   * @return Den nächsten Knoten.
   */
  public Knoten gibNächstes() {
    return nächstes;
  }
}
