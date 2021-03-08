package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;

/**
 * Implementation des Algorithmus von Prim / Jarník.
 *
 * Nach dem Tutorial auf <a href=
 * "https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-matrix/">tutorialhorizon.com</a>.
 */
public class MinimalerSpannbaumPrim extends GraphAdjazenzMatrix {

  public MinimalerSpannbaumPrim(String graphenFormat) {
    super(graphenFormat);
  }

  /**
   * Gib den Knoten mit dem minimalen Gewicht, der sich noch nicht im minimalen
   * Spannbaum befindet.
   *
   * @param minimalerSpannbaum Ein Feld mit der gleichen Länge, wie es Knoten im
   *                           Graphen gibt. Befindet sich beispielsweise ein
   *                           Knoten mit der Index-Nummer 3 im minimalen
   *                           Spannbaum, so wird das Feld an der Index-Position 3
   *                           auf wahr gesetzt.
   * @param kantenGewichte
   *
   * @return Die ID des Knoten mit dem minimalen Gewicht. Es können Zahlen
   *         beginnend mit 0 vorkommen.
   */
  private int gibMinimumKnoten(boolean[] minimalerSpannbaum, int[] kantenGewichte) {
    int minGewicht = Integer.MAX_VALUE;
    int knoten = -1;
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      if (minimalerSpannbaum[i] == false && minGewicht > kantenGewichte[i]) {
        minGewicht = kantenGewichte[i];
        knoten = i;
      }
    }
    return knoten;
  }

  /**
   * Die Instanzen der Klasse Ergebnis wird in das Feld {@code ergebnisse}
   * gespeichert. Die Klasse speichert das Kantengewicht von einem bestimmten
   * Knoten zu seinem Elternknoten.
   */
  class Ergebnis {
    /**
     * Die Index-Nummer des Elternknoten über den der aktuelle Knoten erreicht wird.
     */
    int eltern;

    /**
     * Das Gewicht der Kante vom Elternknoten zum aktuellen Knoten.
     */
    int gewicht;
  }

  /**
   * Führe den Algorithmus von Prim / Jarník aus.
   *
   * @return Die Summer aller Kantengewichte.
   */
  public int führeAus() {
    boolean[] minimalerSpannbaum = new boolean[gibKnotenAnzahl()];
    Ergebnis[] ergebnisse = new Ergebnis[gibKnotenAnzahl()];
    int[] gewichte = new int[gibKnotenAnzahl()];

    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      // Initialisiere alle Gewichte mit Unendlich
      gewichte[i] = Integer.MAX_VALUE;
      // Erzeuge leere Ergebnis-Instanzen.
      ergebnisse[i] = new Ergebnis();
    }

    // start from the vertex 0
    gewichte[0] = 0;
    ergebnisse[0] = new Ergebnis();
    ergebnisse[0].eltern = -1;

    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      int knoten = gibMinimumKnoten(minimalerSpannbaum, gewichte);
      System.out.println("Besuche Knoten „" + gibKnotenName(knoten) + "“");
      minimalerSpannbaum[knoten] = true;
      for (int j = 0; j < gibKnotenAnzahl(); j++) {
        if (matrix[knoten][j] > 0) {
          if (minimalerSpannbaum[j] == false && matrix[knoten][j] < gewichte[j]) {
            gewichte[j] = matrix[knoten][j];
            ergebnisse[j].eltern = knoten;
            ergebnisse[j].gewicht = gewichte[j];
            System.out
                .println("Aktualisiere Kante " + gibKnotenName(knoten) + "--" + gibKnotenName(j) + ": " + gewichte[j]);
          }
        }
      }
    }

    return gibErgebnisAus(ergebnisse);
  }

  /**
   * Gib die Ergebnisse aus.
   *
   * @param ergebnisse Ein Feld mit allen Ergebnissen.
   *
   * @return Die Summer aller Kantengewichte.
   */
  private int gibErgebnisAus(Ergebnis[] ergebnisse) {
    int summeGewichte = 0;
    System.out.println("Minimaler Spannbaum: ");
    for (int i = 1; i < gibKnotenAnzahl(); i++) {
      System.out.println("Kante: " + gibKnotenName(ergebnisse[i].eltern) + "--" + gibKnotenName(i) + " Gewicht: "
          + ergebnisse[i].gewicht);
      summeGewichte += ergebnisse[i].gewicht;
    }
    System.out.println("Summer aller Kantengewichte: " + summeGewichte);
    return summeGewichte;
  }

  public static void main(String[] args) {
    MinimalerSpannbaumPrim prim = new MinimalerSpannbaumPrim(
        "v0--v1:2;v1--v2:3;v0--v3:6;v1--v3:8;v1--v4:5;v2--v4:7;v3--v4:9;");
    prim.gibMatrixAus();
    prim.führeAus();
  }

}
