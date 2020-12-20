package org.bschlangaul.graph;

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
public class EinfachesGraphFormat {

  Pattern zeilenRegexp = Pattern.compile("(?<von>\\w+)\\s*(?<richtung>[->])\\s*(?<nach>\\w+)(\\s*(?<gewicht>\\d+))?");

  HashSet<String> knoten;
  HashSet<Kante> kanten;

  class Kante {
    public String von;
    public String nach;
    public int gewicht;

    public Kante(String von, String nach, int gewicht) {
      this.von = von;
      this.nach = nach;
      this.gewicht = gewicht;
    }

    @Override
    public int hashCode() {
      String ausgabe = von + ":" + nach;
      return ausgabe.hashCode();
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Kante) {
        Kante kante = (Kante) o;
        if (von.equals(kante.von) && nach.equals(kante.nach))
          return true;
      }
      return false;
    }
  }

  public EinfachesGraphFormat(String eingang) {
    String[] zeilen = eingang.split("[\\r?\\n]+");
    knoten = new HashSet<String>();
    kanten = new HashSet<Kante>();
    for (String zeile : zeilen) {
      verarbeiteZeile(zeile);
    }

    for (Object knotenName : knoten.toArray()) {
      System.out.println(knotenName);
    }

    for (Object kante : kanten.toArray()) {
      Kante k = (Kante) kante;
      System.out.println(k.von + " " + k.nach + " " + k.gewicht);
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

  private void fügeUngerichteteKanteEin(String von, String nach, int gewicht) {
    kanten.add(new Kante(von, nach, gewicht));
    kanten.add(new Kante(nach, von, gewicht));
  }

  private void fügeGerichteteKanteEin(String von, String nach, int gewicht) {
    kanten.add(new Kante(von, nach, gewicht));
  }

  public static void main(String[] args) {
    EinfachesGraphFormat graph = new EinfachesGraphFormat("a - b\na - b\na - b\nb > c\nd>e\n e - f 12");
  }
}
