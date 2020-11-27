package org.bschlangaul.graph;

import java.util.Set;
import java.util.PriorityQueue;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementierung des Algorithmus von Dijkstra mit einer Prioritätswarteschlange.
 *
 * Nach einem Kode-Beispiel auf <a href="https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue">geeksforgeeks.org</a>
 */
public class Dijkstra {
  private int dist[];
  private Set<Integer> settled;

  /**
   * Warteschlange
   */
  private PriorityQueue<Element> warteschlange;

  /**
   * Anzahl der Knoten.
   */
  private int knotenAnzahl;

  /**
   *
   */
  List<List<Element>> adjazenzListe;

  public Dijkstra(int knotenAnzahl) {
    this.knotenAnzahl = knotenAnzahl;
    dist = new int[knotenAnzahl];
    settled = new HashSet<Integer>();
    warteschlange = new PriorityQueue<Element>(knotenAnzahl, new Element());
  }

  /**
   * Hauptmethode des Algorithmuses.
   *
   * @param adjazenzListe Die Adjazenz-Liste.
   * @param startKnoten Die Nummer des Startknotens.
   */
  public void dijkstra(List<List<Element>> adjazenzListe, int startKnoten) {
    this.adjazenzListe = adjazenzListe;

    for (int i = 0; i < knotenAnzahl; i++)
      dist[i] = Integer.MAX_VALUE;

    // Add source node to the priority queue
    warteschlange.add(new Element(startKnoten, 0));

    // Distance to the source is 0
    dist[startKnoten] = 0;
    while (settled.size() != knotenAnzahl) {

      // remove the minimum distance node
      // from the priority queue
      int u = warteschlange.remove().nummer;

      // adding the node whose distance is
      // finalized
      settled.add(u);

      findeNachbarn(u);
    }
  }

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

  // Driver code
  public static void main(String arg[]) {
    int knotenAnzahl = 5;
    int source = 0;

    // Adjacency list representation of the
    // connected edges
    List<List<Element>> adjazenzListe = new ArrayList<List<Element>>();

    // Initialize list for every node
    for (int i = 0; i < knotenAnzahl; i++) {
      List<Element> item = new ArrayList<Element>();
      adjazenzListe.add(item);
    }

    // Inputs for the DPQ graph
    adjazenzListe.get(0).add(new Element(1, 9));
    adjazenzListe.get(0).add(new Element(2, 6));
    adjazenzListe.get(0).add(new Element(3, 5));
    adjazenzListe.get(0).add(new Element(4, 3));

    adjazenzListe.get(2).add(new Element(1, 2));
    adjazenzListe.get(2).add(new Element(3, 4));

    // Calculate the single source shortest path
    Dijkstra algorithmus = new Dijkstra(knotenAnzahl);
    algorithmus.dijkstra(adjazenzListe, source);

    // Print the shortest path to all the nodes
    // from the source node
    System.out.println("The shorted path from node :");
    for (int i = 0; i < algorithmus.dist.length; i++)
      System.out.println(source + " to " + i + " is " + algorithmus.dist[i]);
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
