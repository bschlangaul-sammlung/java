package org.bschlangaul.sortier.report;

interface SortierAusgabe {
  public void zahlenFeldAusschnitt(int[] zahlen, int links, int rechts);

  public void zahlenFeld(int[] zahlen, String erklaerung);

  public void vertauschen(int[] zahlen, int index1, int index2);
}
