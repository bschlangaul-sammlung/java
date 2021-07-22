package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;

/**
 * Implementation des Algorithmus von Kruskal.
 *
 * Nach dem Tutorial auf <a href=
 * "https://www.geeksforgeeks.org/kruskals-algorithm-simple-implementation-for-adjacency-matrix/">geeksforgeeks.org</a>.
 */
public class MinimalerSpannbaumKruskal extends GraphAdjazenzMatrix {

  class Ergebnis {
    double kosten;
    ErgebnisKante[] kanten;
    int aktuelleKante;

    public Ergebnis(int knotenanzahl) {
      kanten = new ErgebnisKante[knotenanzahl - 1];
    }

    public void fügeKanteHinzu(String von, String nach, double gewicht) {
      kanten[aktuelleKante] = new ErgebnisKante(von, nach, gewicht);
    }
  }

  class ErgebnisKante {
    String von;
    String nach;
    double gewicht;

    public ErgebnisKante(String von, String nach, double gewicht) {
      this.von = von;
      this.nach = nach;
      this.gewicht = gewicht;
    }
  }

  public MinimalerSpannbaumKruskal(String graphenFormat) {
    super(graphenFormat);
  }

  Ergebnis ergebnis = new Ergebnis(gibKnotenAnzahl());
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
          if (find(i) != find(j) && matrix[i][j] < gewicht && matrix[i][j] != NICHT_ERREICHBAR) {
            gewicht = matrix[i][j];
            knotenA = i;
            knotenB = j;
          }
        }
      }

      union1(knotenA, knotenB);
      kantenAnzahl++;
      ergebnis.fügeKanteHinzu(gibKnotenName(knotenA), gibKnotenName(knotenB), gewicht);
      kosten += gewicht;
    }
    ergebnis.kosten = kosten;
    return kosten;
  }

  public static void main(String[] args) {
    MinimalerSpannbaumKruskal kruskal = new MinimalerSpannbaumKruskal(
        "v0--v1:2;v1--v2:3;v0--v3:6;v1--v3:8;v1--v4:5;v2--v4:7;v3--v4:9;");
    kruskal.gibMatrixAus();
    kruskal.führeAus();
  }
}
