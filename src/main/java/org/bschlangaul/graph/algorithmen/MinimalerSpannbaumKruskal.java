package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;

//https://www.geeksforgeeks.org/kruskals-algorithm-simple-implementation-for-adjacency-matrix/
// Simple Java implementation for Kruskal's
// algorithm

public class MinimalerSpannbaumKruskal extends GraphAdjazenzMatrix {

  public MinimalerSpannbaumKruskal(String graphenFormat) {
    super(graphenFormat);
  }

  int[] parent = new int[gibKnotenAnzahl()];

  // Find set of vertex i
  private int find(int i) {
    while (parent[i] != i)
      i = parent[i];
    return i;
  }

  // Does union of i and j. It returns
  // false if i and j are already in same
  // set.
  private void union1(int i, int j) {
    int a = find(i);
    int b = find(j);
    parent[a] = b;
  }

  // Finds MST using Kruskal's algorithm
  public double führeAus() {
    double mincost = 0; // Cost of min MST.

    // Initialize sets of disjoint sets.
    for (int i = 0; i < gibKnotenAnzahl(); i++)
      parent[i] = i;

    // Include minimum weight edges one by one
    int edge_count = 0;
    while (edge_count < gibKnotenAnzahl() - 1) {
      double min = Double.MAX_VALUE;
      int a = -1, b = -1;
      for (int i = 0; i < gibKnotenAnzahl(); i++) {
        for (int j = 0; j < gibKnotenAnzahl(); j++) {
          if (find(i) != find(j) && matrix[i][j] < min && matrix[i][j] != -Double.MAX_VALUE) {
            min = matrix[i][j];
            a = i;
            b = j;
          }
        }
      }

      union1(a, b);
      System.out.printf("Edge %d:(%d, %d) cost:%d \n", edge_count++, a, b, min);
      mincost += min;
    }
    System.out.printf("\n Minimum cost= %d \n", mincost);
    return mincost;
  }

  public static void main(String[] args) {
    MinimalerSpannbaumKruskal kruskal = new MinimalerSpannbaumKruskal(
        "v0--v1:2;v1--v2:3;v0--v3:6;v1--v3:8;v1--v4:5;v2--v4:7;v3--v4:9;");
    kruskal.gibMatrixAus();
    kruskal.führeAus();
  }
}
