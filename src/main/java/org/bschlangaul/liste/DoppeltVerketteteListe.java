package org.bschlangaul.liste;

/**
 * Implementierung der doppelt verketteten Liste
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 332/337), heißt im
 * Original „DList“.
 */
public class DoppeltVerketteteListe {
  static class Knoten {
    Object wert; // Element
    Knoten vorgänger, nächster; // Zeiger auf Vorgänger und Nachfolger

    public Knoten(Object wert, Knoten vorgänger, Knoten nächster) {
      this.wert = wert;
      this.vorgänger = vorgänger;
      this.nächster = nächster;
    }

    public Knoten() {
      wert = null;
      vorgänger = nächster = null;
    }

    // Vorgänger neu belegen
    public void setzteVorhergehenden(Knoten vorgänger) {
      this.vorgänger = vorgänger;
    }

    // Zugriff auf Vorgänger
    public Knoten gibVorgänger() {
      return vorgänger;
    }

    // Vorgänger neu belegen
    public void setzeNächsten(Knoten nächster) {
      this.nächster = nächster;
    }

    // Zugriff auf Vorgänger
    public Knoten gibNächsten() {
      return nächster;
    }

    // Zugriff auf Vorgänger
    public Object gibWert() {
      return wert;
    }
  }

  private Knoten anfang = null; // Listenanfang
  private Knoten ende = null; // Listenende

  public DoppeltVerketteteListe() {
    anfang = new Knoten();
    ende = new Knoten();
    // Anfang und Ende "verknüpfen"
    anfang.setzeNächsten(ende);
    ende.setzteVorhergehenden(anfang);
    ende.setzeNächsten(ende);
  }

  public void fügeErstenHinzu(Object o) {
    // Knoten zwischen head und dessen Nachfolger einfügen
    Knoten n = new Knoten(o, anfang, anfang.gibNächsten());
    anfang.gibNächsten().setzteVorhergehenden(n);
    anfang.setzeNächsten(n);
  }

  public void fügeLetztenHinzu(Object o) {
    // Knoten zwischen tail und dessen Vorgänger einfügen
    Knoten l = ende.gibVorgänger();
    Knoten n = new Knoten(o, l, ende);
    l.setzeNächsten(n);
    ende.setzteVorhergehenden(n);
  }

  public Object gibErsten() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();

    // Zugriff über Listenanfang
    return anfang.gibNächsten().gibWert();
  }

  public Object gibLetzten() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    // Zugriff über Listenende
    return ende.gibVorgänger().gibWert();
  }

  public Object entferneErsten() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    // Zugriff über Listenanfang
    Object o = anfang.gibNächsten().gibWert();
    // Knoten zwischen head und Nachfolger entfernen
    anfang.setzeNächsten(anfang.gibNächsten().gibNächsten());
    anfang.gibNächsten().setzteVorhergehenden(anfang);
    return o;
  }

  public Object entferneLetzten() throws LeereListeFehler {
    if (istLeer())
      throw new LeereListeFehler();
    // Zugriff über Listenende
    Knoten n = ende.gibVorgänger();
    // Knoten zwischen tail und Vorgänger entfernen
    n.gibVorgänger().setzeNächsten(ende);
    ende.setzteVorhergehenden(n.gibVorgänger());
    return n.gibWert();
  }

  public int länge() {
    int länge = 0;
    Knoten knoten = anfang;
    // Knoten zählen
    while (knoten.gibNächsten() != ende) {
      länge++;
      knoten = knoten.gibNächsten();
    }
    return länge;
  }

  public boolean istLeer() {
    // keine Knoten zwischen head und tail
    return anfang.gibNächsten() == ende;
  }

  class ListenIterator implements java.util.Iterator<Object> {
    private Knoten knoten = null;

    public ListenIterator() {
      // mit Listenanfang initialisieren
      knoten = anfang.gibNächsten();
    }

    public boolean hasNext() {
      return knoten != ende;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Object next() {
      if (!hasNext())
        throw new java.util.NoSuchElementException();
      Object wert = knoten.gibWert();
      knoten = knoten.gibNächsten();
      return wert;
    }
  }

  public java.util.Iterator<Object> iterator() {
    return new ListenIterator();
  }
}
