package org.bschlangaul.sortier.report;

public class KonsolenSortierAusgabe extends SortierAusgabe {

  public KonsolenSortierAusgabe(int zahlen[]) {
    super(zahlen);
  }

  public void feldAusschnitt(int links, int rechts, String suffix) {
    int breite = maxZahlenBreite + 2;
    if (links > 0) {
      System.out.print(" ".repeat(breite * links));
    }

    for (int i = links; i <= rechts; i++) {
      druckeZahl(zahlen[i]);
    }

    if (rechts < zahlen.length - 1) {
      System.out.print(" ".repeat(breite * (zahlen.length - 1 - rechts)));
    }

    if (suffix != null) {
      suffix = suffix +  " (i " + links + "-" + rechts + ")";
    }

    druckeZeilenumbruch(suffix);
  }

  public void feld(String suffix) {
    for (int i = 0; i < zahlen.length; i++) {
      druckeZahl(zahlen[i]);
    }
    druckeZeilenumbruch(suffix);
  }

  public void feldMarkierung(int markierung, String suffix) {
    for (int i = 0; i < zahlen.length; i++) {
      if (i == markierung) {
        druckeZahl(zahlen[i], null, "*");
      } else {
        druckeZahl(zahlen[i]);
      }
    }
    druckeZeilenumbruch(suffix);
  }

  public void vertauschen(int index1, int index2, String suffix) {
    for (int i = 0; i < zahlen.length; i++) {
      int von, nach;

      if (index2 < index1) {
        von = index2;
        nach = index1;
      } else {
        von = index1;
        nach = index2;
      }

      if (i == von) {
        druckeZahl(zahlen[i], ">", null);
      } else if (i == nach) {
        druckeZahl(zahlen[i], null, "<");
      } else {
        druckeZahl(zahlen[i]);
      }
    }
    druckeZeilenumbruch(suffix);
  }
}
