package org.bschlangaul.graph;

import java.util.List;
import java.util.ArrayList;

// https://www.softwaretestinghelp.com/java-graph-tutorial/

// Graph class
class GraphAdjazenzListe extends Graph {

  static class Kante {
    public int nachNr;
    public int gewicht;

    Kante(int nachNr, int gewicht) {
      this.nachNr = nachNr;
      this.gewicht = gewicht;
    }
  }

  private List<List<Kante>> liste = new ArrayList<List<Kante>>();

  public GraphAdjazenzListe(int knotenAnzahl) {
    for (int i = 0; i < knotenAnzahl; i++) {
      liste.add(i, new ArrayList<Kante>());
    }
  }

  public void setzeKante(String von, String nach, int gewicht, boolean gerichtet) {
    int vonNummer = setzeKnoten(von);
    int nachNummer = setzeKnoten(nach);
    if (gibKanteGewicht(von, nach) == -1)
      liste.get(vonNummer).add(new Kante(nachNummer, gewicht));
    if (!gerichtet && gibKanteGewicht(nach, von) == -1)
      liste.get(nachNummer).add(new Kante(vonNummer, gewicht));
  }

  public int gibKanteGewicht(String von, String nach) {
    int vonNummer, nachNummer;
    vonNummer = gibKnotenNummer(von);
    nachNummer = gibKnotenNummer(nach);
    if (vonNummer == -1 || nachNummer == -1)
      return -1;
    List<Kante> unterListe = liste.get(vonNummer);
    for (int i = 0; i < unterListe.size(); i++) {
      Kante kante = unterListe.get(i);
      if (kante.nachNr == nachNummer)
        return kante.gewicht;
    }
    return -1;
  }

}
