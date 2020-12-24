package org.bschlangaul.graph;

import org.bschlangaul.helfer.Farbe;
import java.util.Stack;

/**
 * nach Schulbuch: Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class TiefenSucheStapel extends GraphAdjazenzMatrix {
  // Liste der besuchten Knoten
  private boolean[] besucht;

  // Stapel für die Tiefensuche
  private Stack<String> stapel;
  private Stack<String> route;

  /**
   * Die maximale Anzahl der Knoten wird dabei festgelegt.
   *
   * @param maximaleKnoten Anzahl der maximal möglichen Knoten
   */
  public TiefenSucheStapel(int maximaleKnoten) {
    super(maximaleKnoten);
    besucht = new boolean[maximaleKnoten];
    stapel = new Stack<String>();
    route = new Stack<String>();
  }

  /**
   * Durchlauf aller Knoten und Ausgabe auf der Konsole
   *
   * @param knotenNummer Nummer des Startknotens
   */
  public void besucheKnoten(int knotenNummer) {
    besucht[knotenNummer] = true;
    stapel.push(knoten[knotenNummer].gibName());
    // Stapel ausgeben
    System.out.println(Farbe.grün("Stapel: ") + stapel.toString());
    while (!stapel.isEmpty()) {
      // oberstes Element des Stapels nehmen und in die Route einfügen
      String knotenName = stapel.pop();
      System.out.println(Farbe.rot("besucht: ") + knotenName);
      route.push(knotenName);

      // alle nicht besuchten Nachbarn von w in den Stapel einfügen
      for (int abzweigung = 0; abzweigung <= anzahlKnoten - 1; abzweigung++) {
        if (matrix[gibKnotenNummer(knotenName)][abzweigung] > 0 && !besucht[abzweigung]) {
          besucht[abzweigung] = true;
          stapel.push(knoten[abzweigung].gibName());
        }
      }
      // Stapel ausgeben
      System.out.println(Farbe.grün("Stapel: ") + stapel.toString());
    }
    // Route ausgeben
    System.out.println(Farbe.gelb("Route: ") + route.toString());
  }

  /**
   * Start der Tiefensuche
   *
   * @param startKnoten Bezeichnung des Startknotens
   */
  public void starteTiefenSucheStapel(String startKnoten) {
    int startnummer;
    startnummer = gibKnotenNummer(startKnoten);

    if (startnummer != -1) {
      for (int i = 0; i <= anzahlKnoten - 1; i++) {
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

    ts.starteTiefenSucheStapel("A");
  }
}
