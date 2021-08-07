package org.bschlangaul.sortier.visualisierung;

abstract public class Reporter {
  public int[] zahlen;

  /**
   * Zeige einen Ausschnitt der Zahlen in der Textkonsole. Es handelt sich um eine
   * Hilfsmethode, um die Sortieralgorithmen besser verstehen zu können.
   *
   * @param links  Die linke Grenze, die gezeigt werden soll.
   * @param rechts Die rechte Grenze, die gezeigt werden soll.
   */
  public abstract void druckeZahlen(int links, int rechts);

  /**
   * Zeige die Zahlen in der Textkonsole. Es handelt sich um eine Hilfsmethode, um
   * die Sortieralgorithmen besser verstehen zu können.
   */
  public abstract void druckeZahlen();
}
