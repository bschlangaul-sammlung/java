package org.bschlangaul.graph;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Graph {

  private ArrayList<Knoten> knotenListe;
  private HashMap<String, Integer> knotenIndex;

  public Graph() {
    knotenListe = new ArrayList<Knoten>();
    knotenIndex = new HashMap<String, Integer>();
  }

  public int setzeKnoten(String knotenName) {
    int index = gibKnotenNummer(knotenName);
    if (index > -1)
      return index;
    knotenListe.add(new Knoten(knotenName));
    index = knotenListe.size() - 1;
    knotenIndex.put(knotenName, index);
    return index;
  }

  /**
   * Gib die Index-Nummer des Knoten.
   *
   * Wenn ein Knoten mit diesem Name nicht bekannt ist, wird -1
   * zurückgegeben.
   *
   * @param knotenName Der Name des Knoten.
   *
   * @return Die Knoten-Index-Nummer beginnend ab 0. Falls kein Knoten
   *         mit dem Namen existiert, wird -1 ausgegeben.
   */
  public int gibKnotenNummer(String knotenName) {
    if (knotenIndex.get(knotenName) != null) {
      return knotenIndex.get(knotenName);
    }
    return -1;
  }

  public String gibKnotenName(int knotenNummer) {
    Knoten knoten = knotenListe.get(knotenNummer);
    return knoten.gibName();
  }


  public Knoten gibKnoten(int knotenNummer) {
    return knotenListe.get(knotenNummer);
  }

  /**
   * Gib alle Knotennamen als Feld zurück.
   */
  public String[] gibAlleKnotenNamen() {
    String[] ausgabe = new String[gibKnotenAnzahl()];
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      ausgabe[i] = knotenListe.get(i).gibName();
    }
    return ausgabe;
  }

  /**
   * Gibt die Anzahl der Knoten des Graphen.
   *
   * @return Die Anzahl der Knoten.
   */
  public int gibKnotenAnzahl() {
    return knotenListe.size();
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

  /**
   * Berechne die maximale Textbreite der Knotennamen.
   *
   * @return Die maximale Textbreite in Anzahl an Zeichen.
   */
  public int gibMaximaleKnotennameTextbreite() {
    int max = -1;
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      int länge = knotenListe.get(i).gibName().length();
      if (länge > max)
        max = länge;
    }
    return max;
  }

}
