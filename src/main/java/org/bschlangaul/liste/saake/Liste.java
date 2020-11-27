package org.bschlangaul.liste.saake;

/**
 * Implementierung einer einfach verketteten Liste.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 326-329) heißt im
 * Original „List“.
 */
public class Liste {

  /**
   * Listenkopf
   */
  private Knoten kopf = null;

  public Liste() {
    kopf = new Knoten();
  }

  public void fügeErstesHinzu(Object datenObjekt) {
    // neuen Knoten hinter kopf einfügen
    Knoten knoten = new Knoten(datenObjekt, kopf.gibNächstes());
    kopf.setzteNächstes(knoten);
  }

  public void fügeLetztesHinzu(Object datenObjekt) {
    Knoten tmp = kopf;
    // letzten Knoten ermitteln
    while (tmp.gibNächstes() != null)
      tmp = tmp.gibNächstes();
    Knoten knoten = new Knoten(datenObjekt, null);
    // neuen Knoten anfügen
    tmp.setzteNächstes(knoten);
  }

  public Object gibErstes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    // erstes Element ist Nachfolger von kopf
    return kopf.gibNächstes().gibDaten();
  }

  public Object gibLetztes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    Knoten tmp = kopf;
    // letzten Knoten ermitteln
    while (tmp.gibNächstes() != null)
      tmp = tmp.gibNächstes();
    return tmp.gibDaten();
  }

  /**
   * Verweis von {@link kopf} aktualisieren
   *
   * @return Das entfernte Datenobjekt.
   *
   * @throws LeereListeFehler Leere Liste Fehler
   */
  public Object entferneErstes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    Object datenObjekt = kopf.gibNächstes().gibDaten();
    kopf.setzteNächstes(kopf.gibNächstes().gibNächstes());
    return datenObjekt;
  }

  public Object entferneLetztes() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    Knoten tmp = kopf;
    // vorletzten Knoten ermitteln
    while (tmp.gibNächstes().gibNächstes() != null)
      tmp = tmp.gibNächstes();
    Object datenObjekt = tmp.gibNächstes().gibDaten();
    // Verweis auf Nachfolger löschen
    tmp.setzteNächstes(null);
    return datenObjekt;
  }

  /**
   * „size()“
   *
   * @return Die Anzahl der Knoten.
   */
  public int gibAnzahl() {
    int anzahl = 0;
    Knoten tmp = kopf;
    // Knoten zählen
    while (tmp.gibNächstes() != null) {
      anzahl++;
      tmp = tmp.gibNächstes();
    }
    return anzahl;
  }

  public boolean istLeer() {
    return kopf.gibNächstes() == null;
  }

  public static void main(String[] args) {
    Liste liste = new Liste();
    liste.fügeErstesHinzu(1);
    liste.fügeErstesHinzu(2);
    liste.fügeErstesHinzu(3);
    System.out.println(liste.gibAnzahl());
  }
}
