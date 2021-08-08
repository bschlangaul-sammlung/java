package org.bschlangaul.sortier.report;

import org.bschlangaul.helfer.Farbe;

public class TerminalReporter extends SortierReporter {

  /**
   * {@inheritDoc}
   */
  public void berichte(int links, int rechts) {
    for (int i = links; i <= rechts; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println();
  }

  /**
   * {@inheritDoc}
   */
  public void berichte() {
    berichte("");
  }

  /**
   * {@inheritDoc}
   */
  public void berichte(String erklaerung) {
    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println(erklaerung);
  }

  public void berichteVertauschen(int index1, int index2) {
    for (int i = 0; i < zahlen.length; i++) {
      if (i == index1) {
        System.out.print(Farbe.gelb(">" + zahlen[i]) + " ");
      } else if (i == index2) {
        System.out.print(Farbe.gelb(zahlen[i] + "<") + " ");
      } else {
        System.out.print(zahlen[i] + " ");
      }
    }
    System.out.println();
  }

}
