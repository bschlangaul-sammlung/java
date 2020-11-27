package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

class KonkreterDekorierer1 extends Dekorierer {
  public KonkreterDekorierer1(Komponente komponent) {
    super(komponent);
  }

  /**
   * agiere() der dekorierten Kompontente aufrufen vor
   * der nach der eigenen Funktionalität.
   */
  public void agiere() {
    komponente.agiere();
    // eigene Funktionalität:
    System.out.println("KonkreterDekorierer1 agiert!");
  }

}
