package org.bschlangaul.baum;

/**
 * Eine Implementation eines AVL-Baums. (Nach
 * <a href="https://www.baeldung.com/java-avl-trees">baeldung.com</a> bzw.
 * <a href=
 * "https://github.com/eugenp/tutorials/blob/master/data-structures/src/main/java/com/baeldung/avltree/AVLTree.java">Repository
 * auf Gibhub</a>)
 */
@SuppressWarnings({ "rawtypes" })
public class AVLBaum extends BinaerBaum {

  private AVLBaumKnoten kopf;

  /**
   * Um bei der entfernen-Methode einen boolschen Rückgabewert zu haben. Der
   * binäre Suchbaum gibt auch wahr oder falsch zurück.
   */
  private boolean gelöscht;

  private int gibHöhe(AVLBaumKnoten knoten) {
    return knoten == null ? -1 : knoten.höhe;
  }

  public int gibHöhe() {
    return gibKopf() == null ? -1 : kopf.höhe;
  }

  /**
   * {@inheritDoc}
   */
  public AVLBaumKnoten gibKopf() {
    return kopf;
  }

  /**
   * Gib den Balancefaktor des gegebenen Knotens.
   *
   * @param knoten Der Knoten, dessen Balancefaktor ausgegeben werden soll.
   *
   * @return Der Balancefaktor des Knotens.
   */
  public int gibBalance(AVLBaumKnoten knoten) {
    return (knoten == null) ? 0 : gibHöhe(knoten.rechts) - gibHöhe(knoten.links);
  }

  /**
   * Gib den Balancefaktor des gegebenen Knotens als Text (String). Positive
   * Balancefaktoren haben ein Plus als Präfix.
   *
   * @param knoten Der Knoten, dessen Balancefaktor ausgegeben werden soll.
   *
   * @return Der Balancefaktor des Knotens als Text.
   */
  public String gibBalanceText(AVLBaumKnoten knoten) {
    int balance = gibBalance(knoten);
    if (balance > 0) {
      return "+" + balance;
    } else {
      return "" + balance;
    }
  }

  public AVLBaumKnoten finde(Comparable schlüssel) {
    AVLBaumKnoten aktuellerKnoten = kopf;
    while (aktuellerKnoten != null) {
      if (aktuellerKnoten.schlüssel == schlüssel) {
        break;
      }
      aktuellerKnoten = aktuellerKnoten.vergleiche(schlüssel) < 0 ? aktuellerKnoten.rechts : aktuellerKnoten.links;
    }
    return aktuellerKnoten;
  }

  private AVLBaumKnoten rebalanciere(AVLBaumKnoten knoten) {
    aktualisiereHöhe(knoten);
    int balance = gibBalance(knoten);
    if (balance > 1) {
      if (gibHöhe(knoten.rechts.rechts) > gibHöhe(knoten.rechts.links)) {
        reporter.berichteBaum(this, "Nach der Linksrotation", 1);
        knoten = rotiereLinks(knoten);
      } else {
        reporter.berichteBaum(this, "Nach der Rechtsrotation", 1);
        knoten.rechts = rotiereRechts(knoten.rechts);
        reporter.berichteBaum(this, "Nach der Linksrotation", 1);
        knoten = rotiereLinks(knoten);
      }
    } else if (balance < -1) {
      if (gibHöhe(knoten.links.links) > gibHöhe(knoten.links.rechts)) {
        reporter.berichteBaum(this, "Nach der Rechtsrotation", 1);
        knoten = rotiereRechts(knoten);
      } else {
        reporter.berichteBaum(this, "Nach der Linksrotation", 1);
        knoten.links = rotiereLinks(knoten.links);
        reporter.berichteBaum(this, "Nach der Rechtsrotation", 1);
        knoten = rotiereRechts(knoten);
      }
    }
    return knoten;
  }

  /**
   * Führe eine Rechtsrotation durch.
   *
   * @param knoten Knoten mit einem Balancefaktor von -2.
   *
   * @return Der linke Kindknoten des gegebenen Eingangsknoten mit dem
   *         Balancefaktor -1.
   */
  private AVLBaumKnoten rotiereRechts(AVLBaumKnoten knoten) {
    // Linker Knoten mit Balancefaktor -1
    AVLBaumKnoten links = knoten.gibLinks();
    AVLBaumKnoten rechtsVonLinks = links.gibRechts();
    links.rechts = knoten;
    knoten.links = rechtsVonLinks;
    aktualisiereHöhe(knoten);
    aktualisiereHöhe(links);
    return links;
  }

  /**
   * Führe eine Linksrotation durch.
   *
   * @param knoten Knoten mit einem Balancefaktor von +2.
   *
   * @return Der rechte Kindknoten des gegebenen Eingangsknoten mit dem
   *         Balancefaktor von +1.
   */
  private AVLBaumKnoten rotiereLinks(AVLBaumKnoten knoten) {
    // Rechter Knoten mit Balancefaktor +1
    AVLBaumKnoten rechts = knoten.gibRechts();
    AVLBaumKnoten linksVonRechts = rechts.gibLinks();
    rechts.links = knoten;
    knoten.rechts = linksVonRechts;
    aktualisiereHöhe(knoten);
    aktualisiereHöhe(rechts);
    return rechts;
  }

