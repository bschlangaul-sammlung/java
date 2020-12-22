package org.bschlangaul.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ein sehr einfaches Dateiformat um einen Graph zu spezifizieren.
 *
 * <p>
 * <ul>
 * <li>Es werden nur die Kanten angegeben
 * <li>Knotennamen dürfen keine Leerzeichen haben
 * <li>Die Richtung wird durch - für ungerichtete Kanten und durch > für
 * gerichtete Kanten angegeben.
 * </ul>
 * </p>
 *
 * <pre>
 * {@code
 * v: A 3 5
 * v: "Ein Knoten" 1 3
 *
 * A "Ein Knoten"
 * B C
 * A>D:3
 * }
 * </pre>
 */
public class EinfachesGraphenFormat {

  class Knoten implements Comparable<Knoten> {
    public String name;
    public double x;
    public double y;

    /**
     * Diese Methode wird benötigt, um keine doppelten Knoten in dem HashSet knoten
     * zu haben.
     */
    @Override
    public int hashCode() {
      return name.hashCode();
    }

    /**
     * Diese Methode wird benötigt, um keine doppelten Knoten in dem HashSet knoten
     * zu haben.
     */
    @Override
    public boolean equals(Object o) {
      if (o instanceof Knoten) {
        Knoten knoten = (Knoten) o;
        return name.equals(knoten.name);
      }
      return false;
    }

    /**
     * Diese Methode wird benötigt, um die Knoten sortieren zu können.
     *
     * @param knoten Eine andere Knoten, der sortiert werden soll.
     *
     * @return 0, -1, 1
     */
    @Override
    public int compareTo(Knoten knoten) {
      return name.compareTo(knoten.name);
    }
  }

  /**
   * Diese Klasse dient als eine Art Zwischenspeicher für Kanteninformationen.
   */
  class Kante implements Comparable<Kante> {
    public String von;
    public String nach;
    public double gewicht;
    public boolean gerichtet;

    public Kante(String von, String nach, double gewicht, boolean gerichtet) {
      this.von = von;
      this.nach = nach;
      this.gewicht = gewicht;
      this.gerichtet = gerichtet;
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

  String zeilenTrenner = "[\\r\\n;]+";

  String leerzeichen = "\\s*";

  String zahl = "-?\\d+(\\.\\d+)?";

  String kantenRegexString = macheRegexGruppe("von", "\\w+") + leerzeichen + macheRegexGruppe("richtung", "[->]")
      + leerzeichen + macheRegexGruppe("nach", "\\w+") + String.format("((\\s*:\\s*|\\s+)%s)?", macheRegexGruppe("gewicht", zahl));

  Pattern zeilenRegex = Pattern.compile(kantenRegexString);
  HashSet<String> knoten;
  HashSet<Kante> kanten;

  public EinfachesGraphenFormat(String eingang) {
    String[] zeilen = eingang.split(zeilenTrenner);
    knoten = new HashSet<String>();
    kanten = new HashSet<Kante>();
    for (String zeile : zeilen) {
      verarbeiteZeile(zeile);
    }
  }

  private static String macheRegexGruppe(String gruppenName, String regex) {
    return String.format("(?<%s>%s)", gruppenName, regex);
  }

  private void verarbeiteZeile(String zeile) {
    zeile = zeile.trim();
    Matcher ergebnis = zeilenRegex.matcher(zeile);
    if (ergebnis.find()) {
      String von = ergebnis.group("von");
      String nach = ergebnis.group("nach");

      knoten.add(von);
      knoten.add(nach);

      double gewicht;
      if (ergebnis.group("gewicht") == null) {
        gewicht = 1;
      } else {
        gewicht = Double.parseDouble(ergebnis.group("gewicht"));
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

  private void fügeUngerichteteKanteEin(String von, String nach, double gewicht) {
    kanten.add(new Kante(von, nach, gewicht, false));
  }

  private void fügeGerichteteKanteEin(String von, String nach, double gewicht) {
    kanten.add(new Kante(von, nach, gewicht, true));
  }
}
