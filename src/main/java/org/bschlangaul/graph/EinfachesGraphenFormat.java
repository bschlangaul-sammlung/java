package org.bschlangaul.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ein sehr einfaches Dateiformat um einen Graph zu spezifizieren.
 *
 * - Es werden nur die Kanten angegeben - Knotennamen dürfen keine Leerzeichen
 * haben - Die Richtung wird durch - für ungerichtete Kanten und durch > für
 * gerichtete Kanten angegeben.
 *
 */
public class EinfachesGraphenFormat {

  Pattern zeilenRegexp = Pattern.compile("(?<von>\\w+)\\s*(?<richtung>[->])\\s*(?<nach>\\w+)(\\s+(?<gewicht>\\d+))?");

  HashSet<String> knoten;
  HashSet<Kante> kanten;

  /**
   * Diese Klasse dient als eine Art Zwischenspeicher für Kanteninformationen.
   */
  class Kante implements Comparable<Kante> {
    public String von;
    public String nach;
    public int gewicht;

    public Kante(String von, String nach, int gewicht) {
      this.von = von;
      this.nach = nach;
      this.gewicht = gewicht;
    }

    /**
     * Diese Methode wird benötigt, um keine doppelten Kanten in dem HashSet kanten
     * zu haben.
     */
    @Override
    public int hashCode() {
      String ausgabe = von + ":" + nach;
      return ausgabe.hashCode();
    }

    /**
     * Diese Methode wird benötigt, um keine doppelten Kanten in dem HashSet kanten
     * zu haben.
     */
    @Override
    public boolean equals(Object o) {
      if (o instanceof Kante) {
        Kante kante = (Kante) o;
        if (von.equals(kante.von) && nach.equals(kante.nach))
          return true;
      }
      return false;
    }

    /**
     * Diese Methode wird benötigt, um die Kanten sortieren zu können.
     *
     * @param object Eine andere einfache Kante, die verglichen werden soll.
     *
     * @return 0, -1, 1
     */
    @Override
    public int compareTo(Kante kante) {
      int ersterVergleich = von.compareTo(kante.von);
      if (ersterVergleich != 0)
        return ersterVergleich;
      return nach.compareTo(kante.nach);
    }
  }

  public EinfachesGraphenFormat(String eingang) {
    String[] zeilen = eingang.split("[\\r?\\n]+");
    knoten = new HashSet<String>();
    kanten = new HashSet<Kante>();
    for (String zeile : zeilen) {
      verarbeiteZeile(zeile);
    }
  }

  private void verarbeiteZeile(String zeile) {
    zeile = zeile.trim();
    Matcher ergebnis = zeilenRegexp.matcher(zeile);
    if (ergebnis.find()) {
      String von = ergebnis.group("von");
      String nach = ergebnis.group("nach");

      knoten.add(von);
      knoten.add(nach);

      int gewicht;
      if (ergebnis.group("gewicht") == null) {
        gewicht = 1;
      } else {
        gewicht = Integer.parseInt(ergebnis.group("gewicht"));
      }

      if (ergebnis.group("richtung").equals("-")) {
        fügeUngerichteteKanteEin(von, nach, gewicht);
      } else {
        fügeGerichteteKanteEin(von, nach, gewicht);
      }
    }
  }

  public int gibAnzahlKnoten() {
    return knoten.size();
  }

  public int gibAnzahlKanten() {
    return kanten.size();
  }

  public String[] gibKnoten() {
    String[] ausgabe = {};
    ausgabe = knoten.toArray(ausgabe);
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public Kante[] gibKanten() {
    Kante[] ausgabe = {};
    ausgabe = kanten.toArray(ausgabe);
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  private void fügeUngerichteteKanteEin(String von, String nach, int gewicht) {
    kanten.add(new Kante(von, nach, gewicht));
    kanten.add(new Kante(nach, von, gewicht));
  }

  private void fügeGerichteteKanteEin(String von, String nach, int gewicht) {
    kanten.add(new Kante(von, nach, gewicht));
  }
}
