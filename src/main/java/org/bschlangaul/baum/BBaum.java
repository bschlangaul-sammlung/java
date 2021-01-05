package org.bschlangaul.baum;

import java.util.Vector;

/**
 * Eine Implemenation eines B-Baum (nach Saake Seite 388-389).
 */
@SuppressWarnings("rawtypes")
public class BBaum {

  /**
   * Ein Seite (bzw. ein Knoten) eines B-Baums. Eine Seite enthält mehrer
   * Schlüsselwerte und mehrere Verweise auf andere Seiten.
   */
  public class BBaumSeite {

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
    BBaumSeite eltern = null;

    /**
     * Eine Liste zum Speichern der Schlüssel.
     */
    Vector<Comparable> schlüsselListe;

    /**
     * Eine Liste zum Speichern von Verweisen.
     */
    Vector<BBaumSeite> kinderListe;

    /**
     * Der Type einer Seite (Blatt-Seite (0) oder innere Seite (1)).
     */
    int seitenTyp;

    public BBaumSeite(int seitenTyp) {
      this.seitenTyp = seitenTyp;
      schlüsselListe = new Vector<Comparable>();
      // Zeiger werden nur bei inneren Seite angelegt.
      kinderListe = (seitenTyp == INNERE_SEITE ? new Vector<BBaumSeite>() : null);
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
     * @return Instanzen des Klasse {@link BBaumSeite} als Vector.
     */
    public Vector gibKinder() {
      return kinderListe;
    }

    public BBaumSeite gibKindDurchIndex(int index) {
      return kinderListe.get(index);
    }

    public BBaumSeite gibEltern() {
      return eltern;
    }

    public void setzeSeitenType(int nt) {
      seitenTyp = nt;
      if (kinderListe == null && seitenTyp == INNERE_SEITE)
        kinderListe = new Vector<BBaumSeite>();
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
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    public boolean fügeInSeiteEin(Comparable schlüssel, BBaumSeite linkesGeschwister, BBaumSeite rechtesGeschwister) {
      boolean erledigt = false;
      // Position für Schlüssel suchen
      for (int i = 0; i < schlüsselListe.size(); i++) {
        int res = schlüsselListe.get(i).compareTo(schlüssel);
        if (res == 0) {
          // Schlüssel existiert schon -> ignorieren
          erledigt = true;
          break;
        } else if (res > 0) {
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
    public BBaumSeite teile() {
      int pos = gibAnzahlSchlüssel() / 2;
      // Geschwisterknoten erzeugen
      BBaumSeite geschwister = new BBaumSeite(seitenTyp);
      for (int i = pos + 1; i < gibAnzahlSchlüssel(); i++) {
        // die obere Hälfte der Schlüssel und Verweise kopieren
        geschwister.schlüsselListe.add(this.gibSchlüssel(i));
        if (seitenTyp == BBaumSeite.INNERE_SEITE)
          geschwister.kinderListe.add(this.gibKindDurchIndex(i));
      }
      // es gibt einen Verweis mehr als Schlüssel
      if (seitenTyp == BBaumSeite.INNERE_SEITE)
        geschwister.kinderListe.add(this.gibKindDurchIndex(gibAnzahlSchlüssel()));
      // und anschließend im Originalknoten löschen
      for (int i = gibAnzahlSchlüssel() - 1; i >= pos; i--) {
        schlüsselListe.remove(pos);
        if (seitenTyp == BBaumSeite.INNERE_SEITE)
          kinderListe.remove(pos + 1);
      }
      return geschwister;
    }
  }

  private BBaumSeite wurzel = null;

  private int ordnung;

  /**
   * Dieser Konstruktur erzeugt einen B-Baum mit einer bestimmten Ordnung.u
   *
   * @param ordnung Die Ordnung des B-Baums. Ist die Ordnung beispielsweise 2,
   *                dann muss jede Seite mindestens 2 Knoten und maximal 4 Knoten
   *                aufweisen.
   */
  public BBaum(int ordnung) {
    this.ordnung = ordnung;
    wurzel = new BBaumSeite(BBaumSeite.BLATT_SEITE);
  }

  /**
   * Gib die oberste Seite, die sogenannte Wurzel zurück.
   *
   * @return Die Wurzelseite.
   */
  public BBaumSeite gibWurzel() {
    return wurzel;
  }

  /**
   * Finde einen Schlüsselwert im B-Baum. (nach Saake Seite 391)
   *
   * @param schlüssel Der Schlüsselwert, der gesucht wird.
   *
   * @return Den gefunden Schlüsselwert.
   */
  public Comparable finde(Comparable schlüssel) {
    BBaumSeite seite = wurzel; // Startknoten
    boolean beendet = false;
    Comparable[] ergebnis = { null };
    do {
      // Suche Schlüssel im aktuellen Knoten
      int index = seite.findeSchlüsselInSeite(schlüssel, ergebnis);
      if (index == BBaumSeite.SCHLÜSSEL_GEFUNDEN || index == BBaumSeite.SCHLÜSSEL_NICHT_GEFUNDEN)
        // Schlüssel gefunden oder auf einem
        // Blattknoten nicht gefunden -> fertig
        beendet = true;
      else
        // anderenfalls Verweis verfolgen
        seite = seite.gibKindDurchIndex(index);
    } while (!beendet);
    return ergebnis[0];
  }

  /**
   * Füge einen Schlüsselwert in den B-Baum ein. (nach Saake Seite 393-394)
   *
   * @param schlüssel Der Schlüsselwert, der eingefügt werden soll.
   */
  public void fügeEin(Comparable schlüssel) {
    BBaumSeite linkesGeschwister = null, rechtesGeschwister = null;
    // Suche Blattknoten, der den Schlüssel aufnimmt
    BBaumSeite seite = findeBlattSeite(schlüssel);
    // Schlüssel einfügen
    while (seite.fügeInSeiteEin(schlüssel, linkesGeschwister, rechtesGeschwister)) {
      // Split erforderlich
      int pos = seite.gibAnzahlSchlüssel() / 2;
      schlüssel = seite.gibSchlüssel(pos);
      BBaumSeite eltern = seite.gibEltern();
      if (eltern == null)
        // ein neuer Elternknoten muss angelegt werden
        eltern = new BBaumSeite(BBaumSeite.INNERE_SEITE);
      // Split durchführen
      linkesGeschwister = seite;
      rechtesGeschwister = seite.teile();
      // Wurzel anpassen
      if (wurzel == seite)
        wurzel = eltern;
      // der aktuelle Knoten ist jetzt der Elternknoten
      seite = eltern;
      // und der muss ein innerer Knoten sein
      seite.setzeSeitenType(BBaumSeite.INNERE_SEITE);
    }
  }

  /**
   * Füge mehrere Schlüsselwerte auf einmal ein.
   *
   * @param mehrereSchlüssel Ein Feld mit mehreren Schlüsselwerten.
   */
  public void fügeEin(Comparable... mehrereSchlüssel) {
    for (Comparable schlüssel : mehrereSchlüssel) {
      fügeEin(schlüssel);
    }
  }

  /**
   * Finde die Blatt-Seite, in der der Schlüsselwert gespeichert ist. (nach Saake
   * Seite 395)
   *
   * @param schlüssel Der Schlüsselwert, nach dem gesucht werden soll.
   *
   * @return Die Blatt, in der der Schlüsselwert gespeichert ist.
   */
  private BBaumSeite findeBlattSeite(Comparable schlüssel) {
    BBaumSeite seite = wurzel;
    Comparable[] key = { null }; // wird eigentlich nicht benötigt
    // den Baum von der Wurzel aus nach unten durchlaufen,
    // bis ein Blattknoten gefunden wurde
    while (seite.seitenTyp != BBaumSeite.BLATT_SEITE) {
      // Verweis verfolgen
      seite = seite.gibKindDurchIndex(seite.findeSchlüsselInSeite(schlüssel, key));
    }
    return seite;
  }
}
