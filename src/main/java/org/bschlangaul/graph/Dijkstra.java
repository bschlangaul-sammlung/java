package org.bschlangaul.graph;

import java.util.ArrayList;
import java.util.List;
import com.jakewharton.fliptables.FlipTable;

import org.bschlangaul.helfer.Farbe;

/**
 * https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
 */
public class Dijkstra {

  class Bearbeitungsschritt {
    int nr;
    int[] entfernungen;
    int aktuellerKnoten;

    public Bearbeitungsschritt(int nr) {
      this.nr = nr;
      aktuellerKnoten = ausgewählterKnoten;
      entfernungen = new int[knotenAnzahl];
      for (int i = 0; i < knotenAnzahl; i++) {
        entfernungen[i] = kürzesteEntfernungen[i];
      }
    }
  }

  /**
   * Sammelt Informationen, wie der Algorithmus arbeitet.
   */
  public class Reporter {
    List<List<Integer>> pfade;

    List<Integer> bearbeitungsReihenfolge;

    int schrittZähler = 0;

    List<Bearbeitungsschritt> schritte;

    public void starte() {
      pfade = new ArrayList<List<Integer>>(knotenAnzahl);
      bearbeitungsReihenfolge = new ArrayList<Integer>();
      for (int i = 0; i < knotenAnzahl; i++) {
        pfade.add(new ArrayList<Integer>());
      }
      schritte = new ArrayList<Bearbeitungsschritt>();
    }

    public void speichereSchritt() {
      schrittZähler++;
      bearbeitungsReihenfolge.add(ausgewählterKnoten);
      schritte.add(new Bearbeitungsschritt(schrittZähler));
    }

    public void beende() {
      // Sammle rekursiv alle Pfade (A -> B -> E)
      for (int i = 0; i < knotenAnzahl; i++) {
        if (i != startKnotenNr) {
          sammlePfade(i, i, vorgänger);
        }
      }
    }

    public void gibZwischenschrittTabelle() {
      int knotenVerschiebung = 2;
      int spaltenAnzahl = knotenAnzahl + knotenVerschiebung;
      String[] kopfZeile = new String[spaltenAnzahl];
      kopfZeile[0] = "Schritt-\nNummer";
      kopfZeile[1] = "besuchter\nKnoten";
      for (int i = 0; i < knotenAnzahl; i++) {
        kopfZeile[i + knotenVerschiebung] = gibKnotenName(i);
      }

      String[][] zeilen = new String[schritte.size()][spaltenAnzahl];
      for (int i = 0; i < schritte.size(); i++) {
        int schrittNummer = i + 1;
        zeilen[i][0] = String.valueOf(schrittNummer);
        Bearbeitungsschritt schritt = schritte.get(i);
        zeilen[i][1] = gibKnotenName(schritt.aktuellerKnoten);
        for (int j = 0; j < schritt.entfernungen.length; j++) {
          int ergebnis = schritt.entfernungen[j];
          if (ergebnis == Integer.MAX_VALUE) {
            zeilen[i][j + knotenVerschiebung] = "∞";
          } else if (gibBearbeitungsNummer(j) == schrittNummer - 1) {
            zeilen[i][j + knotenVerschiebung] = Farbe.rot(String.valueOf(schritt.entfernungen[j]));
          } else if (gibBearbeitungsNummer(j) < schrittNummer - 1) {
            zeilen[i][j + knotenVerschiebung] = "|";
          } else {
            zeilen[i][j + knotenVerschiebung] = String.valueOf(schritt.entfernungen[j]);
          }
        }
      }
      System.out.println();
      System.out.println(FlipTable.of(kopfZeile, zeilen));
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
      if (aktuelleKnotenNr == Dijkstra.KEINE_VORGÄNGER) {
        return;
      }
      pfade.get(nachKnotenNr).add(aktuelleKnotenNr);
      sammlePfade(nachKnotenNr, vorgänger[aktuelleKnotenNr], vorgänger);
    }

    private String formatiereVonNach(int knotenNr) {
      return String.format("%s -> %s", gibKnotenName(startKnotenNr), gibKnotenName(knotenNr));
    }

    private String formatierePfade(List<Integer> pfade) {
      String ausgabe = "";
      for (int i = pfade.size() - 1; i >= 0; i--) {
        ausgabe += gibKnotenName(pfade.get(i)) + " -> ";
      }
      return ausgabe.replaceFirst(" -> $", "");
    }

