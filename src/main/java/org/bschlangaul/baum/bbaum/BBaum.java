package org.bschlangaul.baum.bbaum;

/**
 * Eine Implemenation eines B-Baum (nach Saake Seite 388-389).
 */
@SuppressWarnings({ "rawtypes" })
public class BBaum {

  private Seite wurzel = null;

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
    wurzel = new Seite(Seite.BLATT_SEITE, ordnung);
  }

  /**
   * Gib die oberste Seite, die sogenannte Wurzel zurück.
   *
   * @return Die Wurzelseite.
   */
  public Seite gibWurzel() {
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
    Seite seite = wurzel; // Startknoten
    boolean beendet = false;
    Comparable[] ergebnis = { null };
    do {
      // Suche Schlüssel im aktuellen Knoten
      int index = seite.findeSchlüsselInSeite(schlüssel, ergebnis);
      if (index == Seite.SCHLÜSSEL_GEFUNDEN || index == Seite.SCHLÜSSEL_NICHT_GEFUNDEN)
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
    Seite linkesGeschwister = null, rechtesGeschwister = null;
    // Suche Blattknoten, der den Schlüssel aufnimmt
    Seite seite = findeBlattSeite(schlüssel);
    // Schlüssel einfügen
    while (seite.fügeInSeiteEin(schlüssel, linkesGeschwister, rechtesGeschwister)) {
      // Split erforderlich
      int pos = seite.gibAnzahlSchlüssel() / 2;
      schlüssel = seite.gibSchlüssel(pos);
      Seite eltern = seite.gibEltern();
      if (eltern == null)
        // ein neuer Elternknoten muss angelegt werden
        eltern = new Seite(Seite.INNERE_SEITE, ordnung);
      // Split durchführen
      linkesGeschwister = seite;
      rechtesGeschwister = seite.teile();
      // Wurzel anpassen
      if (wurzel == seite)
        wurzel = eltern;
      // der aktuelle Knoten ist jetzt der Elternknoten
      seite = eltern;
      // und der muss ein innerer Knoten sein
      seite.setzeSeitenTyp(Seite.INNERE_SEITE);
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
  private Seite findeBlattSeite(Comparable schlüssel) {
    Seite seite = wurzel;
    Comparable[] key = { null }; // wird eigentlich nicht benötigt
    // den Baum von der Wurzel aus nach unten durchlaufen,
    // bis ein Blattknoten gefunden wurde
    while (seite.seitenTyp != Seite.BLATT_SEITE) {
      // Verweis verfolgen
      seite = seite.gibKindDurchIndex(seite.findeSchlüsselInSeite(schlüssel, key));
    }
    return seite;
  }

  public Seite macheBlattSeite(Comparable... schlüssel) {
    Seite seite = new Seite(Seite.BLATT_SEITE, ordnung);
    seite.setzeSchlüssel(schlüssel);
    return seite;
  }

  public Seite macheInnereSeite(Comparable... schlüssel) {
    Seite seite = new Seite(Seite.INNERE_SEITE, ordnung);
    seite.setzeSchlüssel(schlüssel);
    return seite;
  }

  public static void main(String[] args) {
    BBaum baum = new BBaum(2);

    baum.wurzel.setzeSchlüssel(10, 20);
    Seite seite1 = baum.macheBlattSeite(5, 6, 7, 8);
    Seite seite2 = baum.macheBlattSeite(12, 14, 16, 18);
    Seite seite3 = baum.macheBlattSeite(25, 30);
    baum.wurzel.setzeSeitenTyp(Seite.INNERE_SEITE);
    baum.wurzel.setzeKinder(seite1, seite2, seite3);
    seite1.eltern = baum.wurzel;
    seite2.eltern = baum.wurzel;
    seite3.eltern = baum.wurzel;
    TexBBaumReporter.druckeBaum(baum);

  }
}
