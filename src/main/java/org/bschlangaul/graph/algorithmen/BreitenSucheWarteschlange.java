package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;
import org.bschlangaul.helfer.Farbe;
import java.util.Vector;

/**
 * nach Schulbuch Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class BreitenSucheWarteschlange extends GraphAdjazenzMatrix {

  /**
   * Liste der besuchten Knoten
   */
  private boolean[] besucht;

  /**
   * Warteschlage für die Breitensuche
   */
  private Vector<String> liste = new Vector<String>();
  private Vector<String> route = new Vector<String>();

  /**
   * Die Adjazenzmatrix kann mit diesem Konstruktur im einfachen Graphenformat
   * spezifiziert werden.
   *
   * @param einfachesGraphenFormat Ein String im einfachen Graphenformat.
   */
  public BreitenSucheWarteschlange(String einfachesGraphenFormat) {
    super(einfachesGraphenFormat);
    besucht = new boolean[gibKnotenAnzahl()];
  }

  /**
   * Durchlauf aller Knoten und Ausgabe auf der Konsole
   *
   * @param knotenNummer Nummer des Startknotens
   */
  private void besucheKnoten(int knotenNummer) {
    besucht[knotenNummer] = true;
    liste.add(gibKnotenName(knotenNummer));
    // Liste gibMatrixAus
    System.out.println(Farbe.grün("Warteschlange: ") + liste.toString());
    while (!liste.isEmpty()) {
      // oberstes Element der Liste nehmen und in die Route einfügen
      String knotenName = liste.remove(0);
      System.out.println(Farbe.rot("besucht: ") + knotenName);
      route.add(knotenName);

      // alle nicht besuchten Nachbarn von knotenName in die Liste einfügen
      for (int abzweigung = 0; abzweigung <= gibKnotenAnzahl() - 1; abzweigung++) {
        if (matrix[gibKnotenNummer(knotenName)][abzweigung] > 0 && !besucht[abzweigung]) {
          besucht[abzweigung] = true;
          liste.add(gibKnotenName(abzweigung));
        }
      }
      // Liste ausgeben
      System.out.println(Farbe.grün("Warteschlange: ") + liste.toString());
    }
    // Route ausgeben
    System.out.println(Farbe.gelb("Route: ") + route.toString());
  }

  /**
   * Start der Breitensuche
   *
   * @param startKnoten Bezeichnung des Startknotens
   */
  public void starteBreitenSuche(String startKnoten) {
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
    BreitenSucheWarteschlange bs = new BreitenSucheWarteschlange(
        "a--e; a--f; a--s; b--c; b--d; b--h; c--d; c--h; c--s; d--h; e--f; f--s; g--s; h--s");
    bs.gibMatrixAus();
    bs.starteBreitenSuche("s");
  }

}
