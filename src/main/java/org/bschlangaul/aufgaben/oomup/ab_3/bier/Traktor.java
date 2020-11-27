package org.bschlangaul.aufgaben.oomup.ab_3.bier;

public class Traktor extends Gegner {

  public Traktor() {
    super();

    bilderAnzahl = 2;

    int traktorNummer = BierWorld.gibZufallsBereich(1, 4);
    String traktorName = "traktor" + traktorNummer;

    if (nachLinks) {
      bilderName = traktorName +  "_links_";
    } else {
      bilderName = traktorName + "_rechts_";
    }

    sammleBilder();
  }
}
