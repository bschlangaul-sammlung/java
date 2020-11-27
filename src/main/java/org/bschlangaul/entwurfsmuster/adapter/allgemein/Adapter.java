package org.bschlangaul.entwurfsmuster.adapter.allgemein;

public class Adapter extends Dienst implements Ziel {

  @Override
  public void agiere() {
      System.out.print("agiere: ");
      agiereSpeziell();
  }
}
