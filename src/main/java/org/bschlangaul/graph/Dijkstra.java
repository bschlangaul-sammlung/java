package org.bschlangaul.graph;

import java.util.ArrayList;
import java.util.List;
import com.jakewharton.fliptables.FlipTable;

/**
 * https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
 */
public class Dijkstra {

  String graphenFormat;

  Graph graph;

  /**
   * In diesem Feld werden die kürzesten Entfernungen zu den einzelnen Knoten
   * gespeichert.
   */
  int[] kürzesteEntfernungen;

  /**
   * Feld, mit dem die Vorgänger-Knoten des kürzesten Pfads gespeichert werden.
   * Ein Vorgänger-Knoten des Pfads gibt ab, über welchen Knoten man auf kürzesten
   * Weg zum Knoten kommt.
   */
  int[] vorgänger;

  List<List<Integer>> pfade;

  int startKnotenNr;

  List<Integer> bearbeitungsReihenfolge;

  /**
   * Mit diesem Konstruktur wird die Adjazenzmatrix durch das einfache
   * Graphenformat erzeugt.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public Dijkstra(String graphenFormat) {
    this.graphenFormat = graphenFormat;
    graph = new GraphAdjazenzMatrix(graphenFormat);
  }

  private static final int KEINE_VORGÄNGER = -1;

  /**
   * Diese Methode implementiert den Dijkstra-Algorithmus zum Finden des kürzesten
   * Pfads unter Angabe des Anfangsknoten.
   *
   * @param anfangsKnoten Der Name des Anfangsknoten.
   */
  public int[] sucheKürzestenPfadMatrix(String anfangsKnoten) {
    GraphAdjazenzMatrix matrix = new GraphAdjazenzMatrix(graphenFormat);
    startKnotenNr = matrix.gibKnotenNummer(anfangsKnoten);
    int knotenAnzahl = matrix.gibKnotenAnzahl();
    graph = matrix;

    kürzesteEntfernungen = new int[knotenAnzahl];
    pfade = new ArrayList<List<Integer>>(knotenAnzahl);
    bearbeitungsReihenfolge = new ArrayList<Integer>();

    // besucht[i] wird auf true gesetzt, wenn sich der Knoten i im
    // Kürzesten-Pfad-Baum befindet oder der kürzeste Pfad vom Anfangskonten zum
    // Knoten i fertig berechnet ist.
    boolean[] besucht = new boolean[knotenAnzahl];

    // Initialisierung der beiden Felder kürzesteEntfernungen und
    // besucht.
    for (int i = 0; i < knotenAnzahl; i++) {
      kürzesteEntfernungen[i] = Integer.MAX_VALUE;
      besucht[i] = false;
      pfade.add(new ArrayList<Integer>());
    }

    // Die Entfernung vom Anfangsknoten zu sich selbst ist immer 0.
    kürzesteEntfernungen[startKnotenNr] = 0;

    // Feld mit dem die Vorgänger-Knoten des kürzesten Pfads gespeichert werden.
    // Ein Vorgänger-Knoten des Pfads gibt ab, über welchen Knoten man auf
    // kürzesten Weg zum Knoten kommt.
    vorgänger = new int[knotenAnzahl];

    // Der Anfangsknoten hat keinen Vorgänger.
    vorgänger[startKnotenNr] = KEINE_VORGÄNGER;

    // Hier startet der eigentliche Algorithmus.
    for (int i = 1; i < knotenAnzahl; i++) {
      // Pick the minimum distance vertex from the set of vertices not
      // yet processed. nearestVertex is always equal to startNode in
      // first iteration.
      int nähesterKnoten = -1;
      int entfernung = Integer.MAX_VALUE;
      for (int j = 0; j < knotenAnzahl; j++) {
        if (!besucht[j] && kürzesteEntfernungen[j] < entfernung) {
          nähesterKnoten = j;
          entfernung = kürzesteEntfernungen[j];
        }
      }
      System.out.println(matrix.gibKnotenName(nähesterKnoten));

      // Markiere den ausgewählten Knoten als besucht.
      besucht[nähesterKnoten] = true;

      bearbeitungsReihenfolge.add(nähesterKnoten);

      // Update dist value of the adjacent vertices of the picked
      // vertex.
      for (int j = 0; j < knotenAnzahl; j++) {
        int kantenEntfernung = matrix.matrix[nähesterKnoten][j];

        if (kantenEntfernung > 0 && ((entfernung + kantenEntfernung) < kürzesteEntfernungen[j])) {
          vorgänger[j] = nähesterKnoten;
          kürzesteEntfernungen[j] = entfernung + kantenEntfernung;
        }
      }
    }

    // Sammle rekursive alle Pfade (A -> B -> E)
    for (int i = 0; i < knotenAnzahl; i++) {
      if (i != startKnotenNr) {
        sammlePfade(i, i, vorgänger);
      }
    }
    return kürzesteEntfernungen;
  }

