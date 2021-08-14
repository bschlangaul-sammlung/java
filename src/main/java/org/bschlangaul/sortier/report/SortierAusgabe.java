package org.bschlangaul.sortier.report;

import java.util.Arrays;

import org.bschlangaul.helfer.Farbe;

abstract class SortierAusgabe {

  protected int[] zahlen;

  protected int maxZahlenBreite;

  public SortierAusgabe(int[] zahlen) {
    this.zahlen = zahlen;
    // 2 breiter wegen der Suffixe und dem Leerzeichen
    maxZahlenBreite = gibMaxZahlenBreite(zahlen) + 2;
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

  protected String formatiereZahl(String zahl) {
    char erstesZeichen = zahl.charAt(0);
    char letztesZeichen = zahl.charAt(zahl.length() - 1);
    if (erstesZeichen != '>') {
      zahl = " " + zahl;
    }

    int max = maxZahlenBreite;
    int zahlBreite = zahl.length();
    int anzahlLeerzeichen = max - zahlBreite;
    String leerzeichen = "";
    if (erstesZeichen == '>' || letztesZeichen == '<') {
      zahl = Farbe.gelb(zahl);
    }
    if (zahl.charAt(zahl.length() - 1) == '*') {
      zahl = Farbe.grün(zahl);
    }
    if (anzahlLeerzeichen > -1) {
      leerzeichen = " ".repeat(max - zahlBreite);
    }
    return zahl + leerzeichen;
  }

  protected String formatiereZahl(int zahl) {
    return formatiereZahl(String.valueOf(zahl));
  }

  protected void druckeZahl(String zahl) {
    System.out.print(formatiereZahl(zahl));
  }

  protected void druckeZahl(int zahl) {
    System.out.print(formatiereZahl(zahl));
  }

  protected void druckeZeilenumbruch(String suffix) {
    if (suffix != null) {
      System.out.println(" " + suffix);
    } else {
      System.out.println();
    }
  }

  protected void druckeZeilenumbruch() {
    druckeZeilenumbruch(null);
  }

  public abstract void feld(int links, int rechts, String erklärung);

  public void feld(String erklärung) {
    feld(0, zahlen.length - 1, erklärung);
  }

  public abstract void feldMarkierung(int links, int rechts, int markierung, String erklärung);

  public void feldMarkierung(int markierung, String erklärung) {
    feldMarkierung(0, zahlen.length - 1, markierung, erklärung);
  }

  public abstract void vertauschen(int links, int rechts, int index1, int index2, String erklärung);

  public void vertauschen(int index1, int index2, String erklärung) {
    vertauschen(0, zahlen.length - 1, index1, index2, erklärung);
  }
}
