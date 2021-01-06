package org.bschlangaul.baum;

/**
 * Ein zur Klasse {@link BinaererSuchBaum} gehörender Knoten (nach Saake Seite
 * 349)
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

  /**
   * Vergleiche den Schlüsselwert dieses Knotens mit einem zweiten Schlüsselwert.
   *
   * @param vergleichsSchlüssel Der Schlüsselwert, mit dem verglichen werden soll.
   *
   * @return -1, wenn der eigene Schlüsselwert kleiner als der Vergleichswert ist.
   *         -1, wenn der eigene Schlüsselwert null ist. 0, wenn beiden
   *         Schlüsselwerte gleich groß sind. 1, wenn der eigene Wert größer ist.
   */
  public int vergleiche(Comparable vergleichsSchlüssel) {
    return (schlüssel == null ? -1 : ((Comparable) schlüssel).compareTo(vergleichsSchlüssel));
  }
}
