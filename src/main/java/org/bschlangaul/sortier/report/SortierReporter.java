package org.bschlangaul.sortier.report;

import org.bschlangaul.helfer.report.Reporter;

public class SortierReporter extends Reporter {

  SortierAusgabe sortierAusgabe = new StummeSortierAusgabe();
  public int[] zahlen;

  @Override
  public void aktiviereKonsolenAusgabe() {
    super.aktiviereKonsolenAusgabe();
    sortierAusgabe = new KonsolenSortierAusgabe();
  }

  @Override
  public void aktiviereTexAusgabe() {
    super.aktiviereTexAusgabe();
    // Es gibt noch keine TeX-Ausgabe.
    sortierAusgabe = new KonsolenSortierAusgabe();
  }

  /**
   * Zeige einen Ausschnitt der Zahlen in der Textkonsole.
   *
   * @param links  Die linke Grenze, ab der gezeigt werden soll.
   * @param rechts Die rechte Grenze, bis zu der gezeigt werden soll.
   */
  public void zahlenFeldAusschnitt(int links, int rechts) {
    sortierAusgabe.zahlenFeldAusschnitt(zahlen, links, rechts);
  }

  /**
   * Zeige die Zahlen.
   */
  public void zahlenFeld() {
    sortierAusgabe.zahlenFeld(zahlen, null);
  }

  /**
   * Zeige die Zahlen mit einer Erklärung.
   */
  public void zahlenFeld(String erklaerung) {
    sortierAusgabe.zahlenFeld(zahlen, erklaerung);
  }

  public void vertauschen(int index1, int index2) {
    sortierAusgabe.vertauschen(zahlen, index1, index2);
  }
}
