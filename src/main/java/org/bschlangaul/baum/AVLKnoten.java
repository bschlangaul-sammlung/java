package org.bschlangaul.baum;

@SuppressWarnings("rawtypes")
public class AVLKnoten extends Knoten {
  int höhe;
  AVLKnoten links;
  AVLKnoten rechts;

  AVLKnoten(Comparable schlüssel) {
    super(schlüssel);
  }

  public AVLKnoten gibLinks() {
    return links;
  }

  public AVLKnoten gibRechts() {
    return rechts;
  }
}
