package org.bschlangaul.baum.bbaum;

import java.util.Vector;

/**
 * Ein Seite (bzw. ein Knoten) eines B-Baums. Eine Seite enthält mehrer
 * Schlüsselwerte und mehrere Verweise auf andere Seiten.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Seite {

  /**
   * Eine Konstante für eine Blatt-Seite, d. h. die Seite hat keine Kinder.
   */
  public static final int BLATT_SEITE = 0;

  /**
   * Eine Konstante für eine Knoten-Seite, d. h. die Seite hat Kinder.
   */
  public static final int INNERE_SEITE = 1;

  /**
   * Diese Konstante wird verwendet, wenn der Schlüsselwert gefunden wird.
   */
  public static final int SCHLÜSSEL_GEFUNDEN = -1;

  /**
   * Diese Konstante wird verwendet, wenn der Schlüsselwert nicht gefunden wird.
   */
  public static final int SCHLÜSSEL_NICHT_GEFUNDEN = -2;

  /**
   * Ein Verweis zum Elternknoten.
   */
  Seite eltern = null;

  /**
   * Eine Liste zum Speichern der Schlüssel.
   */
  Vector<Comparable> schlüsselListe;

  /**
   * Eine Liste zum Speichern von Verweisen.
   */
  Vector<Seite> kinderListe;

  /**
   * Der Type einer Seite (Blatt-Seite (0) oder innere Seite (1)).
   */
  int seitenTyp;

  int ordnung;

  /**
   * @param setenTyp Der Type einer Seite (Blatt-Seite (0) oder innere Seite (1)).
   */
  public Seite(int seitenTyp, int ordnung) {
    this.seitenTyp = seitenTyp;
    this.ordnung = ordnung;
    schlüsselListe = new Vector<Comparable>();
    // Zeiger werden nur bei inneren Seite angelegt.
    kinderListe = (seitenTyp == INNERE_SEITE ? new Vector<Seite>() : null);
  }

  public int gibAnzahlSchlüssel() {
    return schlüsselListe.size();
  }

  /**
   * Gib den Schlüssel durch die Index-Nummer zurück. 0 gibt den ersten Schlüssel
   * in der Liste, 1 den zweiten etc.
   *
   * @param index Eine Index-Nummer von 0 weg gezählt.
   *
   * @return Den Schlüsselwert.
   */
  public Comparable gibSchlüssel(int index) {
    return schlüsselListe.get(index);
  }

  /**
   * Setze die Schlüssel
   *
   * @param mehrereSchlüssel Ein Feld mit mehreren Schlüsselwerten.
   */
  public void setzeSchlüssel(Comparable... mehrereSchlüssel) {
    for (Comparable schlüssel : mehrereSchlüssel) {
      schlüsselListe.add(schlüssel);
    }
  }

  /**
   * Gib die Anzahl der Kinder-Seiten zurück.
   *
   * @return Die Anzahl der Kinder-Seiten.
   */
  public int gibAnzahlKinder() {
    if (kinderListe != null)
      return kinderListe.size();
    return 0;
  }

  /**
   * Gib Kinder dieser Seite als Vector zurück.
   *
   * @return Instanzen des Klasse {@link Seite} als Vector.
   */
  public Vector gibKinder() {
    return kinderListe;
  }


  /**
   * Setze die Kindseiten.
   *
   * @param mehrereSeiten Ein Feld mit mehreren Seiten.
   */
  public void setzeKinder(Seite... mehrereSeiten) {
    for (Seite seite : mehrereSeiten) {
      kinderListe.add(seite);
    }
  }


  public Seite gibKindDurchIndex(int index) {
    return kinderListe.get(index);
  }

  public Seite gibEltern() {
    return eltern;
  }

  public void setzeSeitenTyp(int seitenTyp) {
    this.seitenTyp = seitenTyp;
    if (kinderListe == null && seitenTyp == INNERE_SEITE)
      kinderListe = new Vector<Seite>();
  }

  /**
   * Suche den Schlüssel in der Seite (nach Saake Seite 391).
   *
   * @param schlüssel Der Schlüsselwert, nach dem gesucht wird.
   * @param ergebnis  In diesem Feld wird das Ergebnis gespeichert.
   *
   * @return Wird der Schlüssel gefunden, geben wird SCHLÜSSEL_GEFUNDEN (-1)
   *         zurück. Wenn es ein innerer Knoten ist, geben wir den letzten Verweis
   *         zurück, im Fall eines Blattes SCHLÜSSEL_NICHT_GEFUNDEN (-2).
   */
  public int findeSchlüsselInSeite(Comparable schlüssel, Comparable[] ergebnis) {
    for (int i = 0; i < schlüsselListe.size(); i++) {
      int erg = schlüsselListe.get(i).compareTo(schlüssel);
      if (erg == 0) {
        ergebnis[0] = schlüsselListe.get(i);
        return SCHLÜSSEL_GEFUNDEN;
      } else if (erg > 0)
        return seitenTyp == INNERE_SEITE ? i : SCHLÜSSEL_NICHT_GEFUNDEN;
    }
    return (seitenTyp == INNERE_SEITE ? schlüsselListe.size() : SCHLÜSSEL_NICHT_GEFUNDEN);
  }

  /**
   * Füge einen Schlüsselwert in eine Seite ein. (Vergleiche Saake Seite 395-396.)
   *
   * @param schlüssel          Der Schlüsselwert, der eingefügt werden soll.
   * @param linkesGeschwister  Die linke Geschwister-Seite.
   * @param rechtesGeschwister Die rechte Geschwister-Seite.
   *
   * @return Wahr, wenn der Schlüsselwert in die Seite eingefügt werden konnte.
   */
  public boolean fügeInSeiteEin(Comparable schlüssel, Seite linkesGeschwister, Seite rechtesGeschwister) {
    boolean erledigt = false;
    // Position für Schlüssel suchen
    for (int i = 0; i < schlüsselListe.size(); i++) {
      int ergebnis = schlüsselListe.get(i).compareTo(schlüssel);
      if (ergebnis == 0) {
        // Schlüssel existiert schon -> ignorieren
        erledigt = true;
        break;
      } else if (ergebnis > 0) {
        // Stelle gefunden -> einfügen
        schlüsselListe.insertElementAt(schlüssel, i);
        if (rechtesGeschwister != null) {
          kinderListe.insertElementAt(rechtesGeschwister, i + 1);
          rechtesGeschwister.eltern = this;
        }
        erledigt = true;
        break;
      }
    }
    if (!erledigt) {
      // Schlüssel muss am Ende eingefügt werden
      schlüsselListe.add(schlüssel);
      if (linkesGeschwister != null && kinderListe.isEmpty()) {
        kinderListe.add(linkesGeschwister);
        linkesGeschwister.eltern = this;
      }
      if (rechtesGeschwister != null) {
        kinderListe.add(rechtesGeschwister);
        rechtesGeschwister.eltern = this;
      }
    }
    // Knoten zu groß
    return schlüsselListe.size() > ordnung * 2;
  }

  /**
   * Teile eine Seite (Nach Saake Seite 396)
   *
   * @return Die neu erzeugte Geschwisterseite.
   */
  public Seite teile() {
    int pos = gibAnzahlSchlüssel() / 2;
    // Geschwisterknoten erzeugen
    Seite geschwister = new Seite(seitenTyp, ordnung);
    for (int i = pos + 1; i < gibAnzahlSchlüssel(); i++) {
      // die obere Hälfte der Schlüssel und Verweise kopieren
      geschwister.schlüsselListe.add(this.gibSchlüssel(i));
      if (seitenTyp == Seite.INNERE_SEITE)
        geschwister.kinderListe.add(this.gibKindDurchIndex(i));
    }
    // es gibt einen Verweis mehr als Schlüssel
    if (seitenTyp == Seite.INNERE_SEITE)
      geschwister.kinderListe.add(this.gibKindDurchIndex(gibAnzahlSchlüssel()));
    // und anschließend im Originalknoten löschen
    for (int i = gibAnzahlSchlüssel() - 1; i >= pos; i--) {
      schlüsselListe.remove(pos);
      if (seitenTyp == Seite.INNERE_SEITE)
        kinderListe.remove(pos + 1);
    }
    return geschwister;
  }
}
