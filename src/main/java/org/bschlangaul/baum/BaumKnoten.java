package org.bschlangaul.baum;

/**
 * Ein zur Klasse {@link BinaererSuchBaum} gehörender Knoten (nach Saake
 * Seite 349)
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaumKnoten {

  /**
   * Der linke Kindknoten.
   */
  public BaumKnoten links = null;

  /**
   * Der rechte Kindknoten.
   */
  public BaumKnoten rechts = null;
  public Object schlüssel;

  public BaumKnoten(Object schlüssel) {
    this.schlüssel = schlüssel;
  }

  public Object gibSchlüssel() {
    return schlüssel;
  }

  /**
   * Gib den linken Kindknoten.
   *
   * @return Den linken Kindknoten.
   */
  public BaumKnoten gibLinks() {
    return links;
  }

  /**
   * Gib den rechten Kindknoten.
   *
   * @return Den rechten Kindknoten.
   */
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
