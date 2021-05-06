package org.bschlangaul.entwurfsmuster.zustand.allgemein;

interface Zustand {
  public void agiere();
}

class KonkreterZustandA implements Zustand {
  public void agiere() {
    System.out.println("KonkreterZustandA");
  }
}

class KonkreterZustandB implements Zustand {
  public void agiere() {
    System.out.println("KonkreterZustandB");
  }
}