    private String gibKnotenName(int knotenNummer) {
      return graph.gibKnotenName(knotenNummer);
    }

  }

  String graphenFormat;

  Graph graph;

  /**
   * In diesem Feld werden die kürzesten Entfernungen zu den einzelnen Knoten
   * gespeichert.
   */
  public int[] kürzesteEntfernungen;

  /**
   * Feld, mit dem die Vorgänger-Knoten des kürzesten Pfads gespeichert werden.
   * Ein Vorgänger-Knoten des Pfads gibt ab, über welchen Knoten man auf kürzesten
   * Weg zum Knoten kommt.
   */
  int[] vorgänger;

  int startKnotenNr;

  public int knotenAnzahl;

  public Reporter reporter;

  public boolean[] besucht;

  /**
   * Der aktuelle ausgewählte, besuchte Knoten.
   */
  public int ausgewählterKnoten;

  /**
   * Mit diesem Konstruktur wird die Adjazenzmatrix durch das einfache
   * Graphenformat erzeugt.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public Dijkstra(String graphenFormat) {
    this.graphenFormat = graphenFormat;
    graph = new GraphAdjazenzMatrix(graphenFormat);
    reporter = new Reporter();
  }

  public static final int KEINE_VORGÄNGER = -1;

  /**
   * Diese Methode implementiert den Dijkstra-Algorithmus zum Finden des kürzesten
   * Pfads unter Angabe des Anfangsknoten.
   *
   * @param anfangsKnoten Der Name des Anfangsknoten.
   */
  public int[] sucheKürzestenPfadMatrix(String anfangsKnoten) {
    GraphAdjazenzMatrix matrix = new GraphAdjazenzMatrix(graphenFormat);
    startKnotenNr = matrix.gibKnotenNummer(anfangsKnoten);
    knotenAnzahl = matrix.gibKnotenAnzahl();
    graph = matrix;

    kürzesteEntfernungen = new int[knotenAnzahl];

    // besucht[i] wird auf true gesetzt, wenn sich der Knoten i im
    // Kürzesten-Pfad-Baum befindet oder der kürzeste Pfad vom Anfangskonten zum
    // Knoten i fertig berechnet ist.
    boolean[] besucht = new boolean[knotenAnzahl];

    // Initialisierung der beiden Felder kürzesteEntfernungen und
    // besucht.
    for (int i = 0; i < knotenAnzahl; i++) {
      kürzesteEntfernungen[i] = Integer.MAX_VALUE;
      besucht[i] = false;
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
    reporter.starte();
    // Es würde auch i < knotenAnzahl reichen, aber für die Ergebnistabellen
    // lassen wir den Algorithmus nur einen Schritt weiter laufen.
    for (int i = 1; i <= knotenAnzahl; i++) {
      // Es wird der Knoten mit der kürzesten Entfernung zum Startknoten
      // aus den noch nicht besuchten Knoten auswählt. Beim ersten
      // Durchlauf ist dieser Knoten identisch mit dem Startknoten.
      ausgewählterKnoten = -1;
      int entfernung = Integer.MAX_VALUE;
      for (int j = 0; j < knotenAnzahl; j++) {
        if (!besucht[j] && kürzesteEntfernungen[j] < entfernung) {
          ausgewählterKnoten = j;
          entfernung = kürzesteEntfernungen[j];
        }
      }

      // Markiere den ausgewählten Knoten als besucht.
      besucht[ausgewählterKnoten] = true;

      // Hier werden die kürzesten Entfernung der benachbarten Knoten des ausgewählten
      // Knoten aktualisiert.
      for (int j = 0; j < knotenAnzahl; j++) {
        int kantenEntfernung = matrix.matrix[ausgewählterKnoten][j];
        if (kantenEntfernung > 0 && ((entfernung + kantenEntfernung) < kürzesteEntfernungen[j])) {
          vorgänger[j] = ausgewählterKnoten;
          kürzesteEntfernungen[j] = entfernung + kantenEntfernung;
        }
      }
      reporter.speichereSchritt();
    }

    reporter.beende();
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
    return new Dijkstra(einfachesGraphenFormat).sucheKürzestenPfadMatrix(anfangsKnoten);
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
    d.reporter.gibErgebnisTabelle();
    d.reporter.gibZwischenschrittTabelle();
  }
}
