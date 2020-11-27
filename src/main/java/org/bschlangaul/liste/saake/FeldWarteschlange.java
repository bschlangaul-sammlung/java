package org.bschlangaul.liste.saake;

/**
 * Implementierung der Queue auf Basis eines Feldes
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 321/322),
 * heißt im Original „ArrayQueue“.
 */
public class FeldWarteschlange implements Warteschlange {
  private Object[] elements = null; // Elemente
  private int l = 0, u = 0; // unterer und oberer Zeiger

  // Queue mit vorgegebener Kapazität erzeugen
  public FeldWarteschlange(int size) {
    elements = new Object[size];
  }

  // Queue mit Standardkapazität erzeugen
  public FeldWarteschlange() {
    elements = new Object[100];
  }

  public void enter(Object obj) throws WarteschlangeFehler {
    if ((elements.length - l + u) % elements.length == elements.length - 1)
      // Kapazität ist erschöpft
      throw new WarteschlangeFehler();
    elements[u] = obj;
    // oberen Zeiger aktualisieren
    u = (u + 1) % elements.length;
  }

  public Object leave() throws WarteschlangeFehler {
    if (isEmpty())
      throw new WarteschlangeFehler();
    Object obj = elements[l];
    elements[l] = null;
    // unteren Zeiger aktualisieren
    l = (l + 1) % elements.length;
    return obj;
  }

  public Object front() throws WarteschlangeFehler {
    if (isEmpty())
      throw new WarteschlangeFehler();
    return elements[l];
  }

  public boolean isEmpty() {
    return l == u;
  }
}
