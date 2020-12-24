package org.bschlangaul.graph;

import java.util.Set;
import java.util.PriorityQueue;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Comparator;

public class AdjazenzListe {

  /**
   *
   */
  List<List<Element>> adjazenzListe;


  /**
   * Finde Nachbarn.
   *
   * @param u Die Nummer des Knoten.
   */
  private void findeNachbarn(int u) {
    int edgeDistance = -1;
    int newDistance = -1;

    // All the neighbors of v
    for (int i = 0; i < adjazenzListe.get(u).size(); i++) {
      Element v = adjazenzListe.get(u).get(i);

      // If current node hasn't already been processed
      if (!settled.contains(v.nummer)) {
        edgeDistance = v.gewicht;
        newDistance = dist[u] + edgeDistance;

        // If new distance is cheaper in cost
        if (newDistance < dist[v.nummer])
          dist[v.nummer] = newDistance;

        // Add the current node to the queue
        warteschlange.add(new Element(v.nummer, dist[v.nummer]));
      }
    }
  }

}

/**
 * Der Knoten
 *
 * Die Klasse heißt Element, damit sie nicht mit der Klasse Knoten in Konflikt kommt.
 */
class Element implements Comparator<Element> {

  /**
   * Die Nummer des Knoten
   */
  public int nummer;

  /**
   * Das Gewicht des Knoten.
   */
  public int gewicht;

  public Element() {}

  /**
   * @param nummer Die Nummer des Knoten.
   * @param gewicht Das Gewicht des Knoten.
   */
  public Element(int nummer, int gewicht) {
    this.nummer = nummer;
    this.gewicht = gewicht;
  }

  @Override
  public int compare(Element knoten1, Element knoten2) {
    if (knoten1.gewicht < knoten2.gewicht)
      return -1;
    if (knoten1.gewicht > knoten2.gewicht)
      return 1;
    return 0;
  }
}
