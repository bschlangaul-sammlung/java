package org.bschlangaul.graph;

import java.util.List;
import java.util.ArrayList;

// https://www.softwaretestinghelp.com/java-graph-tutorial/

// Graph class
class AdjazenzListe extends KnotenSpeicher {

  static class Kante {
    public int nachNr;
    public int gewicht;

    Kante(int nachNr, int gewicht) {
      this.nachNr = nachNr;
      this.gewicht = gewicht;
    }
  }

  List<List<Kante>> liste = new ArrayList<List<Kante>>();

  public AdjazenzListe(int knotenAnzahl) {
    for (int i = 0; i < knotenAnzahl; i++) {
      liste.add(i, new ArrayList<Kante>());
    }
  }

  public void setzeKante(String von, String nach, int gewicht, boolean gerichtet) {
    int vonNummer = setzeKnoten(von);
    int nachNummer = setzeKnoten(nach);
    liste.get(vonNummer).add(new Kante(nachNummer, gewicht));
    if (!gerichtet) {
      liste.get(nachNummer).add(new Kante(vonNummer, gewicht));
    }
  }

  /**
   * Gib die Gewichtung einer Kante. Die Kante ist durch einen Anfangsknoten und
   * einen Endknoten festgelegt. Ist die Kante unbekannt, wird -1 ausgegeben.
   *
   * @param von  Name des Anfangsknotens
   * @param nach Name des Endknotens
   *
   * @return Gewichtung der Kante
   */
  public int gibKanteGewicht(String von, String nach) {
    int vonNummer, nachNummer;
    vonNummer = gibKnotenNummer(von);
    nachNummer = gibKnotenNummer(nach);
    List<Kante> unterListe = liste.get(vonNummer);
    for (int i = 0; i < unterListe.size(); i++) {
      Kante kante = unterListe.get(i);
      if (kante.nachNr == nachNummer)
        return kante.gewicht;
    }
    return -1;
  }

}
