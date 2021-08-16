package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;
import org.bschlangaul.graph.report.GraphReporter;

/**
 * Implementation des Algorithmus von Prim / Jarník. Momentan beginnt der
 * Algorithmus immer vom ersten Knoten in der Adjazenzmatrix, d.h. mit dem
 * Knoten mit der Knotennummer 0.
 *
 * Nach dem Tutorial auf <a href=
 * "https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-matrix/">tutorialhorizon.com</a>.
 */
public class MinimalerSpannbaumPrim extends GraphAdjazenzMatrix {

  final double KEIN_GEWICHT = Double.MAX_VALUE;

  class PrimReporter extends GraphReporter {

    GraphAdjazenzMatrix matrix;

    public PrimReporter(GraphAdjazenzMatrix matrix) {
      this.matrix = matrix;
    }

    public String knoten(int knotenNr) {
      return konsolenAusgabe.grün(matrix.gibKnotenName(knotenNr));
    }

    void knotenBesuch(int knotenNr) {
      System.out.println("\nBesuche Knoten „" + knoten(knotenNr) + "“");
    }

    public String kante(int knoten, int eltern, double gewicht) {
      String elternName = eltern != -1 ? knoten(eltern) : "null";
      String gewichtAusgabe = gewicht != NICHT_ERREICHBAR ? zahl(gewicht) : "-";
      return "(" + knoten(knoten) + ", " + elternName + ", " + gewichtAusgabe + ")";
    }

    public String zahl(Double zahl) {
      return matrix.formatiereZahl(zahl);
    }

    void kantenAktualisierung(int kind, int eltern, double gewicht) {
      if (kanten[kind].eltern != -1) {
        System.out.println("Aktualisiere Kante " + kante(eltern, kind, gewicht));
      } else {
        System.out.println("Füge Kante " + kante(eltern, kind, gewicht) + " hinzu");
      }
    }

    void schrittEnde(int eltern) {
      schnappschüsse[zähler] = new Schnappschuss(eltern, kanten);
      zähler++;
    }

    /**
     * Vom aktuellen Spannbaum direkt erreichbaren Knoten (sog. „graue Randknoten“)
     */
    String graueKanten(MinimaleKante[] kanten) {
      String ausgabe = "";
      for (int i = 1; i < gibKnotenAnzahl(); i++) {
        if (kanten[i].gewicht != KEIN_GEWICHT && !kanten[i].besucht) {
          ausgabe += kante(i, kanten[i].eltern, kanten[i].gewicht) + "; ";
        }
      }
      return ausgabe.replace("; $", "");
    }

    void schnappschussTabelle() {
      String[][] zeilen = new String[schnappschüsse.length][2];
      for (int i = 0; i < schnappschüsse.length; i++) {

        zeilen[i][0] = kante(schnappschüsse[i].eltern, schnappschüsse[i].kantenKopie[schnappschüsse[i].eltern].eltern,
            schnappschüsse[i].kantenKopie[schnappschüsse[i].eltern].gewicht);
        zeilen[i][1] = graueKanten(schnappschüsse[i].kantenKopie);
      }

      System.out.println(texAusgabe.tabelle(new String[] { "schwarze", "graue" }, zeilen));
    }

    void minimalerSpannbaum() {
      System.out.println("Minimaler Spannbaum: ");
      for (int i = 1; i < gibKnotenAnzahl(); i++) {
        if (kanten[i].gewicht != KEIN_GEWICHT) {
          System.out.println("  " + kante(i, kanten[i].eltern, kanten[i].gewicht));
        }
      }
    }

    Double summeKantengewichte() {
      Double summeGewichte = 0d;
      for (int i = 1; i < gibKnotenAnzahl(); i++) {
        summeGewichte += kanten[i].gewicht;
      }
      System.out.println("Summer aller Kantengewichte: " + zahl(summeGewichte));
      return summeGewichte;
    }
  }

  /**
   * Die Instanzen der Klasse MinimaleKante wird in das Feld {@code kanten}
   * gespeichert. Die Klasse speichert das Kantengewicht von einem bestimmten
   * Knoten zu seinem Elternknoten. Die Instanzen werden unter der
   * Kind-Knoten-Nummer in das Feld bespeichert.
   */
  class MinimaleKante {

    /**
     * Die Index-Nummer des Knotens unter dem dem diese Kante in das kanten-Feld
     * abgelegt wird.
     */
    int kind;

    /**
     * Die Index-Nummer des Elternknoten über den der aktuelle Knoten erreicht wird.
     * -1 bedeuted, dass das Ergebnis keinen Elternknoten hat
     */
    int eltern = -1;

