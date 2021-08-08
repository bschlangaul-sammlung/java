package org.bschlangaul.sortier.report;

import org.bschlangaul.helfer.Farbe;

public class KonsolenSortierAusgabe implements SortierAusgabe {

  public void zahlenFeldAusschnitt(int[] zahlen, int links, int rechts) {
    for (int i = links; i <= rechts; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println();
  }

  public void zahlenFeld(int[] zahlen, String erklaerung) {
    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
    if (erklaerung != null) {
      System.out.println(erklaerung);
    } else {
      System.out.println();
    }
  }

  public void vertauschen(int[] zahlen, int index1, int index2) {
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