  private void aktualisiereHöhe(AVLBaumKnoten knoten) {
    knoten.höhe = 1 + Math.max(gibHöhe(knoten.links), gibHöhe(knoten.rechts));
  }

  private AVLBaumKnoten fügeEin(AVLBaumKnoten knoten, Comparable schlüssel) {
    if (knoten == null) {
      return new AVLBaumKnoten(schlüssel);
    } else if (knoten.vergleiche(schlüssel) > 0) {
      knoten.links = fügeEin(knoten.links, schlüssel);
    } else if (knoten.vergleiche(schlüssel) < 0) {
      knoten.rechts = fügeEin(knoten.rechts, schlüssel);
    } else {
      throw new RuntimeException("duplicate Key!");
    }
    return rebalanciere(knoten);
  }

  /**
   * {@inheritDoc}
   */
  public boolean fügeEin(Comparable schlüssel) {
    reporter.berichteÜberschrift("Nach Einfügen von „" + schlüssel + "“", 0);

    kopf = fügeEin(kopf, schlüssel);
    reporter.berichteBaum(this, 0);
    return true;
  }

  /**
   * Gib das äußerste Kind eines Knoten.
   *
   * @param knoten   Der aktuelle Knoten.
   * @param richtung „links“: der linkesten Knoten des (Teil-)Baums oder „rechts“:
   *                 der rechtesten Knoten des linkesten Knoten des (Teil-)Baums.
   *
   * @return Das äußerste Kind eines Knoten.
   */
  private AVLBaumKnoten gibÄußerstesKind(AVLBaumKnoten knoten, String richtung) {
    AVLBaumKnoten aktuellerKnoten = knoten;
    if (richtung.equals("links")) {
      while (aktuellerKnoten.links != null) {
        aktuellerKnoten = aktuellerKnoten.links;
      }
    } else {
      while (aktuellerKnoten.rechts != null) {
        aktuellerKnoten = aktuellerKnoten.rechts;
      }
    }
    return aktuellerKnoten;
  }

  /**
   * Entferne einen Knoten.
   *
   * @param knoten    Der aktuelle Knoten.
   * @param schlüssel Der Schlüssel, der gelöscht werden soll.
   * @param neuerKopf „links“: rechtesten Knoten des linken Kindbaums oder
   *                  „rechts“: den linkesten Knoten des rechten Kindbaums.
   *
   * @return Den aktuellen Kopf-Knoten des Baums.
   */
  private AVLBaumKnoten entferne(AVLBaumKnoten knoten, Comparable schlüssel, String neuerKopf) {
    if (knoten == null) {
      return knoten;
    } else if (knoten.vergleiche(schlüssel) > 0) {
      knoten.links = entferne(knoten.links, schlüssel, neuerKopf);
    } else if (knoten.vergleiche(schlüssel) < 0) {
      knoten.rechts = entferne(knoten.rechts, schlüssel, neuerKopf);
    } else {
      if (knoten.links == null || knoten.rechts == null) {
        knoten = (knoten.links == null) ? knoten.rechts : knoten.links;
        gelöscht = true;
      } else if (neuerKopf.equals("rechts")) {
        AVLBaumKnoten ganzLinkesKind = gibÄußerstesKind(knoten.rechts, "links");
        knoten.schlüssel = ganzLinkesKind.schlüssel;
        knoten.rechts = entferne(knoten.rechts, (Comparable) knoten.schlüssel, "rechts");
      } else {
        AVLBaumKnoten ganzRechtesKind = gibÄußerstesKind(knoten.links, "rechts");
        knoten.schlüssel = ganzRechtesKind.schlüssel;
        knoten.links = entferne(knoten.links, (Comparable) knoten.schlüssel, "links");
      }
    }
    if (knoten != null) {
      knoten = rebalanciere(knoten);
    }
    return knoten;
  }

  /**
   * {@inheritDoc}
   */
  public boolean entferne(Comparable schlüssel) {
    reporter.berichteÜberschrift("Nach Löschen von „" + schlüssel + "“", 0);
    kopf = entferne(kopf, schlüssel, "rechts");
    // Wieder auf falsch setzten, damit beim nächsten Löschvorgang der
    // Wert wieder von neuem gesetzt werden muss.
    boolean ausgabe = gelöscht;
    gelöscht = false;
    reporter.berichteBaum(this, 0);
    return ausgabe;
  }

  /**
   *
   * @param schlüssel Der Schlüssel, der gelöscht werden soll.
   * @param neuerKopf „links“: rechtesten Knoten des linken Kindbaums oder
   *                  „rechts“: den linkesten Knoten des rechten Kindbaums.
   */
  public void entferne(Comparable schlüssel, String neuerKopf) {
    kopf = entferne(kopf, schlüssel, neuerKopf);
  }

}
