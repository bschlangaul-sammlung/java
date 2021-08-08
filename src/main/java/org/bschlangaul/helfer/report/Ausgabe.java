package org.bschlangaul.helfer.report;

/**
 * Im Strategie-Entwurfsmuster entspricht diese Klasse der Strategieklasse, also
 * der Klasse, die die Schnittstelle für die konkreten Strategieklassen liefert.
 */
interface Ausgabe {
  public String überschrift(String text);

  public String tabelle(String[] kopfZeile, String[][] zeilen);
}
