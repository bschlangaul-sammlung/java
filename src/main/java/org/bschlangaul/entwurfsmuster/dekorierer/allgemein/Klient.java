package org.bschlangaul.entwurfsmuster.dekorierer.allgemein;

public class Klient {
  public static void main(String[] args) {
    Komponente komponente = new KonkreteKomponente();
    komponente.agiere();
    // KonkreteKomponente agiert.

    komponente = new KonkreterDekoriererA(komponente);
    komponente.agiere();
    // KonkreteKomponente agiert.
    // KonkreterDekoriererA agiert!

    komponente = new KonkreterDekoriererB(new KonkreteKomponente());
    komponente.agiere();
    // KonkreteKomponenteA agiert.
    // KonkreterDekoriererB agiert mit dem neuen Zustand: 999
  }
}
