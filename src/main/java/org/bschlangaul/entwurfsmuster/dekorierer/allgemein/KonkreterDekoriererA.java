package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

class KonkreterDekoriererA extends Dekorierer {
  public KonkreterDekoriererA(Komponente komponent) {
    super(komponent);
  }

  /**
   * agiere() der dekorierten Kompontente aufrufen vor
   * der nach der eigenen Funktionalität.
   */
  public void agiere() {
    komponente.agiere();
    // eigene Funktionalität:
    System.out.println("KonkreterDekoriererA agiert!");
  }

}
