package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;
import org.bschlangaul.graph.report.GraphReporter;

class PrimReporter extends GraphReporter {

  GraphAdjazenzMatrix matrix;

  public PrimReporter(GraphAdjazenzMatrix matrix) {
    this.matrix = matrix;
  }

  public String knoten(int knotenNr) {
    return konsolenAusgabe.grün(matrix.gibKnotenName(knotenNr));
  }

  public String zahl(Double zahl) {
    return matrix.formatiereZahl(zahl);
  }

  public String kante(int eltern, int kind, double gewicht) {
    return "v(" + knoten(eltern) + ", " + knoten(kind) + ", " + konsolenAusgabe.gelb(zahl(gewicht)) + ")";
  }

  void knotenBesuch(int knotenNr) {
    System.out.println("Besuche Knoten „" + knoten(knotenNr) + "“");
  }

  void kantenHinzufügen(int eltern, int kind, double gewicht) {
    System.out.println("Füge Kante " + kante(eltern, kind, gewicht) + " hinzu");
  }

  void kantenAktualisierung(int eltern, int kind, double gewicht) {
    System.out.println("Aktualisiere Kante " + kante(eltern, kind, gewicht));
  }
}

/**
 * Implementation des Algorithmus von Prim / Jarník. Momentan beginnt der
 * Algorithmus immer vom ersten Knoten in der Adjazenzmatrix, d.h. mit dem
 * Knoten mit der Knotennummer 0.
 *
 * Nach dem Tutorial auf <a href=
 * "https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-matrix/">tutorialhorizon.com</a>.
 */
public class MinimalerSpannbaumPrim extends GraphAdjazenzMatrix {

  PrimReporter berichte;

  /**
   * Ein Feld, in dem gespeichert wird, ob der Elternknoten bereits besucht wurde.
   * Wurde der Elternknoten mit der Nummer 3 besucht wird besucht[3] auf true
   * gesetzt.
   */
  boolean[] besucht;

  /**
   * Ein Feld, in dem alle Ergebnisse gespeichert werden. Ein Ergebnis wird unter
   * der Kindknoten-Nummer gespeichert. Manche Ergebniseinträge werden
   * überschrieben.
   */
  Ergebnis[] ergebnisse = new Ergebnis[gibKnotenAnzahl()];

  /**
   * Das Gewicht wird unter der Kindknoten-Nummer gespeichert.
   */
  double[] gewichte;

  public MinimalerSpannbaumPrim(String graphenFormat) {
    super(graphenFormat);
    besucht = new boolean[gibKnotenAnzahl()];
    ergebnisse = new Ergebnis[gibKnotenAnzahl()];
    gewichte = new double[gibKnotenAnzahl()];
    berichte = new PrimReporter(this);
  }

  /**
   * Gib den Knoten mit dem minimalen Gewicht, der sich noch nicht im minimalen
   * Spannbaum befindet.
   *
   * @return Die ID des Knoten mit dem minimalen Gewicht. Es können Zahlen
   *         beginnend mit 0 vorkommen.
   */
  private int gibMinimumKnoten() {
    double minGewicht = Double.MAX_VALUE;
    int knoten = -1;
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      if (besucht[i] == false && minGewicht > gewichte[i]) {
        minGewicht = gewichte[i];
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
     * -1 bedeuted, dass das Ergebnis keinen Elternknoten hat
     */
    int eltern = -1;

    /**
     * Das Gewicht der Kante vom Elternknoten zum aktuellen Knoten. Das Feld wird
     * benötigt um den minimale Knoten zu finden.
     */
    double gewicht = NICHT_ERREICHBAR;
  }

  /**
   * Führe den Algorithmus von Prim / Jarník aus.
   *
   * @return Die Summer aller Kantengewichte.
   */
  public double führeAus() {
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      // Initialisiere alle Gewichte mit Unendlich
      gewichte[i] = Double.MAX_VALUE;
      // Erzeuge leere Ergebnis-Instanzen.
      ergebnisse[i] = new Ergebnis();
    }

    gewichte[0] = 0;
    ergebnisse[0] = new Ergebnis();
    ergebnisse[0].eltern = -1;

    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      int eltern = gibMinimumKnoten();
      berichte.knotenBesuch(eltern);
      besucht[eltern] = true;
      for (int kind = 0; kind < gibKnotenAnzahl(); kind++) {
        if (matrix[eltern][kind] > 0) {
          if (besucht[kind] == false && matrix[eltern][kind] < gewichte[kind]) {
            gewichte[kind] = matrix[eltern][kind];

            if (ergebnisse[kind].eltern != -1) {
              berichte.kantenAktualisierung(eltern, kind, gewichte[kind]);
            } else {
              berichte.kantenHinzufügen(eltern, kind, gewichte[kind]);
            }
            ergebnisse[kind].eltern = eltern;
            ergebnisse[kind].gewicht = gewichte[kind];
          }
        }
      }
    }

    return gibErgebnisAus();
  }

  /**
   * Gib die Ergebnisse aus.
   *
   * @return Die Summer aller Kantengewichte.
   */
  private int gibErgebnisAus() {
    int summeGewichte = 0;
    System.out.println("Minimaler Spannbaum: ");
    for (int i = 1; i < gibKnotenAnzahl(); i++) {
      System.out.println("Kante (Eltern->Kind): " + gibKnotenName(ergebnisse[i].eltern) + "--" + gibKnotenName(i)
          + " Gewicht: " + ergebnisse[i].gewicht);
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
