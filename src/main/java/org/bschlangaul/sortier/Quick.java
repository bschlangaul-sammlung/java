package org.bschlangaul.sortier;

/**
 * Die Lage des Pivot-Elements
 */
enum PivotLage {
  LINKS, MITTE, RECHTS,
}

public abstract class Quick extends Sortieralgorithmus {

  protected PivotLage pivotLage = PivotLage.MITTE;

  public void setztePivotLinks() {
    pivotLage = PivotLage.LINKS;
  }

  public void setztePivotMitte() {
    pivotLage = PivotLage.MITTE;
  }

  public void setztePivotRechts() {
    pivotLage = PivotLage.RECHTS;
  }

  protected int bestimmePivot(int links, int rechts) {
    // Pivot-Element bestimmen
    int pivotIndex;
    if (pivotLage == PivotLage.LINKS) {
      pivotIndex = links;
    } else if (pivotLage == PivotLage.RECHTS) {
      pivotIndex = rechts;
    } else {
      pivotIndex = (links + rechts) / 2;
    }
    berichte.feldMarkierung(links, rechts, pivotIndex);

    return pivotIndex;
  }
}
