package org.bschlangaul.graph;

import org.bschlangaul.helfer.Farbe;
import java.util.Vector;

/**
 * nach Schulbuch Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class BreitenSucheWarteschlange extends AdjazenzMatrix {

  // Liste der besuchten Knoten
  private boolean[] besucht;

  // Warteschlage für die Breitensuche
  private Vector<String> liste;
  private Vector<String> route;

  /**
   * Konstruktor für Objekte der Klasse BreitenSucheWarteschlange. Die maximale
   * Anzahl der Knoten wird dabei festgelegt.
   *
   * @param maximaleKnoten Anzahl der maximal möglichen Knoten
   *
   */
  public BreitenSucheWarteschlange(int maximaleKnoten) {
    super(maximaleKnoten);
    besucht = new boolean[maximaleKnoten];
    liste = new Vector<String>();
    route = new Vector<String>();
  }

  /**
   * Durchlauf aller Knoten und Ausgabe auf der Konsole
   *
   * @param knotenNummer Nummer des Startknotens
   */
  public void besucheKnoten(int knotenNummer) {
    besucht[knotenNummer] = true;
    liste.add(knoten[knotenNummer].gibName());
    // Liste gibMatrixAus
    System.out.println(Farbe.grün("Warteschlange: ") + liste.toString());
    while (!liste.isEmpty()) {
      // oberstes Element der Liste nehmen und in die Route einfügen
      String knotenName = liste.remove(0);
      System.out.println(Farbe.rot("besucht: ") + knotenName);
      route.add(knotenName);

      // alle nicht besuchten Nachbarn von knotenName in die Liste einfügen
      for (int abzweigung = 0; abzweigung <= anzahlKnoten - 1; abzweigung++) {
        if (matrix[gibKnotenNummer(knotenName)][abzweigung] > 0 && !besucht[abzweigung]) {
          besucht[abzweigung] = true;
          liste.add(knoten[abzweigung].gibName());
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
      for (int i = 0; i <= anzahlKnoten - 1; i++) {
        besucht[i] = false;
      }
      besucheKnoten(startnummer);
    }
  }

  public static void main(String[] args) {
    BreitenSucheWarteschlange bs = new BreitenSucheWarteschlange(20);
    bs.fügeKnotenUndKantenEin("a-e a-f a-s b-c b-d b-h c-d c-h c-s d-h e-f f-s g-s h-s");

    // bs.fügeKnotenEin("A");
    // bs.fügeKnotenEin("B");
    // bs.fügeKnotenEin("C");
    // bs.fügeKnotenEin("D");
    // bs.fügeKnotenEin("E");
    // bs.fügeKnotenEin("F");
    // bs.fügeKnotenEin("G");
    // bs.fügeKnotenEin("H");
    // bs.fügeKnotenEin("J");
    // bs.fügeKnotenEin("K");

    // bs.fügeKanteEin("A", "B", 1);
    // bs.fügeKanteEin("A", "C", 1);

    // bs.fügeKanteEin("B", "A", 1);
    // bs.fügeKanteEin("B", "D", 1);
    // bs.fügeKanteEin("B", "E", 1);

    // bs.fügeKanteEin("C", "A", 1);
    // bs.fügeKanteEin("C", "F", 1);
    // bs.fügeKanteEin("C", "G", 1);

    // bs.fügeKanteEin("D", "B", 1);
    // bs.fügeKanteEin("D", "H", 1);

    // bs.fügeKanteEin("E", "B", 1);
    // bs.fügeKanteEin("E", "F", 1);

    // bs.fügeKanteEin("F", "C", 1);
    // bs.fügeKanteEin("F", "E", 1);
    // bs.fügeKanteEin("F", "G", 1);
    // bs.fügeKanteEin("F", "J", 1);

    // bs.fügeKanteEin("G", "C", 1);
    // bs.fügeKanteEin("G", "F", 1);

    // bs.fügeKanteEin("H", "D", 1);

    // bs.fügeKanteEin("J", "F", 1);

    // bs.fügeKanteEin("K", "F", 1);

    bs.gibMatrixAus();

    bs.starteBreitenSuche("s");
  }

}
