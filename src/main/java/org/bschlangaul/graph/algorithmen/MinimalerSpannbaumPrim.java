package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;

/**
 * https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-matrix/
 */

class MinimalerSpannbaumPrim extends GraphAdjazenzMatrix {

  public MinimalerSpannbaumPrim(String graphenFormat) {
    super(graphenFormat);
  }

  // get the vertex with minimum key which is not included in MST
  private int getMinimumVertex(boolean[] mst, int[] key) {
    int minKey = Integer.MAX_VALUE;
    int vertex = -1;
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      if (mst[i] == false && minKey > key[i]) {
        minKey = key[i];
        vertex = i;
      }
    }
    return vertex;
  }

  class ResultSet {
    // will store the vertex(parent) from which the current vertex will reached
    int parent;
    // will store the weight for printing the MST weight
    int weight;
  }

  public int führeAus() {
    boolean[] mst = new boolean[gibKnotenAnzahl()];
    ResultSet[] resultSet = new ResultSet[gibKnotenAnzahl()];
    int[] key = new int[gibKnotenAnzahl()];

    // Initialize all the keys to infinity and
    // initialize resultSet for all the gibKnotenAnzahl()
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      key[i] = Integer.MAX_VALUE;
      resultSet[i] = new ResultSet();
    }

    // start from the vertex 0
    key[0] = 0;
    resultSet[0] = new ResultSet();
    resultSet[0].parent = -1;

    // create MST
    for (int i = 0; i < gibKnotenAnzahl(); i++) {

      // get the vertex with the minimum key
      int vertex = getMinimumVertex(mst, key);

      System.out.println("Besuche: " +  gibKnotenName(vertex));

      // include this vertex in MST
      mst[vertex] = true;

      // iterate through all the adjacent gibKnotenAnzahl() of above vertex and update
      // the keys
      for (int j = 0; j < gibKnotenAnzahl(); j++) {
        // check of the edge
        if (matrix[vertex][j] > 0) {
          // check if this vertex 'j' already in mst and
          // if no then check if key needs an update or not
          if (mst[j] == false && matrix[vertex][j] < key[j]) {
            System.out.println("Update Kante: " +  gibKnotenName(vertex) + "--" + gibKnotenName(j));

            // update the key
            key[j] = matrix[vertex][j];
            // update the result set
            resultSet[j].parent = vertex;
            resultSet[j].weight = key[j];
          }
        }
      }
    }
    // print mst
    return printMST(resultSet);
  }

  private int printMST(ResultSet[] resultSet) {
    int total_min_weight = 0;
    System.out.println("Minimum Spanning Tree: ");
    for (int i = 1; i < gibKnotenAnzahl(); i++) {
      System.out.println("Kante: " + gibKnotenName(resultSet[i].parent) + " - " + gibKnotenName(i) + " gewicht: "
          + resultSet[i].weight);
      total_min_weight += resultSet[i].weight;
    }
    System.out.println("Total minimum key: " + total_min_weight);
    return total_min_weight;
  }

}
