package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;
import org.bschlangaul.helfer.Farbe;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * nach Schulbuch Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class BreitenSucheWarteschlange extends GraphAdjazenzMatrix {

  /**
   * Der Schnappschuss wird entweder erstellt, nachdem ein Knoten besucht wurde,
   * oder ein Knoten aus dem Stapel entfernt wurde.
   */
  class SchnappSchuss {
    String besuchterKnoten;
    String entnommenerKnoten;

    public SchnappSchuss(Vector<String> warteschlange) {
      this.kopiereWarteschlange(warteschlange);
    }

    /**
     * Eine Kopie der referenzierten Warteschlange als einfaches Feld.
     */
    Object[] warteschlange;

    void kopiereWarteschlange(Vector<String> warteschlange) {
      this.warteschlange = warteschlange.toArray();
    }

    SchnappSchuss merkeBesuch(String knotenName) {
      this.besuchterKnoten = knotenName;
      return this;
    }

    SchnappSchuss merkeEntnahme(String knotenName) {
      this.entnommenerKnoten = knotenName;
      return this;
    }
  }

  class Protokoll {
    List<SchnappSchuss> schnappSchuesse;

    /**
     * Eine Referenze auf den vom Algorithmus verwendeten Stapel.
     */
    Vector<String> warteschlange;

    public Protokoll(Vector<String> warteschlange) {
      this.schnappSchuesse = new ArrayList<SchnappSchuss>();
      this.warteschlange = warteschlange;
    }

    void merkeBesuch(String knotenName) {
      schnappSchuesse.add(new SchnappSchuss(warteschlange).merkeBesuch(knotenName));
    }

    void merkeEntnahme(String knotenName) {
      schnappSchuesse.add(new SchnappSchuss(warteschlange).merkeEntnahme(knotenName));
    }
  }

  /**
   * Liste der besuchten Knoten
   */
  private boolean[] besucht;

  /**
   * Eine Warteschlange für die Breitensuche
   */
  private Vector<String> warteschlange = new Vector<String>();
  private Vector<String> route = new Vector<String>();

  Protokoll protokoll = new Protokoll(warteschlange);

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
   * Gib Ausgleichsleerzeichen, die vorne oder hinten an den Knotennamen angehängt
   * werden können, sodass die Textausgabe in der Konsole schöne ausgerichtet ist.
   *
   * @param name Der Name des Knoten.
   *
   * @return 0 oder mehr Leerzeichen.
   */
  private String gibLeerzeichen(String name) {
    int anzahl = gibMaximaleKnotennameTextbreite() - name.length();
    if (anzahl > 0) {
      return " ".repeat(anzahl);
    }
    return "";
  }

  private void druckeZeile(String entferne, String fügeHinzu) {
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
    System.out.println(Farbe.gelb(warteschlange.toString()));
  }

  public void besuche(int knotenNummer) {
    String name = gibKnotenName(knotenNummer);
    besucht[knotenNummer] = true;
    route.add(name);
    warteschlange.add(name);
    protokoll.merkeBesuch(name);

    druckeZeile(null, name);
  }

  /**
   * Durchlauf aller Knoten und Ausgabe auf der Konsole
   *
   * @param knotenNummer Nummer des Startknotens
   */
  private void besucheKnoten(int knotenNummer) {
    besuche(knotenNummer);

    while (!warteschlange.isEmpty()) {
      // oberstes Element der Liste nehmen
      String knotenName = warteschlange.remove(0);
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

  /**
   * Start der Breitensuche
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
    BreitenSucheWarteschlange bs = new BreitenSucheWarteschlange(
        "a--e; a--f; a--s; b--c; b--d; b--h; c--d; c--h; c--s; d--h; e--f; f--s; g--s; h--s;");
    bs.gibMatrixAus();
    bs.führeAus("s");
  }

}
