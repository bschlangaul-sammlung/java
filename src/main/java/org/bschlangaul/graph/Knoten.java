package org.bschlangaul.graph;

import org.bschlangaul.helfer.Farbe;

/**
 * Klasse für die Darstellungen eines Graphen mittels Adjazenzmatrix.
 *
 * nach dem Schulbuch Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class Knoten {
  private String name;

  /**
   * Konstruktor für Objekte der Klasse Knoten
   *
   * @param name Der Name des Knotens.
   */
  public Knoten(String name) {
    this.name = name;
  }

  /**
   * Gibt den Name des Knotenobjekts zurück
   *
   * @return Name
   */
  public String gibName() {
    return name;
  }

  /**
   * Gibt den Name des Knotenobjekts formatiert zurück Der Name wird
   * auf die angegebene Länge abgeschnitten bzw. mit Blanks aufgefüllt
   *
   * @param breite Anzahl der Zeichen auf die der Name formatiert wird.
   *               Maximal 15 Zeichen.
   * @return formatierter Name
   *
   */
  public String gibNameFormatiert(int breite) {
    return Farbe.rot(name) + "               ".substring(0, breite - name.length());
  }

}
