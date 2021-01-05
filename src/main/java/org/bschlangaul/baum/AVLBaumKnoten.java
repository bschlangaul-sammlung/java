package org.bschlangaul.baum;

/**
 * Ein zur Klasse {@link AVLBaum} gehörender Baumknoten.
 */
@SuppressWarnings("rawtypes")
public class AVLBaumKnoten extends BaumKnoten {

  int höhe;

  /**
   * {@inheritDoc}
   */
  AVLBaumKnoten links;

  /**
   * {@inheritDoc}
   */
  AVLBaumKnoten rechts;

  AVLBaumKnoten(Comparable schlüssel) {
    super(schlüssel);
  }

  /**
   * {@inheritDoc}
   */
  public AVLBaumKnoten gibLinks() {
    return links;
  }

  /**
   * {@inheritDoc}
   */
  public AVLBaumKnoten gibRechts() {
    return rechts;
  }
}
