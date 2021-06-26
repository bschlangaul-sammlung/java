package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

/**
 * KonkreterDekoriererB fügt der Komponente einen neuen Zustand hinzu.
 */
class KonkreterDekoriererB extends Dekorierer {

  private int neuerZustand;

  public KonkreterDekoriererB(Komponente komponente) {
    super(komponente);
    neuerZustand = 999;
  }

  public void agiere() {
    komponente.agiere();
    System.out.println("KonkreterDekoriererB agiert mit dem neuen Zustand: " + neuerZustand);
  }
}
