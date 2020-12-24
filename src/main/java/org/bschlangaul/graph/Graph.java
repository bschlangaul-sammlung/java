package org.bschlangaul.graph;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Graph {

  private ArrayList<Knoten> knotenSpeicher;
  private HashMap<String, Integer> knotenIndex;

  public Graph() {
    knotenSpeicher = new ArrayList<Knoten>();
    knotenIndex = new HashMap<String, Integer>();
  }

  public int setzeKnoten(String knotenName) {
    int index = gibKnotenNummer(knotenName);
    if (index > -1)
      return index;
    knotenSpeicher.add(new Knoten(knotenName));
    index = knotenSpeicher.size() - 1;
    knotenIndex.put(knotenName, index);
    return index;
  }

  /**
   * Gib die Index-Nummer des Knoten.
   *
   * @param knotenName Der Name des Knoten.
   *
   * @return Die Knoten-Index-Nummer beginnend ab 0. Falls kein Knoten mit dem
   *         Namen existiert, wird -1 ausgegeben.
   */
  public int gibKnotenNummer(String knotenName) {
    if (knotenIndex.get(knotenName) != null) {
      return knotenIndex.get(knotenName);
    }
    return -1;
  }

  public String gibKnotenName(int knotenNummer) {
    Knoten knoten = knotenSpeicher.get(knotenNummer);
    return knoten.gibName();
  }

  public int gibKnotenAnzahl() {
    return knotenSpeicher.size();
  }

  public abstract void setzeKante(String von, String nach, int gewicht, boolean gerichtet);

  /**
   * Füge eine gerichtete Kante in den Graphen ein.
   *
   * Eine Kante ist durch einen Anfangsknoten und einen Endknoten festgelegt und
   * hat eine Gewichtung.
   *
   * @param von     Der Name des Anfangsknotens.
   * @param nach    Der Name des Endknotens.
   * @param gewicht Die Gewichtung der Kante als Ganzzahl.
   */
  public void setzeGerichteteKante(String von, String nach, int gewicht) {
    setzeKante(von, nach, gewicht, true);
  }

  /**
   * Füge eine ungerichtete Kante in den Graphen ein.
   *
   * Eine Kante ist durch einen Anfangsknoten und einen Endknoten festgelegt und
   * hat eine Gewichtung.
   *
   * @param von     Der Name des Anfangsknotens.
   * @param nach    Der Name des Endknotens.
   * @param gewicht Die Gewichtung der Kante als Ganzzahl.
   */
  public void setzeUngerichteteKante(String von, String nach, int gewicht) {
    setzeKante(von, nach, gewicht, false);
  }


}
