package org.bschlangaul.entwurfsmuster.stellvertreter.allgemein;

public class KonkretesSubjekt implements Subjekt {

  public KonkretesSubjekt() {
    konfiguriere();
  }

  @Override
  public void agiere() {
    System.out.println("agiere ...");
  }

  private void konfiguriere() {
    System.out.println("Lade aufwändige Konfiguration ...");
  }

}
