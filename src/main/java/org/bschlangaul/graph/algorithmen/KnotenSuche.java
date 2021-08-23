package org.bschlangaul.graph.algorithmen;

import org.bschlangaul.graph.GraphAdjazenzMatrix;
import org.bschlangaul.helfer.Farbe;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Vector;

/**
 * Diese Klasse wir von der Tiefen- oder der Breitensuche mit Stapel oder
 * Warteschlange geerbt.
 */
public abstract class KnotenSuche extends GraphAdjazenzMatrix {

  private void initialisiereKnotenSuche(int maximaleKnoten) {
    besucht = new boolean[maximaleKnoten];
    route = new Vector<String>();
  }

  public KnotenSuche(int maximaleKnoten) {
    super(maximaleKnoten);
    initialisiereKnotenSuche(maximaleKnoten);
  }

  /**
   * Die Adjazenzmatrix kann mit diesem Konstruktur im einfachen Graphenformat
   * spezifiziert werden.
   *
   * @param einfachesGraphenFormat Ein String im einfachen Graphenformat.
   */
  public KnotenSuche(String einfachesGraphenFormat) {
    super(einfachesGraphenFormat);
    initialisiereKnotenSuche(gibKnotenAnzahl());
  }

  /**
   * Der Schnappschuss wird entweder erstellt, nachdem ein Knoten besucht wurde,
   * oder ein Knoten entfernt wurde.
   */
  class SchnappSchuss {
    String besuchterKnoten;
    String entnommenerKnoten;

    public SchnappSchuss(Collection<String> speicher) {
      this.kloneListe(speicher);
    }

    /**
     * Eine Kopie des referenzierten Stapels/Warteschlange als einfaches Feld.
     */
    Object[] speicher;

    void kloneListe(Collection<String> speicher) {
      this.speicher = speicher.toArray();
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
    Collection<String> speicher;

    public Protokoll(Collection<String> speicher) {
      this.schnappSchuesse = new ArrayList<SchnappSchuss>();
      this.speicher = speicher;
    }

    void merkeBesuch(String knotenName) {
      schnappSchuesse.add(new SchnappSchuss(speicher).merkeBesuch(knotenName));
    }

    void merkeEntnahme(String knotenName) {
      schnappSchuesse.add(new SchnappSchuss(speicher).merkeEntnahme(knotenName));
    }
  }

  /**
   * Liste der besuchten Knoten
   */
  protected boolean[] besucht;

  /**
   * Zwischenspeicher: Stapel für die Tiefensuche, Warteschlange für die
   * Breitensuche
   */
  protected Collection<String> speicher;
  protected Vector<String> route;

  Protokoll protokoll = new Protokoll(speicher);

  /**
   * Umgedreht ausgeben
   */
  protected String gibStapelAlsText() {
    String[] ausgabe = speicher.toArray(new String[] {});
    for (int i = 0; i < ausgabe.length / 2; i++) {
      String tmp = ausgabe[i];
      ausgabe[i] = ausgabe[ausgabe.length - i - 1];
      ausgabe[ausgabe.length - i - 1] = tmp;
    }
    for (int i = 0; i < ausgabe.length; i++) {

    }
    return "[" + String.join(", ", ausgabe) + "]";
  }

  /**
   * Gib Ausgleichsleerzeichen, die vorne oder hinten an den Knotennamen angehängt
   * werden können, sodass die Textausgabe in der Konsole schöner ausgerichtet ist.
   *
   * @param name Der Name des Knoten.
   *
   * @return 0 oder mehr Leerzeichen.
   */
  protected String gibLeerzeichen(String name) {
    int anzahl = gibMaximaleKnotennameTextbreite() - name.length();
    if (anzahl > 0) {
      return " ".repeat(anzahl);
    }
    return "";
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
    speicher.add(name);
    protokoll.merkeBesuch(name);
    druckeZeile(null, name);
  }

  protected abstract void besucheKnoten(int startnummer);

  /**
   * Start der Tiefen/Breitensuche
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
}
