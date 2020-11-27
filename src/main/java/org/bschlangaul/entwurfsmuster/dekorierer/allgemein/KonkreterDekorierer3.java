package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

class KonkreterDekorierer3 extends Dekorierer {
  public KonkreterDekorierer3(Komponente komponent) {
    super(komponent);
  }

  public void agiere() {
    komponente.agiere();
    System.out.println("KonkreterDekorierer3 agiert mit neuer Aktion: " + neueAktion());
  }

  /**
   * KonkreterDekorierer3 fügt der Komponente eine neue Methode hinzu
   * und erweitert seine Schnittstelle nach außen.
   *
   * @return Eine zufällige Zahl.
   */
  public int neueAktion() {
    return (int) (Math.random() * 20);
  }
}
