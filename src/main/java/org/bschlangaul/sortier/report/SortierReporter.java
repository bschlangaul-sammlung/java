package org.bschlangaul.sortier.report;

import org.bschlangaul.helfer.report.Reporter;

public class SortierReporter extends Reporter {

  private SortierAusgabe sortierAusgabe;
  private int[] zahlen;

  public SortierReporter() {
    sortierAusgabe = new StummeSortierAusgabe(new int[] {});
  }

  @Override
  public void aktiviereKonsolenAusgabe() {
    super.aktiviereKonsolenAusgabe();
    sortierAusgabe = new KonsolenSortierAusgabe(zahlen);
  }

  @Override
  public void aktiviereTexAusgabe() {
    super.aktiviereTexAusgabe();
    // Es gibt noch keine TeX-Ausgabe.
    sortierAusgabe = new KonsolenSortierAusgabe(zahlen);
  }

  public void setzeZahlen(int[] zahlen) {
    this.zahlen = zahlen;
  }

  /**
   * Zeige einen Ausschnitt des zu zeigenden Felds in der Textkonsole.
   *
   * @param links     Die linke Grenze, ab der gezeigt werden soll.
   * @param rechts    Die rechte Grenze, bis zu der gezeigt werden soll.
   * @param erklärung Ein Erklärungstext, der zusätzlich zum Feld gezeigt werden
   *                  soll.
   */
  public void feldAusschnitt(int links, int rechts, String erklärung) {
    String indexAusschnitt = "";
    if (links != 0 || rechts != zahlen.length - 1) {
      indexAusschnitt = sortierAusgabe.indexAusschnitt(links, rechts);
    }
    sortierAusgabe.feld(links, rechts, erklärung + indexAusschnitt);
  }

  /**
   * Zeige einen Ausschnitt des zu zeigenden Felds in der Textkonsole.
   *
   * @param links  Die linke Grenze, ab der gezeigt werden soll.
   * @param rechts Die rechte Grenze, bis zu der gezeigt werden soll.
   */
  public void feldAusschnitt(int links, int rechts) {
    sortierAusgabe.feld(links, rechts, null);
  }

  /**
   * Zeige das zu sortierene Feld.
   */
  public void feld() {
    sortierAusgabe.feld(null);
  }

  /**
   * Zeige das Feld mit einem Erklärungstext.
   *
   * @param erklärung Ein Erklärungstext, der zusätzlich zum Feld gezeigt werden
   *                  soll.
   */
  public void feld(String erklärung) {
    sortierAusgabe.feld(erklärung);
  }

  /**
   * Zeige das Feld mit einem markierten Element.
   *
   * @param links      Die linke Grenze, ab der gezeigt werden soll.
   * @param rechts     Die rechte Grenze, bis zu der gezeigt werden soll.
   * @param markierung Die Indexnummer, des Feldeintrags, der markiert werden
   *                   soll.
   */
  public void feldMarkierung(int links, int rechts, int markierung) {
    sortierAusgabe.feldMarkierung(links, rechts, markierung, "markiere" + sortierAusgabe.index(markierung));
  }

  /**
   * Zeige das Feld mit einem markierten Element.
   *
   * @param links  Die linke Grenze, ab der gezeigt werden soll.
   * @param rechts Die rechte Grenze, bis zu der gezeigt werden soll.
   * @param grenze Die rechte Grenze der Teilung
   */
  public void feldTeilung(int links, int rechts, int grenze) {
    sortierAusgabe.feldTeilung(links, rechts, grenze, "teile" + sortierAusgabe.index(grenze));
  }

  /**
   * Zeige das Feld mit einem markierten Element.
   *
   * @param markierung Die Indexnummer, des Feldeintrags, der markiert werden
   *                   soll.
   */
  public void feldMarkierung(int markierung) {
    feldMarkierung(0, zahlen.length - 1, markierung);
  }

  public void vertauschen(int links, int rechts, int index1, int index2) {
    int min, max;
    if (index2 < index1) {
      min = index2;
      max = index1;
    } else {
      min = index1;
      max = index2;
    }
    sortierAusgabe.vertauschen(links, rechts, min, max, "vertausche" + sortierAusgabe.indexAusschnitt(min, max, "<>"));
  }

  public void vertauschen(int index1, int index2) {
    vertauschen(0, zahlen.length - 1, index1, index2);
  }
}
