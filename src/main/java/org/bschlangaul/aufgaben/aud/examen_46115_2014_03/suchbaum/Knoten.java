package org.bschlangaul.aufgaben.aud.examen_46115_2014_03.suchbaum;

class Knoten {
  public int wert;
  public Knoten links;
  public Knoten rechts;
  public Knoten elternKnoten;

  Knoten(int wert) {
    this.wert = wert;
    links = null;
    rechts = null;
    elternKnoten = null;
  }

  public Knoten findeMiniumRechterTeilbaum() {
    if (rechts != null) {
      Knoten minimumKonten = rechts;
      while (minimumKonten.links != null) {
        minimumKonten = minimumKonten.links;
      }
      return minimumKonten;
    }
    return null;
  }

  public void anhängen (Knoten knoten) {
    if (knoten.wert < wert) {
      links = knoten;
    } else {
      rechts = knoten;
    }
  }
}
