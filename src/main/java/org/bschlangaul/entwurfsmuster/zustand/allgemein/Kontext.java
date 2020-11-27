package org.bschlangaul.entwurfsmuster.zustand.allgemein;

class Kontext {
  private Zustand zustand;

  public void agiere() {
    zustand.agiere();
  }
}
