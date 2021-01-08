package org.bschlangaul.examen.examen_46115.jahr_2014.fruehjahr.suchbaum;

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