    /**
     * Das Gewicht der Kante vom Elternknoten zum aktuellen Knoten. Das Feld wird
     * benötigt um den minimale Knoten zu finden.
     */
    double gewicht = KEIN_GEWICHT;

    /**
     * Ist dieses Attribut auf wahr gesetzt, dann heißt das, dass die Kante zum
     * minimalen Stammbaum gehört.
     */
    boolean besucht;

    public MinimaleKante() {
    }

    public MinimaleKante(int kind) {
      this.kind = kind;
    }

    public MinimaleKante(int eltern, double gewicht) {
      this.eltern = eltern;
      this.gewicht = gewicht;
    }

    public MinimaleKante(int kind, int eltern, double gewicht, boolean besucht) {
      this.kind = kind;
      this.eltern = eltern;
      this.gewicht = gewicht;
      this.besucht = besucht;
    }

    public MinimaleKante klone() {
      return new MinimaleKante(kind, eltern, gewicht, besucht);
    }

  }

  /**
   * Speichert einen Schnappschuss, d. h. eine Schritt im Prim-Algorithmus..
   * Eine Instanz sollte nach jedem Besuch eines Knoten erzeugt werden.
   */
  class Schnappschuss {
    int eltern;
    MinimaleKante[] kantenKopie;

    public Schnappschuss(int eltern, MinimaleKante[] kanten) {
      this.eltern = eltern;
      kantenKopie = new MinimaleKante[kanten.length];
      for (int i = 0; i < kanten.length; i++) {
        kantenKopie[i] = kanten[i].klone();
      }
    }
  }

  PrimReporter berichte;

  Schnappschuss[] schnappschüsse;

  int zähler;

  /**
   * Ein Feld, in dem alle Ergebnisse gespeichert werden. Ein Ergebnis wird unter
   * der Kindknoten-Nummer gespeichert. Manche Ergebniseinträge werden
   * überschrieben.
   */
  MinimaleKante[] kanten = new MinimaleKante[gibKnotenAnzahl()];

  public MinimalerSpannbaumPrim(String graphenFormat) {
    super(graphenFormat);
    kanten = new MinimaleKante[gibKnotenAnzahl()];
    berichte = new PrimReporter(this);
    schnappschüsse = new Schnappschuss[gibKnotenAnzahl()];
    zähler = 0;
  }

  /**
   * Gib den Knoten mit dem minimalen Gewicht, der sich noch nicht im minimalen
   * Spannbaum befindet.
   *
   * @return Die ID des Knoten mit dem minimalen Gewicht. Es können Zahlen
   *         beginnend mit 0 vorkommen.
   */
  private int gibMinimumKnoten() {
    double minGewicht = KEIN_GEWICHT;
    int knoten = -1;
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      if (kanten[i].besucht == false && minGewicht > kanten[i].gewicht) {
        minGewicht = kanten[i].gewicht;
        knoten = i;
      }
    }
    return knoten;
  }

  /**
   * Führe den Algorithmus von Prim / Jarník aus.
   *
   * @return Die Summer aller Kantengewichte.
   */
  public double führeAus() {
    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      kanten[i] = new MinimaleKante(i);
    }

    kanten[0].gewicht = 0;

    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      int eltern = gibMinimumKnoten();
      berichte.knotenBesuch(eltern);
      kanten[eltern].besucht = true;
      for (int kind = 0; kind < gibKnotenAnzahl(); kind++) {
        if (matrix[eltern][kind] > NICHT_ERREICHBAR) {
          if (kanten[kind].besucht == false && matrix[eltern][kind] < kanten[kind].gewicht) {
            berichte.kantenAktualisierung(kind, eltern, matrix[eltern][kind]);
            kanten[kind].eltern = eltern;
            kanten[kind].gewicht = matrix[eltern][kind];
          }
        }
      }
      berichte.schrittEnde(eltern);
    }

    return gibErgebnisAus();
  }

  /**
   * Gib die Ergebnisse aus.
   *
   * @return Die Summer aller Kantengewichte.
   */
  private Double gibErgebnisAus() {
    berichte.minimalerSpannbaum();
    berichte.schnappschussTabelle();
    return berichte.summeKantengewichte();
  }

  public static void main(String[] args) {
    MinimalerSpannbaumPrim prim = new MinimalerSpannbaumPrim(
        "v0--v1:2;v1--v2:3;v0--v3:6;v1--v3:8;v1--v4:5;v2--v4:7;v3--v4:9;");
    prim.gibMatrixAus();
    prim.führeAus();
  }

}
