package org.bschlangaul.baum;

/**
 * Saake Seite 349
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaumKnoten {
  public BaumKnoten links = null;
  public BaumKnoten rechts = null;
  public Object schlüssel;

  public BaumKnoten(Object schlüssel) {
    this.schlüssel = schlüssel;
  }

  public Object gibSchlüssel() {
    return schlüssel;
  }

  public BaumKnoten gibLinks() {
    return links;
  }

  public BaumKnoten gibRechts() {
    return rechts;
  }

  public void setzeLinks(BaumKnoten knoten) {
    links = knoten;
  }

  public void setzeRechts(BaumKnoten knoten) {
    rechts = knoten;
  }

  public String toString() {
    return schlüssel.toString();
  }

  public int vergleiche(Comparable c) {
    return (schlüssel == null ? -1 : ((Comparable) schlüssel).compareTo(c));
  }
}
