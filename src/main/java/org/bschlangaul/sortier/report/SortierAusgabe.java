package org.bschlangaul.sortier.report;

import java.util.Arrays;

abstract class SortierAusgabe {

  protected int[] zahlen;

  protected int maxZahlenBreite;

  public SortierAusgabe(int[] zahlen) {
    this.zahlen = zahlen;
    maxZahlenBreite = gibMaxZahlenBreite(zahlen);
  }

  private int gibMaxZahlenBreite(int[] zahlen) {
    if (zahlen.length == 0) {
      return 0;
    }
    int min = Arrays.stream(zahlen).min().getAsInt();
    int max = Arrays.stream(zahlen).max().getAsInt();

    int minLänge = String.valueOf(min).length();
    int maxLänge = String.valueOf(max).length();

    return Math.max(minLänge, maxLänge);
  }

  protected String formatiereZahl(String zahl, String präfix) {
    // Damit ein zusätzliches Zeichen wie <, >, * berücksichtigt werden kann.
    String präfixZeichen = " ";
    if (präfix != null) {
      präfixZeichen = präfix;
    }
    // „-“ für angehängte Leerzeichen.
    return präfixZeichen + String.format("%-" + maxZahlenBreite + "s", zahl) + " ";
  }

  protected String formatiereZahl(int zahl, String präfix) {
    return formatiereZahl(String.valueOf(zahl), präfix);
  }

  protected String formatiereZahl(int zahl) {
    return formatiereZahl(String.valueOf(zahl), null);
  }

  protected String formatiereZahl(String zahl) {
    return formatiereZahl(zahl, null);
  }

  protected void druckeZahl(String zahl, String präfix) {
    System.out.print(formatiereZahl(zahl, präfix));
  }

  protected void druckeZahl(int zahl, String präfix) {
    System.out.print(formatiereZahl(zahl, präfix));
  }

  protected void druckeZahl(String zahl) {
    System.out.print(formatiereZahl(zahl));
  }

  protected void druckeZahl(int zahl) {
    System.out.print(formatiereZahl(zahl));
  }

  protected void druckeZeilenumbruch(String suffix) {
    if (suffix != null) {
      System.out.println("(" + suffix + ")");
    } else {
      System.out.println();
    }
  }

  protected void druckeZeilenumbruch() {
    druckeZeilenumbruch(null);
  }

  public abstract void feldAusschnitt(int links, int rechts, String suffix);

  public abstract void feld(String suffix);

  public abstract void feldMarkierung(int markierung, String suffix);

  public abstract void vertauschen(int index1, int index2, String suffix);
}
