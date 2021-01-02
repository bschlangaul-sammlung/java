package org.bschlangaul.baum;

@SuppressWarnings("rawtypes")
public class AVLBaumKnoten extends BaumKnoten {
  int höhe;
  AVLBaumKnoten links;
  AVLBaumKnoten rechts;

  AVLBaumKnoten(Comparable schlüssel) {
    super(schlüssel);
  }

  public AVLBaumKnoten gibLinks() {
    return links;
  }

  public AVLBaumKnoten gibRechts() {
    return rechts;
  }
}
