package org.bschlangaul.baum;

/**
 * https://www.baeldung.com/java-avl-trees
 * https://github.com/eugenp/tutorials/blob/master/data-structures/src/main/java/com/baeldung/avltree/AVLTree.java
 */
@SuppressWarnings({ "rawtypes" })
public class AVLBaum extends Baum {

  private AVLKnoten kopf;

  private int gibHöhe(AVLKnoten knoten) {
    return knoten == null ? -1 : knoten.höhe;
  }

  public int gibHöhe() {
    return kopf == null ? -1 : kopf.höhe;
  }

  public AVLKnoten gibKopf() {
    return kopf;
  }

  public int gibBalance(AVLKnoten knoten) {
    return (knoten == null) ? 0 : gibHöhe(knoten.rechts) - gibHöhe(knoten.links);
  }

  public AVLKnoten finde(Comparable schlüssel) {
    AVLKnoten aktuellerKnoten = kopf;
    while (aktuellerKnoten != null) {
      if (aktuellerKnoten.schlüssel == schlüssel) {
        break;
      }
      aktuellerKnoten = aktuellerKnoten.vergleiche(schlüssel) < 0 ? aktuellerKnoten.rechts : aktuellerKnoten.links;
    }
    return aktuellerKnoten;
  }

  private AVLKnoten rebalanciere(AVLKnoten knoten) {
    aktualisiereHöhe(knoten);
    int balance = gibBalance(knoten);
    if (balance > 1) {
      if (gibHöhe(knoten.rechts.rechts) > gibHöhe(knoten.rechts.links)) {
        knoten = rotiereLinks(knoten);
      } else {
        knoten.rechts = rotiereRechts(knoten.rechts);
        knoten = rotiereLinks(knoten);
      }
    } else if (balance < -1) {
      if (gibHöhe(knoten.links.links) > gibHöhe(knoten.links.rechts)) {
        knoten = rotiereRechts(knoten);
      } else {
        knoten.links = rotiereLinks(knoten.links);
        knoten = rotiereRechts(knoten);
      }
    }
    return knoten;
  }

  private AVLKnoten rotiereRechts(AVLKnoten y) {
    AVLKnoten x = y.links;
    AVLKnoten z = x.rechts;
    x.rechts = y;
    y.links = z;
    aktualisiereHöhe(y);
    aktualisiereHöhe(x);
    return x;
  }

  private AVLKnoten rotiereLinks(AVLKnoten y) {
    AVLKnoten x = y.rechts;
    AVLKnoten z = x.links;
    x.links = y;
    y.rechts = z;
    aktualisiereHöhe(y);
    aktualisiereHöhe(x);
    return x;
  }

  private void aktualisiereHöhe(AVLKnoten knoten) {
    knoten.höhe = 1 + Math.max(gibHöhe(knoten.links), gibHöhe(knoten.rechts));
  }

  private AVLKnoten fügeEin(AVLKnoten knoten, Comparable schlüssel) {
    if (knoten == null) {
      return new AVLKnoten(schlüssel);
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
    kopf = fügeEin(kopf, schlüssel);
    return true;
  }

  /**
   *
   * @param knoten   Der aktuelle Knoten.
   * @param richtung „links“: der linkesten Knoten des (Teil-)Baums oder „rechts“:
   *                 der rechtesten Knoten des linkesten Knoten des (Teil-)Baums.
   * @return
   */
  private AVLKnoten gibÄußerstesKind(AVLKnoten knoten, String richtung) {
    AVLKnoten aktuellerKnoten = knoten;
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
  private AVLKnoten entferne(AVLKnoten knoten, Comparable schlüssel, String neuerKopf) {
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
        AVLKnoten ganzLinkesKind = gibÄußerstesKind(knoten.rechts, "links");
        knoten.schlüssel = ganzLinkesKind.schlüssel;
        knoten.rechts = entferne(knoten.rechts, (Comparable) knoten.schlüssel, "rechts");
      } else {
        AVLKnoten ganzRechtesKind = gibÄußerstesKind(knoten.links, "rechts");
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
