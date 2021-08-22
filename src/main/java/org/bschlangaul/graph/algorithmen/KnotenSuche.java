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
public class KnotenSuche extends GraphAdjazenzMatrix {

  public KnotenSuche(int maximaleKnoten) {
    super(maximaleKnoten);
    besucht = new boolean[maximaleKnoten];
  }
  /**
   * Der Schnappschuss wird entweder erstellt, nachdem ein Knoten besucht wurde,
   * oder ein Knoten entfernt wurde.
   */
  class SchnappSchuss {
    String besuchterKnoten;
    String entnommenerKnoten;

    public SchnappSchuss(Collection<String> liste) {
      this.kloneListe(liste);
    }

    /**
     * Eine Kopie des referenzierten Stapels/Warteschlange als einfaches Feld.
     */
    Object[] liste;

    void kloneListe(Collection<String> liste) {
      this.liste = liste.toArray();
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
    Collection<String> stapel;

    public Protokoll(Collection<String> stapel) {
      this.schnappSchuesse = new ArrayList<SchnappSchuss>();
      this.stapel = stapel;
    }

    void merkeBesuch(String knotenName) {
      schnappSchuesse.add(new SchnappSchuss(stapel).merkeBesuch(knotenName));
    }

    void merkeEntnahme(String knotenName) {
      schnappSchuesse.add(new SchnappSchuss(stapel).merkeEntnahme(knotenName));
    }
  }

  /**
   * Liste der besuchten Knoten
   */
  private boolean[] besucht;

  /**
   * Stapel für die Tiefensuche
   */
  private Collection<String> liste;
  private Vector<String> route;

  Protokoll protokoll = new Protokoll(liste);


  /**
   * Umgedreht ausgeben
   */
  private String gibStapelAlsText() {
    String[] ausgabe = liste.toArray(new String[] {});
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
    System.out.println(Farbe.gelb(gibStapelAlsText()));
  }

  public void besuche(int knotenNummer) {
    String name = gibKnotenName(knotenNummer);
    besucht[knotenNummer] = true;
    route.add(name);
    liste.add(name);
    protokoll.merkeBesuch(name);
    druckeZeile(null, name);
  }

  public void führeAus(String startKnoten) {
  }
}
