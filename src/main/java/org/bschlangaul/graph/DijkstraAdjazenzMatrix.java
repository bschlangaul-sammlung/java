package org.bschlangaul.graph;

/**
 * https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
 */
class DijkstraAdjazenzMatrix extends GraphAdjazenzMatrix {

  /**
   * Mit diesem Konstruktur wird die Adjazenzmatrix durch das einfache
   * Graphenformat erzeugt.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public DijkstraAdjazenzMatrix(String graphenFormat) {
    super(graphenFormat);
  }

  private static final int KEINE_VORGÄNGER = -1;

  /**
   * Diese Methode implementiert den Dijkstra-Algorithmus zum Finden des kürzesten
   * Pfads unter Angabe des Anfangsknoten.
   *
   * @param anfangsKnoten Der Name des Anfangsknoten.
   */
  public void sucheKürzestenPfad(String anfangsKnoten) {
    int knotenAnzahl = gibKnotenAnzahl();

    // In diesem Feld werden die kürzesten Entfernungen zu den einzelnen Knoten
    // gespeichert.
    int[] kürzesteEntfernungen = new int[knotenAnzahl];

    // besucht[i] wird auf true gesetzt, wenn sich der Knoten i im
    // Kürzesten-Pfad-Baum befindet oder der kürzeste Pfad vom Anfangskonten zum
    // Knoten i fertig berechnet ist.
    boolean[] besucht = new boolean[knotenAnzahl];

    // Initialisierung der beiden Felder kürzesteEntfernungen und
    // besucht.
    for (int KnotenNr = 0; KnotenNr < knotenAnzahl; KnotenNr++) {
      kürzesteEntfernungen[KnotenNr] = Integer.MAX_VALUE;
      besucht[KnotenNr] = false;
    }

    // Die Entfernung vom Anfangsknoten zu sich selbst ist immer 0.
    kürzesteEntfernungen[gibKnotenNummer(anfangsKnoten)] = 0;

    // Feld mit dem die Vorgänger-Knoten des kürzesten Pfads gespeichert werden.
    // Ein Vorgänger-Knoten des Pfads gibt ab, über welchen Knoten man auf
    // kürzesten Weg zum Knoten kommt.
    int[] vorgänger = new int[knotenAnzahl];

    // Der Anfangsknoten hat keinen Vorgänger.
    vorgänger[gibKnotenNummer(anfangsKnoten)] = KEINE_VORGÄNGER;

    // Hier startet der eigentliche Algorithmus.
    for (int i = 1; i < knotenAnzahl; i++) {
      // Pick the minimum distance vertex from the set of vertices not
      // yet processed. nearestVertex is always equal to startNode in
      // first iteration.
      int nächsterKnoten = -1;
      int entfernung = Integer.MAX_VALUE;
      for (int knotenNr = 0; knotenNr < knotenAnzahl; knotenNr++) {
        if (!besucht[knotenNr] && kürzesteEntfernungen[knotenNr] < entfernung) {
          nächsterKnoten = knotenNr;
          entfernung = kürzesteEntfernungen[knotenNr];
        }
      }

      // Markiere den ausgewählten Knoten als besucht.
      besucht[nächsterKnoten] = true;

      // Update dist value of the adjacent vertices of the picked
      // vertex.
      for (int knotenNr = 0; knotenNr < knotenAnzahl; knotenNr++) {
        int kantenEntfernung = matrix[nächsterKnoten][knotenNr];

        if (kantenEntfernung > 0 && ((entfernung + kantenEntfernung) < kürzesteEntfernungen[knotenNr])) {
          vorgänger[knotenNr] = nächsterKnoten;
          kürzesteEntfernungen[knotenNr] = entfernung + kantenEntfernung;
        }
      }
    }

    printSolution(anfangsKnoten, kürzesteEntfernungen, vorgänger);
  }

  // A utility function to print the constructed distances array and
  // shortest paths
  private void printSolution(String startVertex, int[] distances, int[] parents) {
    int nVertices = distances.length;
    System.out.print("Vertex\t Distance\tPath");

    for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
      if (vertexIndex != gibKnotenNummer(startVertex)) {
        System.out.print("\n" + startVertex + " -> ");
        System.out.print(gibKnotenNamen(vertexIndex) + " \t\t ");
        System.out.print(distances[vertexIndex] + "\t\t");
        printPath(vertexIndex, parents);
      }
    }
  }

  // Function to print shortest path from source to currentVertex using
  // parents array
  private void printPath(int currentVertex, int[] parents) {

    // Base case : Source node has
    // been processed
    if (currentVertex == KEINE_VORGÄNGER) {
      return;
    }
    printPath(parents[currentVertex], parents);
    System.out.print(gibKnotenNamen(currentVertex) + " ");
  }

  // Driver Code
  public static void main(String[] args) {
    DijkstraAdjazenzMatrix dijkstra = new DijkstraAdjazenzMatrix("a -- b; b -- c: 7; a -- d: 2; b -> d: 19");
    dijkstra.sucheKürzestenPfad("c");
  }
}
