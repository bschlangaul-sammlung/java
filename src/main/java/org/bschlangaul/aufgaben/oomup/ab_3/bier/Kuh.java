package org.bschlangaul.aufgaben.oomup.ab_3.bier;

public class Kuh extends Gegner {

  public Kuh() {
    super();

    bilderAnzahl = 3;

    if (nachLinks) {
      bilderName = "kuh_links_";
    } else {
      bilderName = "kuh_rechts_";
    }

    sammleBilder();
  }
}
