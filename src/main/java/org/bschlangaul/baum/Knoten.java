package org.bschlangaul.baum;

/**
 * Saake Seite 349
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Knoten {
  public Knoten links = null;
  public Knoten rechts = null;
  public Object schlüssel;

  public Knoten(Object schlüssel) {
    this.schlüssel = schlüssel;
  }

  public Object gibSchlüssel() {
    return schlüssel;
  }

  public Knoten gibLinks() {
    return links;
  }

  public Knoten gibRechts() {
    return rechts;
  }

  public void setzeLinks(Knoten knoten) {
    links = knoten;
  }

  public void setzeRechts(Knoten knoten) {
    rechts = knoten;
  }

  public String toString() {
    return schlüssel.toString();
  }

  public int vergleiche(Comparable c) {
    return (schlüssel == null ? -1 : ((Comparable) schlüssel).compareTo(c));
  }
}
