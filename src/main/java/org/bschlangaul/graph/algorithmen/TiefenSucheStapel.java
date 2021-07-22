package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;
import org.bschlangaul.helfer.Farbe;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Der Schnappschuss wird entweder erstellt, nachdem ein Knoten besucht wurde,
 * oder ein Knoten aus dem Stapel entfernt wurde.
 */
class SchnappSchuss {
  String besuchterKnoten;
  String entnommenerKnotenStapel;

  public SchnappSchuss(Stack<String> stapel) {
    this.kopiereStapel(stapel);
  }

  /**
   * Eine Kopie des referenzierten Stapels als einfaches Feld.
   */
  Object[] stapel;

  void kopiereStapel(Stack<String> stapel) {
    this.stapel = stapel.toArray();
  }

  SchnappSchuss besucheKnoten(String knotenName) {
    this.besuchterKnoten = knotenName;
    return this;
  }

  SchnappSchuss entnimmKnotenStapel(String knotenName) {
    this.entnommenerKnotenStapel = knotenName;
    return this;
  }
}

class Protokoll {
  List<SchnappSchuss> schnappSchuesse;

  /**
   * Eine Referenze auf den vom Algorithmus verwendeten Stapel.
   */
  Stack<String> stapel;

  public Protokoll(Stack<String> stapel) {
    this.schnappSchuesse = new ArrayList<SchnappSchuss>();
    this.stapel = stapel;
  }

  void merkeBesuch(String knotenName) {
    schnappSchuesse.add(new SchnappSchuss(stapel).besucheKnoten(knotenName));
  }

  void merkeEntnahme(String knotenName) {
    schnappSchuesse.add(new SchnappSchuss(stapel).entnimmKnotenStapel(knotenName));
  }
}

/**
 * nach Schulbuch: Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class TiefenSucheStapel extends GraphAdjazenzMatrix {

  /**
   * Liste der besuchten Knoten
   */
  private boolean[] besucht;

  /**
   * Stapel für die Tiefensuche
   */
  private Stack<String> stapel = new Stack<String>();
  private Vector<String> route = new Vector<String>();

  Protokoll protokoll = new Protokoll(stapel);

  /**
   * Die maximale Anzahl der Knoten wird dabei festgelegt.
   *
   * @param maximaleKnoten Anzahl der maximal möglichen Knoten
   */
  public TiefenSucheStapel(int maximaleKnoten) {
    super(maximaleKnoten);
    initialisiereTiefensuche(maximaleKnoten);
  }

  /**
   * Die Adjazenzmatrix kann mit diesem Konstruktur im einfachen Graphenformat
   * spezifiziert werden.
   *
   * @param einfachesGraphenFormat Ein String im einfachen Graphenformat.
   */
  public TiefenSucheStapel(String einfachesGraphenFormat) {
    super(einfachesGraphenFormat);
    initialisiereTiefensuche(gibKnotenAnzahl());
  }

  private void initialisiereTiefensuche(int maximaleKnoten) {
    besucht = new boolean[maximaleKnoten];
    route = new Stack<String>();
  }

  public void besuche(int knotenNummer) {
    String name = gibKnotenName(knotenNummer);
    besucht[knotenNummer] = true;
    route.add(name);
    stapel.push(name);
    protokoll.merkeBesuch(name);
    System.out.println(Farbe.rot("besucht: ") + name);
    System.out.println(Farbe.grün("Stapel: ") + stapel.toString());
  }

  /**
   * Durchlauf aller Knoten und Ausgabe auf der Konsole
   *
   * @param knotenNummer Nummer des Startknotens
   */
  public void besucheKnoten(int knotenNummer) {
    besuche(knotenNummer);
    // Stapel ausgeben
    while (!stapel.isEmpty()) {
      // oberstes Element des Stapels nehmen und in die Route einfügen
      String knotenName = stapel.pop();
      protokoll.merkeEntnahme(knotenName);
      System.out.println(Farbe.gelb("Aus dem Stapel entfernen: ") + knotenName);

      // alle nicht besuchten Nachbarn von w in den Stapel einfügen
      for (int abzweigung = 0; abzweigung <= gibKnotenAnzahl() - 1; abzweigung++) {
        if (matrix[gibKnotenNummer(knotenName)][abzweigung] != NICHT_ERREICHBAR && !besucht[abzweigung]) {
          besuche(abzweigung);
        }
      }
    }
    // Route ausgeben
    System.out.println("\n" + Farbe.gelb("Route: ") + route.toString());
  }

  /**
   * Start der Tiefensuche
   *
   * @param startKnoten Bezeichnung des Startknotens
   */
  public void führeAus(String startKnoten) {
    int startnummer;
    startnummer = gibKnotenNummer(startKnoten);

    if (startnummer != -1) {
      for (int i = 0; i <= gibKnotenAnzahl() - 1; i++) {
        besucht[i] = false;
      }
      besucheKnoten(startnummer);
    }
  }

  public static void main(String[] args) {
    TiefenSucheStapel ts = new TiefenSucheStapel(20);

    ts.setzeKnoten("A");
    ts.setzeKnoten("B");
    ts.setzeKnoten("C");
    ts.setzeKnoten("D");

    ts.setzeKnoten("E");
    ts.setzeKnoten("F");
    ts.setzeKnoten("G");
    ts.setzeKnoten("H");
    ts.setzeKnoten("J");
    ts.setzeKnoten("K");

    ts.setzeUngerichteteKante("A", "B", 1);
    ts.setzeUngerichteteKante("A", "C", 1);

    ts.setzeUngerichteteKante("B", "A", 1);
    ts.setzeUngerichteteKante("B", "D", 1);
    ts.setzeUngerichteteKante("B", "E", 1);

    ts.setzeUngerichteteKante("C", "A", 1);
    ts.setzeUngerichteteKante("C", "F", 1);
    ts.setzeUngerichteteKante("C", "G", 1);

    ts.setzeUngerichteteKante("D", "B", 1);
    ts.setzeUngerichteteKante("D", "H", 1);

    ts.setzeUngerichteteKante("E", "B", 1);
    ts.setzeUngerichteteKante("E", "F", 1);

    ts.setzeUngerichteteKante("F", "C", 1);
    ts.setzeUngerichteteKante("F", "E", 1);
    ts.setzeUngerichteteKante("F", "G", 1);
    ts.setzeUngerichteteKante("F", "J", 1);

    ts.setzeUngerichteteKante("G", "C", 1);
    ts.setzeUngerichteteKante("G", "F", 1);

    ts.setzeUngerichteteKante("H", "D", 1);

    ts.setzeUngerichteteKante("J", "F", 1);

    ts.setzeUngerichteteKante("K", "F", 1);

    ts.gibMatrixAus();

    ts.führeAus("A");
  }
}
