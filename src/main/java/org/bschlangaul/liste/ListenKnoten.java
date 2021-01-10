package org.bschlangaul.liste;

class ListenKnoten {
  /**
   * Das Datenobjekt
   */
  Object datenObjekt;

  /**
   * Verweis auf Nachfolger.
   */
  ListenKnoten nächstes;

  /**
   * Konstruktoren.
   *
   * @param datenObjekt Das Datenobjekt, das gespeichert werden soll.
   * @param knoten Der nächste Knoten
   */
  public ListenKnoten(Object datenObjekt, ListenKnoten knoten) {
    this.datenObjekt = datenObjekt;
    nächstes = knoten;
  }

  public ListenKnoten() {
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
  public void setzteNächstes(ListenKnoten knoten) {
    nächstes = knoten;
  }

  /**
   * Zugriff auf Nachfolger
   *
   * @return Den nächsten Knoten.
   */
  public ListenKnoten gibNächstes() {
    return nächstes;
  }
}
