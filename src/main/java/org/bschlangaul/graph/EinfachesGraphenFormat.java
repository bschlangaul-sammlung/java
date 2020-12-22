package org.bschlangaul.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
 * V: A 3 5
 * v: Knoten 1 -2.3
 *
 * A - Knoten
 * B - C
 * A->D:3
 * }
 * </pre>
 */
public class EinfachesGraphenFormat {

  class Knoten implements Comparable<Knoten> {
    public String name;
    public double x;
    public double y;

    public Knoten(String name) {
      this.name = name;
    }

    public Knoten(String name, double x, double y) {
      this.name = name;
      this.x = x;
      this.y = y;
    }

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

    public String toString() {
      return String.format("Knoten (name: %s, x: %s, y: %s)", name, x, y);
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

    public String toString () {
      return String.format("Kante (von: %s, nach: %s, gewicht: %s, gerichtet: %b)", von, nach, gewicht, gerichtet);
    }
  }

  String zeilenTrenner = "[\\r\\n;]+";

  String leerzeichen = "\\s*";

  String zahl = "-?\\d+(\\.\\d+)?";

  String name = "[\\wäöüÄÜÖß]+";

  String knotenRegexString = "(v|V):" + "\\s+" + macheRegexGruppe("name", name) + "\\s+" + macheRegexGruppe("x", zahl)
      + "\\s+" + macheRegexGruppe("y", zahl);

  Pattern knotenRegex = Pattern.compile(knotenRegexString);

  String kantenRegexString = macheRegexGruppe("von", "\\w+") + leerzeichen + macheRegexGruppe("richtung", "[->]")
      + leerzeichen + macheRegexGruppe("nach", "\\w+")
      + String.format("((\\s*:\\s*|\\s+)%s)?", macheRegexGruppe("gewicht", zahl));

  Pattern kantenRegex = Pattern.compile(kantenRegexString);

  HashMap<String, Knoten> knoten;
  HashSet<Kante> kanten;

  public EinfachesGraphenFormat() {
    knoten = new HashMap<String, Knoten>();
    kanten = new HashSet<Kante>();
  }

  public EinfachesGraphenFormat(String eingang) {
    this();
    String[] zeilen = eingang.split(zeilenTrenner);
    for (String zeile : zeilen) {
      verarbeiteZeile(zeile);
    }
  }

  private static String macheRegexGruppe(String gruppenName, String regex) {
    return String.format("(?<%s>%s)", gruppenName, regex);
  }

  private void verarbeiteZeile(String zeile) {
    zeile = zeile.trim();
    Matcher kantenErgebnis = kantenRegex.matcher(zeile);
    Matcher knotenErgebnis = knotenRegex.matcher(zeile);

    if (knotenErgebnis.find()) {
      fügeKnotenEin(knotenErgebnis.group("name"), knotenErgebnis.group("x"), knotenErgebnis.group("y"));
    } else if (kantenErgebnis.find()) {
      String von = kantenErgebnis.group("von");
      String nach = kantenErgebnis.group("nach");

      fügeKnotenEin(von);
      fügeKnotenEin(nach);

      double gewicht;
      if (kantenErgebnis.group("gewicht") == null) {
        gewicht = 1;
      } else {
        gewicht = Double.parseDouble(kantenErgebnis.group("gewicht"));
      }

      if (kantenErgebnis.group("richtung").equals("-")) {
        fügeUngerichteteKanteEin(von, nach, gewicht);
      } else {
        fügeGerichteteKanteEin(von, nach, gewicht);
      }
    } else {
      System.out.println(String.format("Fehler: %s", zeile));
    }
  }

  public int gibAnzahlKnoten() {
    return knoten.size();
  }

  public int gibAnzahlKanten() {
    return kanten.size();
  }

  public Knoten[] gibKnoten() {
    Knoten[] ausgabe = knoten.values().toArray(new Knoten[0]);
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public String[] gibKnotenNamen() {
    String[] ausgabe = new String[knoten.size()];
    int zähler = 0;
    for (Map.Entry<String, Knoten> entry : knoten.entrySet()) {
      Knoten k = entry.getValue();
      ausgabe[zähler] = k.name;
      zähler++;
    }
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public Kante[] gibKanten() {
    Kante[] ausgabe = {};
    ausgabe = kanten.toArray(ausgabe);
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public void fügeKnotenEin(String name) {
    if (knoten.get(name) == null) {
      knoten.put(name, new Knoten(name));
    }
  }

  public void fügeKnotenEin(String name, String x, String y) {
    double xDouble = Double.parseDouble(x);
    double yDouble = Double.parseDouble(y);
    if (knoten.get(name) == null) {
      knoten.put(name, new Knoten(name, xDouble, yDouble));
    } else {
      Knoten k = knoten.get(name);
      k.x = xDouble;
      k.y = yDouble;
    }
  }

  public void fügeUngerichteteKanteEin(String von, String nach, String gewicht) {
    fügeUngerichteteKanteEin(von, nach, Double.parseDouble(gewicht));
  }

  public void fügeUngerichteteKanteEin(String von, String nach, double gewicht) {
    kanten.add(new Kante(von, nach, gewicht, false));
  }

  public void fügeGerichteteKanteEin(String von, String nach, double gewicht) {
    kanten.add(new Kante(von, nach, gewicht, true));
  }

  public void gibAus() {
    System.out.println(String.format("Anzahl an Knoten: %d", gibAnzahlKnoten()));
    System.out.println(String.format("Anzahl an Kanten: %d", gibAnzahlKanten()));

    for (Knoten knoten : gibKnoten()) {
      System.out.println(knoten);
    }
    for (Kante kante : gibKanten()) {
      System.out.println(kante);
    }
  }

}
