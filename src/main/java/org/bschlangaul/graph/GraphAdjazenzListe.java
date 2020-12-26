package org.bschlangaul.graph;

import java.util.List;
import java.util.ArrayList;

// https://www.softwaretestinghelp.com/java-graph-tutorial/

// Graph class
class GraphAdjazenzListe extends Graph {

  static class Kante{
    public int nachNr;
    public int gewicht;

    Kante(int nachNr, int gewicht) {
      this.nachNr = nachNr;
      this.gewicht = gewicht;
    }

  }

  protected List<List<Kante>> liste;

  /**
   * Konstruktor für Objekte der Klasse GraphAdjazenzListe.
   *
   * Die maximale Anzahl der Knoten wird dabei festgelegt.
   *
   * @param maximaleKnotenAnzahl Anzahl der maximal möglichen Knoten
   */
  public GraphAdjazenzListe(int maximaleKnotenAnzahl) {
    initialisiere(maximaleKnotenAnzahl);
  }

  /**
   * Mit diesem Konstruktur kann eine Adjazenzliste durch das einfache
   * Graphenformat erzeugt werden.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public GraphAdjazenzListe(String graphenFormat) {
    super(graphenFormat);
  }

  @Override
  protected void initialisiere(int maximaleAnzahlKnoten) {
    liste = new ArrayList<List<Kante>>();
    for (int i = 0; i < maximaleAnzahlKnoten; i++) {
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

  /**
   * Berechne das kleinste Einzel-Kantengewicht aller Kanten.
   *
   * Diese Methode ist nützlich für die negativen Zahlen. Dieser Wert ist z. B.
   * nützlich, wenn die Adjazenz-Matrix in der Konsole ausgegeben werden soll. Mit
   * Hilfe dieses Wertes kann die Breite der Tabelle bestimmt werden.
   *
   * @return Das Gewicht der Kante mit dem Minimalgewicht.
   */
  public int gibMinimalesGewicht() {
    int min = 0;
    for (List<Kante> unterListe : liste) {
      for (Kante kante : unterListe) {
        if (kante.gewicht < min)
          min = kante.gewicht;
      }
    }
    return min;
  }

  /**
   * Berechne das größte Einzel-Kantengewicht aller Kanten.
   *
   * Dieser Wert ist z. B. nützlich, wenn die Adjazenz-Matrix in der Konsole
   * ausgegeben werden soll. Mit Hilfe dieses Wertes kann die Breite der Tabelle
   * bestimmt werden.
   *
   * @return Das Gewicht der Kante mit dem Maximalgewicht.
   */
  public int gibMaximalesGewicht() {
    int max = 0;
    for (List<Kante> unterListe : liste) {
      for (Kante kante : unterListe) {
        if (kante.gewicht > max)
          max = kante.gewicht;
      }
    }
    return max;
  }

  public int gibMaximaleUnterListenTiefe() {
    int max = 0;
    for (List<Kante> unterListe : liste) {
      if (unterListe.size() > max) {
        max = unterListe.size();
      }

    }
    return max;
  }

}
