package org.bschlangaul.entwurfsmuster.stellvertreter.allgemein;

public class Stellvertreter implements Subjekt {
  private static Subjekt subjekt;

  @Override
  public void agiere() {
    if (subjekt == null) {
      subjekt = new KonkretesSubjekt();
    }
    subjekt.agiere();
  }
}
