package org.bschlangaul.liste;

class ListenKnoten {
  /**
   * Das Datenobjekt.
   */
  Object wert;

  /**
   * Verweis auf Nachfolger.
   */
  ListenKnoten nächstes;

  /**
   * Konstruktoren.
   *
   * @param wert Das Datenobjekt, das gespeichert werden soll.
   * @param knoten Der nächste Knoten
   */
  public ListenKnoten(Object wert, ListenKnoten knoten) {
    this.wert = wert;
    nächstes = knoten;
  }

  public ListenKnoten() {
    wert = null;
    nächstes = null;
  }

  /**
   * Element neu belegen.
   *
   * @param wert Das Datenobjekt, das gespeichert werden soll.
   */
  public void setzeDaten(Object wert) {
    this.wert = wert;
  }

  /**
   * Zugriff auf das Datenobjekt.
   *
   * @return Ein Datenobjekt.
   */
  public Object gibDaten() {
    return wert;
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