  public String gibVorgänger(String knotenName) {
    int knotenNummer = vorgänger[graph.gibKnotenNummer(knotenName)];
    if (knotenNummer == -1)
      return knotenName;
    return graph.gibKnotenName(knotenNummer);
  }

  public int gibEntfernung(String knotenName) {
    return kürzesteEntfernungen[graph.gibKnotenNummer(knotenName)];
  }

  public static int[] sucheKürzestenPfad(String einfachesGraphenFormat, String anfangsKnoten) {
    return new DijkstraAdjazenzMatrix(einfachesGraphenFormat).sucheKürzestenPfad(anfangsKnoten);
  }

  public void gibErgebnisTabelle() {
    String[] kopfZeile = { "von ->\nnach", "Entfernung", "Bearbeitungs-\nReihenfolge", "Pfad" };
    String[][] zeilen = new String[pfade.size()][4];
    for (int i = 0; i < pfade.size(); i++) {
      zeilen[i][0] = formatiereVonNach(i);
      zeilen[i][1] = String.valueOf(kürzesteEntfernungen[i]);
      zeilen[i][2] = String.valueOf(gibBearbeitungsNummer(i));
      zeilen[i][3] = formatierePfade(pfade.get(i));
    }
    System.out.println();
    System.out.println(FlipTable.of(kopfZeile, zeilen));
  }

  private int gibBearbeitungsNummer(int knotenNr) {
    for (int i = 0; i < bearbeitungsReihenfolge.size(); i++) {
      if (bearbeitungsReihenfolge.get(i) == knotenNr) {
        return i;
      }
    }
    return bearbeitungsReihenfolge.size();
  }

  private void sammlePfade(int nachKnotenNr, int aktuelleKnotenNr, int[] vorgänger) {
    // Base case : Source node has
    // been processed
    if (aktuelleKnotenNr == KEINE_VORGÄNGER) {
      return;
    }
    pfade.get(nachKnotenNr).add(aktuelleKnotenNr);
    sammlePfade(nachKnotenNr, vorgänger[aktuelleKnotenNr], vorgänger);
  }

  private String formatiereVonNach(int knotenNr) {
    return String.format("%s -> %s", graph.gibKnotenName(startKnotenNr), graph.gibKnotenName(knotenNr));
  }

  private String formatierePfade(List<Integer> pfade) {
    String ausgabe = "";
    for (int i = pfade.size() - 1; i >= 0; i--) {
      ausgabe += graph.gibKnotenName(pfade.get(i)) + " -> ";
    }
    return ausgabe.replaceFirst(" -> $", "");
  }

  public static void main(String[] args) {
    // DijkstraAdjazenzMatrix dijkstra = new DijkstraAdjazenzMatrix("a -- b; b -- c:
    // 7; a -- d: 2; b -> d: 19");
    // dijkstra.sucheKürzestenPfad("c");

    // Dijkstra d = new Dijkstra(
    // "a->b: 1; a->e: 7; b->c: 3; c->d: 8; c->e: 3; e->f: 1; c->f: 6; f->c: 1;
    // f->d: 3");
    Dijkstra d = new Dijkstra(
        "A: 1 4; B: 3 5; C: 3 3; D: 0 2; E: 5 5; F: 5 1; G: 3 0; H: 6 3; I: 8 4; A -- B: 2; A -- C: 5; A -- D: 2; B -- C: 3; B -- E; C -- D: 3; C -- E; C -- F; C -- H; D -- G: 2; E -- I: 7; F -- G: 2; F -- H: 3; H -- I;");

    d.sucheKürzestenPfadMatrix("A");
    d.gibErgebnisTabelle();
  }
}
