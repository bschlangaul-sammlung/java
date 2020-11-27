package org.bschlangaul.entwurfsmuster.beobachter.hauer;

class FamilieMeier implements Abonnent {

  public void erhalteZeitung(Zeitung zeitung) {
    System.out.println("Familie Meier erhielt die aktuelle Zeitung: " + zeitung.getTitel());
  }
}
