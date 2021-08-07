package org.bschlangaul.sortier.visualisierung;


public class TerminalReporter extends Reporter {

  /**
   * {@inheritDoc}
   */
  public void druckeZahlen(int links, int rechts) {
    for (int i = links; i <= rechts; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println();
  }

  /**
   * {@inheritDoc}
   */
  public void druckeZahlen() {
    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println();
  }

}
