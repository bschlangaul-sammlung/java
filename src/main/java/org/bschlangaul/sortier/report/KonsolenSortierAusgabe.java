package org.bschlangaul.sortier.report;

import org.bschlangaul.helfer.Farbe;

class Markierung {
  int index;
  String präfix = "";
  String suffix = "";

  /**
   * Ordne die Markierungen ihrer Index-Position in ein Feld, das genau so lange
   * ist wie das Zahlenfeld.
   *
   * @param zahlen           Das Zahlen-Feld des Sortieralgorithmus.
   * @param roheMarkierungen Ein Feld mit ungeordneten Markierungen. Dieses Feld
   *                         ist möglicherweise viel kleiner als das Zahlenfeld.
   * @return Ein Feld mit Markierungen, dass der Länge des Zahlenfelds entspricht.
   */
  static Markierung[] ordne(int[] zahlen, Markierung[] roheMarkierungen) {
    Markierung[] markierungen = new Markierung[zahlen.length];
    for (Markierung neu : roheMarkierungen) {
      if (markierungen[neu.index] != null) {
        Markierung alt = markierungen[neu.index];
        if (!neu.präfix.equals("")) {
          alt.präfix = neu.präfix;
        }
        if (!neu.suffix.equals("")) {
          alt.suffix = neu.suffix;
        }
      } else {
        markierungen[neu.index] = neu;
      }
    }
    return markierungen;
  }

  /**
   * Gib die Zahl als Text mit vorangestellten Präfix und hinten angestellen
   * Suffix.
   *
   * @param zahl Eine Zahl
   *
   * @return Die Zahl als Text mit vorangestellten Präfix und hinten angestellen
   *         Suffix.
   */
  String gib(int zahl) {
    return präfix + zahl + suffix;
  }
}

public class KonsolenSortierAusgabe extends SortierAusgabe {

  public KonsolenSortierAusgabe(int zahlen[]) {
    super(zahlen);
  }

  /**
   * Nur das Feld ohne den Erklärungstext rechts.
   */
  private void rohesFeld(int links, int rechts, Markierung... roheMarkierungen) {
    Markierung[] markierungen = Markierung.ordne(zahlen, roheMarkierungen);
    int breite = maxZahlenBreite + 1;
    if (links > 0) {
      System.out.print(" ".repeat(breite * links));
    }

    for (int i = links; i <= rechts; i++) {
      if (markierungen[i] != null) {
        druckeZahl(markierungen[i].gib(zahlen[i]));
      } else {
        druckeZahl(zahlen[i]);
      }
    }

    if (rechts < zahlen.length - 1) {
      System.out.print(" ".repeat(breite * (zahlen.length - 1 - rechts)));
    }
  }

  private Markierung präfix(int index, String zeichen) {
    Markierung m = new Markierung();
    m.index = index;
    m.präfix = zeichen;
    return m;
  }

  private Markierung suffix(int index, String zeichen) {
    Markierung m = new Markierung();
    m.index = index;
    m.suffix = zeichen;
    return m;
  }

  public void feld(int links, int rechts, String erklärung) {
    rohesFeld(links, rechts);
    druckeZeilenumbruch(erklärung);
  }

  public void feldMarkierung(int links, int rechts, int markierung, String erklärung) {
    rohesFeld(0, zahlen.length - 1, suffix(markierung, "*"));
    druckeZeilenumbruch(erklärung);
  }

  public void vertauschen(int links, int rechts, int index1, int index2, String erklärung) {
    rohesFeld(links, rechts, präfix(index1, ">"), suffix(index2, "<"));
    druckeZeilenumbruch(erklärung);
  }
}
