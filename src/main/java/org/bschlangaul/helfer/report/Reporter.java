package org.bschlangaul.helfer.report;

/**
 * Im Strategie-Entwurfsmuster entspricht diese Klasse der Kontext-Klasse.
 */
public class Reporter {

  /**
   * Standardmäßig soll der Reporter stumm sein.
   */
  Ausgabe ausgabe = new StummeAusgabe();

  /**
   * Aktiviere die Konsolen-Ausgabe.
   */
  public void aktiviereKonsolenAusgabe() {
    ausgabe = new KonsolenAusgabe();
  }

  /**
   * Aktivere die TeX-Ausgabe. Der Reporter produziert dann TeX-Markup.
   */
  public void aktiviereTexAusgabe() {
    ausgabe = new TexAusgabe();
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
