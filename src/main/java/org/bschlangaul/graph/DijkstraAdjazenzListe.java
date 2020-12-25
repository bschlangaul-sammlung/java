package org.bschlangaul.graph;

import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashSet;

/**
 * Implementierung des Algorithmus von Dijkstra mit einer
 * Prioritätswarteschlange.
 *
 * Nach einem Kode-Beispiel auf <a href=
 * "https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue">geeksforgeeks.org</a>
 */
public class DijkstraAdjazenzListe extends GraphAdjazenzListe {
  private int kürzesteEntfernungen[];
  private Set<Integer> besucht;

  /**
   * Warteschlange
   */
  private PriorityQueue<Kante> warteschlange;

  /**
   * Mit diesem Konstruktur wird die Adjazenzmatrix durch das einfache
   * Graphenformat erzeugt.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public DijkstraAdjazenzListe(String graphenFormat) {
    super(graphenFormat);
    kürzesteEntfernungen = new int[gibKnotenAnzahl()];
    besucht = new HashSet<Integer>();
    warteschlange = new PriorityQueue<Kante>(gibKnotenAnzahl(), new Kante(0,0));
  }

  /**
   * Hauptmethode des Algorithmuses.
   *
   * @param adjazenzListe Die Adjazenz-Liste.
   * @param startKnotenName Der Name des Startknoten
   */
  public void sucheKürzestenPfad(String startKnotenName) {

    int startKnotenNr = gibKnotenNummer(startKnotenName);

    for (int i = 0; i < gibKnotenAnzahl(); i++)
      kürzesteEntfernungen[i] = Integer.MAX_VALUE;

    // Add source node to the priority queue
    warteschlange.add(new Kante(startKnotenNr, 0));

    // Distance to the source is 0
    kürzesteEntfernungen[startKnotenNr] = 0;
    while (besucht.size() != gibKnotenAnzahl()) {

      // remove the minimum distance node
      // from the priority queue
      int knotenNummer = warteschlange.remove().nachNr;

      // adding the node whose distance is
      // finalized
      besucht.add(knotenNummer);

      findeNachbarn(knotenNummer);
    }
  }

  /**
   * Finde Nachbarn.
   *
   * @param knotenNummer Die Nummer des Knoten.
   */
  private void findeNachbarn(int knotenNummer) {
    int kantenEntfernung = -1;
    int neueEntfernung = -1;

    // All the neighbors of v
    for (int i = 0; i < liste.get(knotenNummer).size(); i++) {
      Kante kante = liste.get(knotenNummer).get(i);

      // If current node hasn't already been processed
      if (!besucht.contains(kante.nachNr)) {
        kantenEntfernung = kante.gewicht;
        neueEntfernung = kürzesteEntfernungen[knotenNummer] + kantenEntfernung;

        // If new distance is cheaper in cost
        if (neueEntfernung < kürzesteEntfernungen[kante.nachNr])
          kürzesteEntfernungen[kante.nachNr] = neueEntfernung;

        // Add the current node to the queue
        warteschlange.add(new Kante(kante.nachNr, kürzesteEntfernungen[kante.nachNr]));
      }
    }
  }

  public int gibEntfernung(String knotenName) {
    return kürzesteEntfernungen[gibKnotenNummer(knotenName)];
  }
}
