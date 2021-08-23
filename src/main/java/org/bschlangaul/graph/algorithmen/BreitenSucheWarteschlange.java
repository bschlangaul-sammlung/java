package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.helfer.Farbe;

import java.util.Vector;

/**
 * nach Schulbuch Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class BreitenSucheWarteschlange extends KnotenSuche {

  /**
   * Eine Warteschlange für die Breitensuche
   */
  Vector<String> speicher;

  /**
   * Die Adjazenzmatrix kann mit diesem Konstruktur im einfachen Graphenformat
   * spezifiziert werden.
   *
   * @param einfachesGraphenFormat Ein String im einfachen Graphenformat.
   */
  public BreitenSucheWarteschlange(String einfachesGraphenFormat) {
    super(einfachesGraphenFormat);
    initialisiereBreitensuche(gibKnotenAnzahl());
  }

  /**
   * Die maximale Anzahl der Knoten wird dabei festgelegt.
   *
   * @param maximaleKnoten Anzahl der maximal möglichen Knoten
   */
  public BreitenSucheWarteschlange(int maximaleKnoten) {
    super(maximaleKnoten);
    initialisiereBreitensuche(maximaleKnoten);
  }

  private void initialisiereBreitensuche(int maximaleKnoten) {
    speicher = new Vector<String>();
    protokoll.speicher = speicher;
    super.speicher = speicher;
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
    System.out.println(Farbe.gelb(speicher.toString()));
  }

  public void besuche(int knotenNummer) {
    String name = gibKnotenName(knotenNummer);
    besucht[knotenNummer] = true;
    route.add(name);
    speicher.add(name);
    protokoll.merkeBesuch(name);

    druckeZeile(null, name);
  }

  /**
   * Durchlauf aller Knoten und Ausgabe auf der Konsole
   *
   * @param knotenNummer Nummer des Startknotens
   */
  protected void besucheKnoten(int knotenNummer) {
    besuche(knotenNummer);

    while (!speicher.isEmpty()) {
      // oberstes Element der Liste nehmen
      String knotenName = speicher.remove(0);
      druckeZeile(knotenName, null);

      protokoll.merkeEntnahme(knotenName);

      // alle nicht besuchten Nachbarn von knotenName in die Liste einfügen
      for (int abzweigung = 0; abzweigung <= gibKnotenAnzahl() - 1; abzweigung++) {
        if (matrix[gibKnotenNummer(knotenName)][abzweigung] != NICHT_ERREICHBAR && !besucht[abzweigung]) {
          besuche(abzweigung);
        }
      }
    }
    // Route ausgeben
    System.out.println(Farbe.gelb("Route: ") + route.toString());
  }

  public static void main(String[] args) {
    BreitenSucheWarteschlange bs = new BreitenSucheWarteschlange(
        "a--e; a--f; a--s; b--c; b--d; b--h; c--d; c--h; c--s; d--h; e--f; f--s; g--s; h--s;");
    bs.gibMatrixAus();
    bs.führeAus("s");
  }

}
