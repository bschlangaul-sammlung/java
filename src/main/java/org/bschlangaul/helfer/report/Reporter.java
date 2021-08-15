package org.bschlangaul.helfer.report;

/**
 * Die Klasse Reporter ist die Basisklasse für alle spezialisierten
 * Reporter-Klassen (z. B. GraphenReporter, BaumReporter, SortierReporter).
 * Instanzen der Klassen werden mittels Delegation als Attribut
 * <em>berichte</em> in die Algorithmen-Klassen eingebunden.
 *
 * Im Strategie-Entwurfsmuster entspricht diese Klasse der Kontext-Klasse.
 */
public class Reporter {

  /**
   * Standardmäßig soll der Reporter stumm sein.
   */
  protected Ausgabe ausgabe = new StummeAusgabe();

  protected KonsolenAusgabe konsolenAusgabe = new KonsolenAusgabe();

  protected TexAusgabe texAusgabe = new TexAusgabe();

  /**
   * Aktiviere die Konsolen-Ausgabe.
   */
  public void aktiviereKonsolenAusgabe() {
    ausgabe = konsolenAusgabe;
  }

  /**
   * Aktivere die TeX-Ausgabe. Der Reporter produziert dann TeX-Markup.
   */
  public void aktiviereTexAusgabe() {
    ausgabe = texAusgabe;
  }

  /**
   * Höhere Zahlen sind redseliger (verbose).
   */
  public static int redseligkeit = 0;

  private void gibAus(String ausgabe) {
    if (ausgabe != null) {
      System.out.println(ausgabe);
    }
  }

  public void überschrift(String überschrift) {
    überschrift(überschrift, 0);
  }

  public void überschrift(String überschrift, int redselig) {
    if (redselig <= redseligkeit) {
      gibAus(ausgabe.überschrift(überschrift));
    }
  }

  public void tabelle(String[] kopfZeile, String[][] zeilen) {
    gibAus(ausgabe.tabelle(kopfZeile, zeilen));
  }

}
