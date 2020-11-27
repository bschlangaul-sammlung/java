package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

public class Klient {

  public static void main(String[] args) {
    Komponente kompA = new KonkreteKomponenteA();
    kompA.agiere();
    // KonkreteKomponenteA agiert.

    kompA = new KonkreterDekorierer1(kompA);
    kompA.agiere();
    // KonkreteKomponenteA agiert.
    // KonkreterDekorierer1 agiert!

    Komponente kompB = new KonkreterDekorierer3(new KonkreterDekorierer2(new KonkreteKomponenteB()));
    kompB.agiere();
    // KonkreteKomponenteB agiert.
    // KonkreterDekorierer2 agiert mit dem neuen Zustand: 999
    // KonkreterDekorierer3 agiert mit neuer Aktion: 11
  }
}
