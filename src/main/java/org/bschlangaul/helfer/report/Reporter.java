package org.bschlangaul.helfer.report;

public abstract class Reporter {

  /**
   * Höhere Zahlen sind redseliger (verbose).
   */
  public static int redseligkeit = 0;

  private void gibAus(String ausgabe) {
    if (ausgabe != null) {
      System.out.println(ausgabe);
    }
  }

  public abstract String erzeugeÜberschrift(String überschrift);

  public void überschrift(String überschrift) {
    überschrift(überschrift, 0);
  }

  public void überschrift(String überschrift, int redselig) {
    if (redselig <= redseligkeit) {
      gibAus(erzeugeÜberschrift(überschrift));
    }
  }

  public abstract String erzeugeTabelle(String[] kopfZeile, String[][] zeilen);

  public void tabelle(String[] kopfZeile, String[][] zeilen) {
    gibAus(erzeugeTabelle(kopfZeile, zeilen));
  }

}
