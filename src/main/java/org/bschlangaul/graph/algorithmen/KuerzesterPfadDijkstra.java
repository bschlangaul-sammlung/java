package org.bschlangaul.graph.algorithmen;

import java.util.ArrayList;
import java.util.List;

import org.bschlangaul.graph.Graph;
import org.bschlangaul.graph.GraphAdjazenzMatrix;
import org.bschlangaul.helfer.Farbe;
import org.bschlangaul.helfer.Tex;
import org.bschlangaul.helfer.Tabelle;

/**
 * https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/
 */
public class KuerzesterPfadDijkstra {

  class Bearbeitungsschritt {
    int nr;
    double[] entfernungen;
    int aktuellerKnoten;

    public Bearbeitungsschritt(int nr) {
      this.nr = nr;
      aktuellerKnoten = ausgewählterKnoten;
      entfernungen = new double[knotenAnzahl];
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

    /**
     * Speichert die Knoten-IDs in der Reihenfolge wie sie bearbeitet werden.
     */
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

    public void gibZwischenschrittTabelle(boolean alsTex) {
      int knotenVerschiebung = 2;
      int spaltenAnzahl = knotenAnzahl + knotenVerschiebung;
      String[] kopfzeile = new String[spaltenAnzahl];
      kopfzeile[0] = "Nr.";
      kopfzeile[1] = "besucht";
      for (int i = 0; i < knotenAnzahl; i++) {
        kopfzeile[i + knotenVerschiebung] = gibKnotenName(i);
      }

      String[][] zeilen = new String[schritte.size()][spaltenAnzahl];
      for (int i = 0; i < schritte.size(); i++) {
        // Nr.
        int schrittNummer = i;
        zeilen[i][0] = String.valueOf(schrittNummer);

        // besucht
        Bearbeitungsschritt schritt = schritte.get(schrittNummer);
        if (schrittNummer == 0)
          zeilen[i][1] = "";
        else
          zeilen[i][1] = gibKnotenName(schritt.aktuellerKnoten);

        // Entfernungen zu den Knoten
        for (int knotenNr = 0; knotenNr < schritt.entfernungen.length; knotenNr++) {
          double ergebnis = schritt.entfernungen[knotenNr];
          Object zelle;
          if (ergebnis == Integer.MAX_VALUE) {
            zelle = alsTex ? "$\\infty$" : "∞";
            // Damit erst im 1. Schritt der erste Knoten hervorgehoben ist und nicht schon
            // im 0. Schritt
          } else if ((schrittNummer != 0 && gibBearbeitungsNummer(knotenNr) == schrittNummer)
              || (schrittNummer == 1 && gibBearbeitungsNummer(knotenNr) == schrittNummer - 1)) {
            String z = String.valueOf(schritt.entfernungen[knotenNr]);
            zelle = alsTex ? Tex.makro("bf", z) : Farbe.rot(z);
          } else if (gibBearbeitungsNummer(knotenNr) < schrittNummer) {
            zelle = "|";
          } else {
            zelle = String.valueOf(schritt.entfernungen[knotenNr]);
          }
          zeilen[i][knotenNr + knotenVerschiebung] = String.valueOf(zelle);
        }
      }
      System.out.println(Tabelle.gibAus(kopfzeile, zeilen, alsTex));
    }

    public void gibErgebnisTabelle(boolean alsTex) {
      String[] kopfZeile = { "nach", "Entfernung", "Reihenfolge", "Pfad" };
      String[][] zeilen = new String[pfade.size()][4];
      for (int i = 0; i < pfade.size(); i++) {
        zeilen[i][0] = formatiereVonNach(i, alsTex);
        zeilen[i][1] = String.valueOf(kürzesteEntfernungen[i]);
        zeilen[i][2] = String.valueOf(gibBearbeitungsNummer(i));
        zeilen[i][3] = formatierePfade(pfade.get(i), alsTex);
      }
      System.out.println(Tabelle.gibAus(kopfZeile, zeilen, alsTex));
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
      if (aktuelleKnotenNr == KuerzesterPfadDijkstra.KEINE_VORGÄNGER) {
        return;
      }
      pfade.get(nachKnotenNr).add(aktuelleKnotenNr);
      sammlePfade(nachKnotenNr, vorgänger[aktuelleKnotenNr], vorgänger);
    }

    private String formatiereVonNach(int knotenNr, boolean alsTex) {
      String pfeil = " -> ";
      if (alsTex)
        pfeil = " $\\rightarrow$ ";
      return String.format("%s %s %s", gibKnotenName(startKnotenNr), pfeil, gibKnotenName(knotenNr));
    }

    private String formatierePfade(List<Integer> pfade, boolean alsTex) {
      String ausgabe = "";
      String pfeil = " -> ";
      if (alsTex)
        pfeil = " $\\rightarrow$ ";
      for (int i = pfade.size() - 1; i >= 0; i--) {
        ausgabe += gibKnotenName(pfade.get(i));
        if (i >= 1)
          ausgabe += pfeil;
      }
      return ausgabe;
    }

    private String gibKnotenName(int knotenNummer) {
      return graph.gibKnotenName(knotenNummer);
    }

  }

