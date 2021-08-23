package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.helfer.Farbe;

import java.util.Stack;

/**
 * nach Schulbuch: Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class TiefenSucheStapel extends KnotenSuche {

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
    speicher = new Stack<String>();
  }

  protected void druckeZeile(String entferne, String fügeHinzu) {
    int spaltenBreite = gibMaximaleKnotennameTextbreite() + 5;
    if (entferne == null) {
      System.out.print(" ".repeat(spaltenBreite));
    } else {
      System.out.print(Farbe.rot("del ") + Farbe.rot(entferne) + gibLeerzeichen(entferne) + " ");
    }

    if (fügeHinzu == null) {
      System.out.print(" ".repeat(spaltenBreite));
    } else {
      System.out.print(Farbe.grün("add ") + Farbe.grün(fügeHinzu) + gibLeerzeichen(fügeHinzu) + " ");
    }
    System.out.println(Farbe.gelb(gibStapelAlsText()));
  }

  public void besuche(int knotenNummer) {
    String name = gibKnotenName(knotenNummer);
    besucht[knotenNummer] = true;
    route.add(name);
    speicher.push(name);
    protokoll.merkeBesuch(name);
    druckeZeile(null, name);
  }

  /**
   * Durchlauf aller Knoten und Ausgabe auf der Konsole
   *
   * @param knotenNummer Nummer des Startknotens
   */
  public void besucheKnoten(int knotenNummer) {
    besuche(knotenNummer);
    // Stapel ausgeben
    while (!speicher.isEmpty()) {
      // oberstes Element des Stapels nehmen und in die Route einfügen
      String knotenName = speicher.pop();
      protokoll.merkeEntnahme(knotenName);
      druckeZeile(knotenName, null);

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
