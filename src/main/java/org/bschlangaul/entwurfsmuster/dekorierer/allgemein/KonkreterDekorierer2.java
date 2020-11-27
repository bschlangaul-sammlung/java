package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

/**
 * KonkreterDekorierer2 fügt der Komponente einen neuen Zustand hinzu.
 */
class KonkreterDekorierer2 extends Dekorierer {

  private int neuerZustand;

  public KonkreterDekorierer2(Komponente komponente) {
    super(komponente);
    neuerZustand = 999;
  }

  public void agiere() {
    komponente.agiere();
    System.out.println("KonkreterDekorierer2 agiert mit dem neuen Zustand: " + neuerZustand);
  }
}
