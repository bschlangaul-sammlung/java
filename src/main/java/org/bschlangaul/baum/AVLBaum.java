package org.bschlangaul.baum;

/**
 * https://www.baeldung.com/java-avl-trees
 * https://github.com/eugenp/tutorials/blob/master/data-structures/src/main/java/com/baeldung/avltree/AVLTree.java
 */
@SuppressWarnings({ "rawtypes" })
public class AVLBaum extends Baum {

  private AVLBaumKnoten kopf;

  private int gibHöhe(AVLBaumKnoten knoten) {
    return knoten == null ? -1 : knoten.höhe;
  }

  public int gibHöhe() {
    return kopf == null ? -1 : kopf.höhe;
  }

  public AVLBaumKnoten gibKopf() {
    return kopf;
  }

  public int gibBalance(AVLBaumKnoten knoten) {
    return (knoten == null) ? 0 : gibHöhe(knoten.rechts) - gibHöhe(knoten.links);
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
        reporter.berichteBaum(this, "Linksrotation", 1);
        knoten = rotiereLinks(knoten);
      } else {
        reporter.berichteBaum(this, "Rechtsrotation", 1);
        knoten.rechts = rotiereRechts(knoten.rechts);
        reporter.berichteBaum(this, "Linksrotation", 1);
        knoten = rotiereLinks(knoten);
      }
    } else if (balance < -1) {
      if (gibHöhe(knoten.links.links) > gibHöhe(knoten.links.rechts)) {
        reporter.berichteBaum(this, "Rechtsrotation", 1);
        knoten = rotiereRechts(knoten);
      } else {
        reporter.berichteBaum(this, "Linksrotation", 1);
        knoten.links = rotiereLinks(knoten.links);
        reporter.berichteBaum(this, "Rechtsrotation", 1);
        knoten = rotiereRechts(knoten);
      }
    }
    return knoten;
  }

  private AVLBaumKnoten rotiereRechts(AVLBaumKnoten y) {
    AVLBaumKnoten x = y.links;
    AVLBaumKnoten z = x.rechts;
    x.rechts = y;
    y.links = z;
    aktualisiereHöhe(y);
    aktualisiereHöhe(x);
    return x;
  }

  private AVLBaumKnoten rotiereLinks(AVLBaumKnoten y) {
    AVLBaumKnoten x = y.rechts;
    AVLBaumKnoten z = x.links;
    x.links = y;
    y.rechts = z;
    aktualisiereHöhe(y);
    aktualisiereHöhe(x);
    return x;
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

  public boolean fügeEin(Comparable schlüssel) {
    reporter.berichteÜberschrift("Einfügen von „" + schlüssel + "“", 0);

    kopf = fügeEin(kopf, schlüssel);
    reporter.berichteBaum(this, 0);
    return true;
  }

  /**
   *
   * @param knoten   Der aktuelle Knoten.
   * @param richtung „links“: der linkesten Knoten des (Teil-)Baums oder „rechts“:
   *                 der rechtesten Knoten des linkesten Knoten des (Teil-)Baums.
   * @return
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
   *
   * @param knoten    Der aktuelle Knoten.
   * @param schlüssel Der Schlüssel, der gelöscht werden soll.
   * @param neuerKopf „links“: rechtesten Knoten des linken Kindbaums oder
   *                  „rechts“: den linkesten Knoten des rechten Kindbaums.
   * @return
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
   * Lösche einen Schlüssel aus dem AVL-Baum. Der zu lösche Knoten wird
   * standardmäßig mit dem kleinsten Wert des rechten Teilbaums ersetzt.
   *
   * @param schlüssel Der Schlüssel, der gelöscht werden soll.
   */
  public void entferne(Comparable schlüssel) {
    kopf = entferne(kopf, schlüssel, "rechts");
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
