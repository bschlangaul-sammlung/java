package org.bschlangaul.graph.einfaches_format;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.bschlangaul.antlr.GraphLexer;
import org.bschlangaul.antlr.GraphParser;

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
 * A: 3 5
 * Knoten: 1 -2.3
 *
 * A -- Knoten
 * B -- C
 * A->D: 3
 * Knoten mit Leerzeichen -> Z: 42
 * }
 * </pre>
 */
public class Graph {

  HashMap<String, Knoten> knoten;
  HashSet<Kante> kanten;

  public Graph() {
    knoten = new HashMap<String, Knoten>();
    kanten = new HashSet<Kante>();
  }

  public static Graph lese(String inhalt) throws Exception {
    GraphLexer serverGraphLexer = new GraphLexer(CharStreams.fromString(inhalt));
    CommonTokenStream tokens = new CommonTokenStream(serverGraphLexer);
    GraphParser graphParser = new GraphParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    AntlrListener antlrListener = new AntlrListener();
    walker.walk(antlrListener, graphParser.graph());
    return antlrListener.gibGraph();
  }

  public static String formatiereZahl(String zahl) {
    return zahl.replaceFirst("\\.0$", "");
  }

  public static String formatiereZahl(double zahl) {
    return formatiereZahl(String.valueOf(zahl));
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
    fügeKanteEin(von, nach, gewicht, false);
  }

  public void fügeUngerichteteKanteEin(String von, String nach, double gewicht) {
    fügeKanteEin(von, nach, gewicht, false);
  }

  public void fügeGerichteteKanteEin(String von, String nach, double gewicht) {
    fügeKanteEin(von, nach, gewicht, true);
  }

  public void fügeKanteEin(String von, String nach, String gewicht, boolean gerichtet) {
    fügeKanteEin(von, nach, Double.parseDouble(gewicht), gerichtet);
  }

  public void fügeKanteEin(String von, String nach, double gewicht, boolean gerichtet) {
    kanten.add(new Kante(von, nach, gewicht, gerichtet));
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

  public void gibAusFürKommandozeile() {
    System.out.println(String.format("Anzahl an Knoten: %d", gibAnzahlKnoten()));
    System.out.println(String.format("Anzahl an Kanten: %d", gibAnzahlKanten()));

    for (Knoten knoten : gibKnoten()) {
      System.out.println(knoten);
    }
    for (Kante kante : gibKanten()) {
      System.out.println(kante);
    }
  }

  public String toString() {
    String ausgabe = "";
    for (Knoten knoten : gibKnoten()) {
      ausgabe += knoten.gibAlsEinfachesFormat();
    }
    for (Kante kante : gibKanten()) {
      ausgabe += kante.gibAlsEinfachesFormat();
    }
    return ausgabe;
  }

}
