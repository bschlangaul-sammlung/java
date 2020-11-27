package org.bschlangaul.entwurfsmuster.zustand.allgemein;

interface Zustand {
  public void agiere();
}

class KonkreterZustand1 implements Zustand {
  public void agiere() {
    System.out.println("KonkreterZustand1");
  }
}

class KonkreterZustand2 implements Zustand {
  public void agiere() {
    System.out.println("KonkreterZustand2");
  }
}

class KonkreterZustand3 implements Zustand {
  public void agiere() {
    System.out.println("KonkreterZustand3");
  }
}
