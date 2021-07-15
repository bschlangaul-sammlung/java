package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;

/**
 * Implementation des Algorithmus von Kruskal.
 *
 * Nach dem Tutorial auf <a href=
 * "https://www.geeksforgeeks.org/kruskals-algorithm-simple-implementation-for-adjacency-matrix/">geeksforgeeks.org</a>.
 */
public class MinimalerSpannbaumKruskal extends GraphAdjazenzMatrix {

  public MinimalerSpannbaumKruskal(String graphenFormat) {
    super(graphenFormat);
  }

  int[] eltern = new int[gibKnotenAnzahl()];

  private int find(int i) {
    while (eltern[i] != i) {
      i = eltern[i];
    }
    return i;
  }

  private void union1(int i, int j) {
    int a = find(i);
    int b = find(j);
    eltern[a] = b;
  }

  public double führeAus() {
    double kosten = 0;

    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      eltern[i] = i;
    }

    int kantenAnzahl = 0;
    while (kantenAnzahl < gibKnotenAnzahl() - 1) {
      double gewicht = Double.MAX_VALUE;
      int knotenA = -1;
      int knotenB = -1;
      for (int i = 0; i < gibKnotenAnzahl(); i++) {
        for (int j = 0; j < gibKnotenAnzahl(); j++) {
          if (find(i) != find(j) && matrix[i][j] < gewicht && matrix[i][j] != -Double.MAX_VALUE) {
            gewicht = matrix[i][j];
            knotenA = i;
            knotenB = j;
          }
        }
      }

      union1(knotenA, knotenB);
      kantenAnzahl++;
      System.out.printf("Edge %d:(%d, %d) cost:%s \n", kantenAnzahl, knotenA, knotenB, gewicht);
      kosten += gewicht;
    }
    System.out.printf("\n Minimum cost= %s \n", kosten);
    return kosten;
  }

  public static void main(String[] args) {
    MinimalerSpannbaumKruskal kruskal = new MinimalerSpannbaumKruskal(
        "v0--v1:2;v1--v2:3;v0--v3:6;v1--v3:8;v1--v4:5;v2--v4:7;v3--v4:9;");
    kruskal.gibMatrixAus();
    kruskal.führeAus();
  }
}
