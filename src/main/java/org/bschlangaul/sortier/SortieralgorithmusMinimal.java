package org.bschlangaul.sortier;

/**
 * Bietet ein Feld mit dem Namen a an, dass dem Feld zahlen entspricht, um einen
 * minimalen Algorithmus mit kurzem Variablennamen bereitstellen zu können.
 */
public abstract class SortieralgorithmusMinimal extends Sortieralgorithmus {

  int[] a;

  public SortieralgorithmusMinimal() {
    super();
    this.a = this.zahlen;
  }

  public SortieralgorithmusMinimal(int[] zahlen) {
    super(zahlen);
    this.a = zahlen;
  }

  public void setzeZahlen(int[] zahlen) {
    this.a = zahlen;
    this.zahlen = zahlen;
  }
}
