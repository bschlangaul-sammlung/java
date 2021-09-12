package org.bschlangaul.baum;

/**
 * Eine Implementation eines binären Suchbaums. (Nach
 * Saake Seite 357)
 */
@SuppressWarnings({ "rawtypes" })
public class BinaererSuchBaum extends BinaerBaum {

  /**
   * Saake Seite 349
   */
  public BinaererSuchBaum() {
    kopf = new BaumKnoten(null);
    kopf.setzeRechts(null);
  }

  /**
   * {@inheritDoc}
   */
  public BaumKnoten gibKopf() {
    return kopf.gibRechts();
  }

  /**
   * {@inheritDoc}
   */
  public boolean fügeEin(Comparable schlüssel) {
    // Vergleiche Saake Seite 363.
    BaumKnoten eltern = kopf;
    BaumKnoten kind = gibKopf();
    while (kind != null) {
      eltern = kind;
      int vergleich = kind.vergleiche(schlüssel);
      if (vergleich == 0)
        return false;
      else
        kind = (vergleich > 0 ? kind.gibLinks() : kind.gibRechts());
    }
    BaumKnoten knoten = new BaumKnoten(schlüssel);
    if (eltern.vergleiche(schlüssel) > 0)
      eltern.setzeLinks(knoten);
    else
      eltern.setzeRechts(knoten);
    knoten.setzeLinks(null);
    knoten.setzeRechts(null);
    reporter.berichteBaum(" Nach dem Einfügen von „" + schlüssel + "“", this, 0);
    return true;
  }

  /**
   * Finde einen Knoten im Binärbaum. Vergleiche Saake Seite 362.
   *
   * @param schlüssel Der Schlüsselwert, nach dem gesucht werden soll.
   *
   * @return Der Knoten, in dem sich der Schlüsselwert befindet.
   */
  protected BaumKnoten findeKnoten(Comparable schlüssel) {
    BaumKnoten knoten = gibKopf();
    while (knoten != null) {
      int cmp = knoten.vergleiche(schlüssel);
      if (cmp == 0)
        return knoten;
      else
        knoten = (cmp > 0 ? knoten.gibLinks() : knoten.gibRechts());
    }
    return null;
  }

  /**
   * Finde einen Schlüssel im Binär Baum. Vergleiche Saake Seite 362.
   *
   * @param schlüssel Der Schlüssel, nach dem gesucht werden soll.
   *
   * @return Wahr, wenn der Schlüssel gefunden wurde.
   */
  public boolean finde(Comparable schlüssel) {
    return (findeKnoten(schlüssel) != null);
  }

  /**
   * {@inheritDoc}
   */
  public boolean entferne(Comparable schlüssel) {
    // Saake Seite 365-366
    BaumKnoten eltern = kopf;
    BaumKnoten knoten = gibKopf();
    BaumKnoten kind = null;
    BaumKnoten tmp = null;

    // zu löschenden Knoten suchen
    while (knoten != null) {
      int vergleich = knoten.vergleiche(schlüssel);
      if (vergleich == 0)
        break;
      else {
        eltern = knoten;
        knoten = (vergleich > 0 ? knoten.gibLinks() : knoten.gibRechts());
      }
    }
    if (knoten == null)
      // Kein Knoten gefunden
      return false;
    // Fall 1
    if (knoten.gibLinks() == null && knoten.gibRechts() == null)
      kind = null;
    // Fall 2
    else if (knoten.gibLinks() == null)
      kind = knoten.gibRechts();
    else if (knoten.gibRechts() == null)
      kind = knoten.gibLinks();
    else { // Fall 3
      // minimales Element suchen
      kind = knoten.gibRechts();
      tmp = knoten;
      while (kind.gibLinks() != null) {
        tmp = kind;
        kind = kind.gibLinks();
      }
      kind.setzeLinks(knoten.gibLinks());
      if (tmp != knoten) {
        tmp.setzeLinks(kind.gibRechts());
        kind.setzeRechts(knoten.gibRechts());
      }
    }
    if (eltern.gibLinks() == knoten)
      eltern.setzeLinks(kind);
    else
      eltern.setzeRechts(kind);
    reporter.berichteBaum(" Nach dem Löschen von „" + schlüssel + "“", this);

    return true;
  }
}