  String graphenFormat;

  public Graph graph;

  /**
   * In diesem Feld werden die kürzesten Entfernungen zu den einzelnen Knoten
   * gespeichert.
   */
  public double[] kürzesteEntfernungen;

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
   * Der aktuelle ausgewählte, besuchte Knoten. Zu Beginn des Algorithmus ist
   * keine Knoten ausgewählt, deshalb -1.
   */
  public int ausgewählterKnoten = -1;

  /**
   * Mit diesem Konstruktur wird die Adjazenzmatrix durch das einfache
   * Graphenformat erzeugt.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public KuerzesterPfadDijkstra(String graphenFormat) {
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
   *
   * @return Alle kürzesten Wege in einem Feld gespeichert.
   */
  public double[] sucheKürzestenPfadMatrix(String anfangsKnoten) {
    GraphAdjazenzMatrix matrix = new GraphAdjazenzMatrix(graphenFormat);
    startKnotenNr = matrix.gibKnotenNummer(anfangsKnoten);
    knotenAnzahl = matrix.gibKnotenAnzahl();
    graph = matrix;

    kürzesteEntfernungen = new double[knotenAnzahl];

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

    reporter.starte();
    reporter.speichereSchritt();

    // Feld mit dem die Vorgänger-Knoten des kürzesten Pfads gespeichert werden.
    // Ein Vorgänger-Knoten des Pfads gibt ab, über welchen Knoten man auf
    // kürzesten Weg zum Knoten kommt.
    vorgänger = new int[knotenAnzahl];

    // Der Anfangsknoten hat keinen Vorgänger.
    vorgänger[startKnotenNr] = KEINE_VORGÄNGER;

    // Hier startet der eigentliche Algorithmus.

    // Es würde auch i < knotenAnzahl reichen, aber für die Ergebnistabellen
    // lassen wir den Algorithmus nur einen Schritt weiter laufen.
    for (int i = 1; i <= knotenAnzahl; i++) {
      // Es wird der Knoten mit der kürzesten Entfernung zum Startknoten
      // aus den noch nicht besuchten Knoten auswählt. Beim ersten
      // Durchlauf ist dieser Knoten identisch mit dem Startknoten.
      ausgewählterKnoten = -1;
      double entfernung = Double.MAX_VALUE;
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
        double kantenEntfernung = matrix.matrix[ausgewählterKnoten][j];
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

  public double gibEntfernung(String knotenName) {
    return kürzesteEntfernungen[graph.gibKnotenNummer(knotenName)];
  }

  public static double[] sucheKürzestenPfad(String einfachesGraphenFormat, String anfangsKnoten) {
    return new KuerzesterPfadDijkstra(einfachesGraphenFormat).sucheKürzestenPfadMatrix(anfangsKnoten);
  }

  public static void main(String[] args) {
    // DijkstraAdjazenzMatrix dijkstra = new DijkstraAdjazenzMatrix("a -- b; b -- c:
    // 7; a -- d: 2; b -> d: 19");
    // dijkstra.sucheKürzestenPfad("c");

    // Dijkstra d = new Dijkstra(
    // "a->b: 1; a->e: 7; b->c: 3; c->d: 8; c->e: 3; e->f: 1; c->f: 6; f->c: 1;
    // f->d: 3");

    KuerzesterPfadDijkstra d = new KuerzesterPfadDijkstra(
        // "A: 1 4; B: 3 5; C: 3 3; D: 0 2; E: 5 5; F: 5 1; G: 3 0; H: 6 3; I: 8 4; A --
        // B: 2; A -- C: 5; A -- D: 2; B -- C: 3; B -- E; C -- D: 3; C -- E; C -- F; C
        // -- H; D -- G: 2; E -- I: 7; F -- G: 2; F -- H: 3; H -- I;"
        "s: -1 0; a: 6 0; b: 0 -1; c: 4 0; d: 1 2; e: 2 0; f: 6 2; g: 2 -2; h: -2 -2; a -- c: 4; a -- f: 9; b -- e: 6; b -- g: 4; b -- s: 3; c -- d: 7; c -- e: 5; c -- f: 1; d -- e: 3; d -- f: 4; d -- s: 8; e -- g: 4; e -- s: 13; g -- h: 3; h -- s: 5;");

    d.sucheKürzestenPfadMatrix("s");
    d.reporter.gibErgebnisTabelle(false);
    d.reporter.gibZwischenschrittTabelle(false);
  }
}
