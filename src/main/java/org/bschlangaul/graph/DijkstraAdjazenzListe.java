package org.bschlangaul.graph;

import java.util.Set;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Entfernung vom Startknoten zum Nach-Knoten.
 */
class Entfernung implements Comparator<Entfernung> {
  public int nachNr;
  public int entfernung;

  Entfernung(int nachNr, int entfernung) {
    this.nachNr = nachNr;
    this.entfernung = entfernung;
  }

  @Override
  public int compare(Entfernung entfernung1, Entfernung entfernung2) {
    if (entfernung1.entfernung < entfernung2.entfernung)
      return -1;
    if (entfernung1.entfernung > entfernung2.entfernung)
      return 1;
    return 0;
  }
}

/**
 * Implementierung des Algorithmus von Dijkstra mit einer
 * Prioritätswarteschlange.
 *
 * Nach einem Kode-Beispiel auf <a href=
 * "https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue">geeksforgeeks.org</a>
 */
public class DijkstraAdjazenzListe extends GraphAdjazenzListe {
  /**
   * In diesem Feld werden die kürzesten Entfernungen zu den einzelnen Knoten
   * gespeichert.
   */
  int[] kürzesteEntfernungen;

  /**
   * Feld, mit dem die Vorgänger-Knoten des kürzesten Pfads gespeichert werden.
   * Ein Vorgänger-Knoten des Pfads gibt ab, über welchen Knoten man auf kürzesten
   * Weg zum Knoten kommt.
   */
  int[] vorgänger;

  private Set<Integer> besucht;

  private static final int KEINE_VORGÄNGER = -1;

  /**
   * Mit diesem Konstruktur wird die Adjazenzmatrix durch das einfache
   * Graphenformat erzeugt.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public DijkstraAdjazenzListe(String graphenFormat) {
    super(graphenFormat);
    kürzesteEntfernungen = new int[gibKnotenAnzahl()];
    vorgänger = new int[gibKnotenAnzahl()];
    besucht = new HashSet<Integer>();
  }

  /**
   * Hauptmethode des Algorithmuses.
   *
   * @param adjazenzListe   Die Adjazenz-Liste.
   * @param startKnotenName Der Name des Startknoten
   */
  public void sucheKürzestenPfad(String startKnotenName) {
    PriorityQueue<Entfernung> warteschlange = new PriorityQueue<Entfernung>(gibKnotenAnzahl(), new Entfernung(0, 0));

    int startKnotenNr = gibKnotenNummer(startKnotenName);

    // Der Anfangsknoten hat keinen Vorgänger.
    vorgänger[startKnotenNr] = KEINE_VORGÄNGER;

    for (int i = 0; i < gibKnotenAnzahl(); i++)
      kürzesteEntfernungen[i] = Integer.MAX_VALUE;

    // Add source node to the priority queue
    warteschlange.add(new Entfernung(startKnotenNr, 0));

    // Distance to the source is 0
    kürzesteEntfernungen[startKnotenNr] = 0;
    while (besucht.size() != gibKnotenAnzahl()) {

      // remove the minimum distance node
      // from the priority queue
      int knotenNummer = warteschlange.remove().nachNr;

      // adding the node whose distance is
      // finalized
      besucht.add(knotenNummer);

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
          if (neueEntfernung < kürzesteEntfernungen[kante.nachNr]) {
            kürzesteEntfernungen[kante.nachNr] = neueEntfernung;
            vorgänger[kante.nachNr] = knotenNummer;
          }

          // Add the current node to the queue
          warteschlange.add(new Entfernung(kante.nachNr, kürzesteEntfernungen[kante.nachNr]));
        }
      }
    }
  }

  public int gibEntfernung(String knotenName) {
    return kürzesteEntfernungen[gibKnotenNummer(knotenName)];
  }

  public String gibVorgänger(String knotenName) {
    int knotenNummer = vorgänger[gibKnotenNummer(knotenName)];
    if (knotenNummer == -1)
      return knotenName;
    return gibKnotenName(knotenNummer);
  }

  public static void main(String[] args) {
    // DijkstraAdjazenzListe d = new DijkstraAdjazenzListe(
    // "a->b: 1; a->e: 7; b->c: 3; c->d: 8; c->e: 3; e->f: 1; c->f: 6; f->c: 1;
    // f->d: 3");

    // d.sucheKürzestenPfad("d");

    DijkstraAdjazenzListe d = new DijkstraAdjazenzListe(
        "A: 1 4; B: 3 5; C: 3 3; D: 0 2; E: 5 5; F: 5 1; G: 3 0; H: 6 3; I: 8 4; A -- B: 2; A -- C: 5; A -- D: 2; B -- C: 3; B -- E; C -- D: 3; C -- E; C -- F; C -- H; D -- G: 2; E -- I: 7; F -- G: 2; F -- H: 3; H -- I;");

    d.sucheKürzestenPfad("A");
  }

}
