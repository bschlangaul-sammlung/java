package org.bschlangaul.liste;

/**
 * Implementierung einer einfach verketteten Liste.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 326-329) heißt im
 * Original „List“.
 */
public class Liste {

  /**
   * Der Listenkopf.
   */
  private ListenKnoten kopf = null;

  public Liste() {
    kopf = new ListenKnoten();
  }

  /**
   * Diese Methode heißt im Englischen oft „addFirst()“
   *
   * @param wert Das Datenobjekt, das in die Liste eingefügt werden soll.
   */
  public void setzeErstes(Object wert) {
    // neuen Knoten hinter kopf einfügen
    ListenKnoten knoten = new ListenKnoten(wert, kopf.gibNächstes());
    kopf.setzteNächstes(knoten);
  }

  /**
   * Diese Methode heißt im Englischen oft „addLast()“
   *
   * @param wert Das Datenobjekt, das in die Liste eingefügt werden soll.
   */
  public void setzeLetztes(Object wert) {
    ListenKnoten tmp = kopf;
    // letzten Knoten ermitteln
    while (tmp.gibNächstes() != null)
      tmp = tmp.gibNächstes();
    ListenKnoten knoten = new ListenKnoten(wert, null);
    // neuen Knoten anfügen
    tmp.setzteNächstes(knoten);
  }

  /**
   * Gib den Wert des ersten Listenknotens zurück. Diese Methode heißt im
   * Englischen oft „getFirst()“.
   *
   * @return Der Wert des ersten Listenknotens.
   *
   * @throws LeereListeFehler Wirft einen Fehler, wenn die Liste leer ist.
   */
  public Object gibErstes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    // erstes Element ist Nachfolger von kopf
    return kopf.gibNächstes().gibDaten();
  }

  /**
   * Gib den Wert des letzten Listenknotens zurück. Diese Methode heißt im
   * Englischen oft „getLast()“.
   *
   * @return Der Wert des letzten Listenknotens.
   *
   * @throws LeereListeFehler Wirft einen Fehler, wenn die Liste leer ist.
   */
  public Object gibLetztes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    ListenKnoten tmp = kopf;
    // letzten Knoten ermitteln
    while (tmp.gibNächstes() != null)
      tmp = tmp.gibNächstes();
    return tmp.gibDaten();
  }

  /**
   * Entferne den ersten Listenknoten aus der Liste und gib seinen Wert zurück.
   * Der Verweis von {@link kopf} wird aktualisiert. Diese Methode heißt im
   * Englischen oft „removeFirst()“.
   *
   * @return Der Wert des entfernten ersten Datenknotens.
   *
   * @throws LeereListeFehler Wirft einen Fehler, wenn die Liste leer ist.
   */
  public Object entferneErstes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    Object datenObjekt = kopf.gibNächstes().gibDaten();
    kopf.setzteNächstes(kopf.gibNächstes().gibNächstes());
    return datenObjekt;
  }

  /**
   * Entferne den letzten Listenknoten aus der Liste und gib seinen Wert zurück.
   * Diese Methode heißt im Englischen oft „removeLast()“.
   *
   * @return Der Wert des entfernten letzten Datenknotens.
   *
   * @throws LeereListeFehler Wirft einen Fehler, wenn die Liste leer ist.
   */
  public Object entferneLetztes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    ListenKnoten tmp = kopf;
    // vorletzten Knoten ermitteln
    while (tmp.gibNächstes().gibNächstes() != null)
      tmp = tmp.gibNächstes();
    Object datenObjekt = tmp.gibNächstes().gibDaten();
    // Verweis auf Nachfolger löschen
    tmp.setzteNächstes(null);
    return datenObjekt;
  }

  /**
   * Diese Methode heißt im Englischen oft „size()“.
   *
   * @return Die Anzahl der Knoten.
   */
  public int gibAnzahl() {
    int anzahl = 0;
    ListenKnoten tmp = kopf;
    // Knoten zählen
    while (tmp.gibNächstes() != null) {
      anzahl++;
      tmp = tmp.gibNächstes();
    }
    return anzahl;
  }

  /**
   * Diese Methode heißt im Englischen oft „isEmpty()“.
   *
   * @return Die Anzahl der Knoten.
   */
  public boolean istLeer() {
    return kopf.gibNächstes() == null;
  }

  public static void main(String[] args) {
    Liste liste = new Liste();
    liste.setzeErstes(1);
    liste.setzeErstes(2);
    liste.setzeErstes(3);
    System.out.println(liste.gibAnzahl());
  }
}
