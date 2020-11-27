package org.bschlangaul.baum.bbaum;

import java.util.Vector;

/**
 * Saake Seite 388-389
 */
@SuppressWarnings("rawtypes")
public class BBaum {

  class Knoten {
    /* Knotentypen */
    public static final int LEAF_PAGE = 0;
    public static final int NODE_PAGE = 1;
    /* Suchergebnisse */
    public static final int KEY_FOUND = -1;
    public static final int KEY_NOT_FOUND = -2;

    /**
     * Elternknoten
     */
    Knoten eltern = null;

    /**
     * Feld für Schlüssel.
     */
    Vector<Comparable> schlüssel;

    /**
     * Feld für Verweise.
     */
    Vector<Knoten> zeiger;
    int ntype; // Knotentyp

    public Knoten(int nt) {
      ntype = nt;
      schlüssel = new Vector<Comparable>();
      // pointers nur für innere Knoten
      zeiger = (ntype == NODE_PAGE ? new Vector<Knoten>() : null);
    }

    public Comparable gibSchlüssel(int index) {
      return schlüssel.get(index);
    }

    public Vector gibZeiger() {
      return zeiger;
    }

    public Knoten gibZeigerDurchIndex(int index) {
      return zeiger.get(index);
    }

    public Knoten gibEltern() {
      return eltern;
    }

    public int gibAnzahlSchlüssel() {
      return schlüssel.size();
    }

    public void setNodeType(int nt) {
      ntype = nt;
      if (zeiger == null && ntype == NODE_PAGE)
        zeiger = new Vector<Knoten>();
    }

    /**
     * Saake Seite 391
     *
     * @param c
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public int findKeyInNode(Comparable c, Comparable[] key) {
      for (int i = 0; i < schlüssel.size(); i++) {
        int res = schlüssel.get(i).compareTo(c);
        if (res == 0) {
          // Schlüssel gefunden
          key[0] = schlüssel.get(i);
          return KEY_FOUND;
        } else if (res > 0)
          return ntype == NODE_PAGE ? i : KEY_NOT_FOUND;
      }
      // Wenn es ein innerer Knoten ist, geben wir den letzten Verweis
      // zurück, im Fall eines Blattes KEY NOT FOUND.
      return (ntype == NODE_PAGE ? schlüssel.size() : KEY_NOT_FOUND);
    }

    /**
     * Saake Seite 395-396
     *
     * @param obj
     * @param lnode
     * @param rnode
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean fügeInKnotenEin(Comparable obj, Knoten lnode, Knoten rnode) {
      boolean done = false;
      // Position für Schlüssel suchen
      for (int i = 0; i < schlüssel.size(); i++) {
        int res = schlüssel.get(i).compareTo(obj);
        if (res == 0) {
          // Schlüssel existiert schon -> ignorieren
          done = true;
          break;
        } else if (res > 0) {
          // Stelle gefunden -> einfügen
          schlüssel.insertElementAt(obj, i);
          if (rnode != null) {
            zeiger.insertElementAt(rnode, i + 1);
            rnode.eltern = this;
          }
          done = true;
          break;
        }
      }
      if (!done) {
        // Schlüssel muss am Ende eingefügt werden
        schlüssel.add(obj);
        if (lnode != null && zeiger.isEmpty()) {
          zeiger.add(lnode);
          lnode.eltern = this;
        }
        if (rnode != null) {
          zeiger.add(rnode);
          rnode.eltern = this;
        }
      }
      // Knoten zu groß
      return schlüssel.size() > grad * 2;
    }

    /**
     * Seite 396
     *
     * @return
     */
    public Knoten split() {
      int pos = gibAnzahlSchlüssel() / 2;
      // Geschwisterknoten erzeugen
      Knoten sibling = new Knoten(ntype);
      for (int i = pos + 1; i < gibAnzahlSchlüssel(); i++) {
        // die obere Hälfte der Schlüssel und Verweise kopieren
        sibling.schlüssel.add(this.gibSchlüssel(i));
        if (ntype == Knoten.NODE_PAGE)
          sibling.zeiger.add(this.gibZeigerDurchIndex(i));
      }
      // es gibt einen Verweis mehr als Schlüssel
      if (ntype == Knoten.NODE_PAGE)

        sibling.zeiger.add(this.gibZeigerDurchIndex(gibAnzahlSchlüssel()));
      // und anschließend im Originalknoten löschen
      for (int i = gibAnzahlSchlüssel() - 1; i >= pos; i--) {
        schlüssel.remove(pos);
        if (ntype == Knoten.NODE_PAGE)
          zeiger.remove(pos + 1);
      }
      return sibling;
    }
  }

  private Knoten wurzel = null; // Wurzel
  private int grad; // Ordnung

  public BBaum(int grad) {
    this.grad = grad;
    wurzel = new Knoten(Knoten.LEAF_PAGE);
  }

  public Knoten gibWurzel() {
    return wurzel;
  }

  /**
   * Saake Seite 391
   */
  public Comparable find(Comparable k) {
    Knoten node = wurzel; // Startknoten
    boolean finished = false;
    Comparable[] res = { null };
    do {
      // Suche Schlüssel im aktuellen Knoten
      int idx = node.findKeyInNode(k, res);
      if (idx == Knoten.KEY_FOUND || idx == Knoten.KEY_NOT_FOUND)
        // Schlüssel gefunden oder auf einem
        // Blattknoten nicht gefunden -> fertig
        finished = true;
      else
        // anderenfalls Verweis verfolgen
        node = node.gibZeigerDurchIndex(idx);
    } while (!finished);
    return res[0];
  }

  /**
   * Saake Seite 393-394
   *
   * @param schlüssel
   */
  public void fügeEin(Comparable schlüssel) {
    Knoten lsibling = null, rsibling = null;
    // Suche Blattknoten, der den Schlüssel aufnimmt
    Knoten node = findeBlattKnoten(schlüssel);
    // Schlüssel einfügen
    while (node.fügeInKnotenEin(schlüssel, lsibling, rsibling)) {
      // Split erforderlich
      int pos = node.gibAnzahlSchlüssel() / 2;
      schlüssel = node.gibSchlüssel(pos);
      Knoten parent = node.gibEltern();
      if (parent == null)
        // ein neuer Elternknoten muss angelegt werden
        parent = new Knoten(Knoten.NODE_PAGE);
      // Split durchführen
      lsibling = node;
      rsibling = node.split();
      // Wurzel anpassen
      if (wurzel == node)
        wurzel = parent;
      // der aktuelle Knoten ist jetzt der Elternknoten
      node = parent;
      // und der muss ein innerer Knoten sein
      node.setNodeType(Knoten.NODE_PAGE);
    }
  }

  /**
   *
   * Seite 395
   */
  private Knoten findeBlattKnoten(Comparable c) {
    Knoten node = wurzel;
    Comparable[] key = { null }; // wird eigentlich nicht benötigt
    // den Baum von der Wurzel aus nach unten durchlaufen,
    // bis ein Blattknoten gefunden wurde
    while (node.ntype != Knoten.LEAF_PAGE) {
      // Verweis verfolgen
      node = node.gibZeigerDurchIndex(node.findKeyInNode(c, key));
    }
    return node;
  }
}
