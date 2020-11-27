package org.bschlangaul.baum.einfach;

public class Baum {

  public Knoten wurzel;
  public int anzahl;

  public Baum(int anzahl) {
    this.anzahl = anzahl;

    if (anzahl == 0) {
      return;
    } else {
      wurzel = new Knoten();
      // wurzel.wert = anzahl;
      Baum linkBaum = new Baum(anzahl / 2);
       // Zeiger auf linken Teilbaum
      wurzel.links = linkBaum.wurzel;
      Baum rechtBaum = new Baum(anzahl - 1 - anzahl / 2);
      // Zeiger auf rechten Teilbaum
      wurzel.rechts = rechtBaum.wurzel;
    }
  }

}
