package org.bschlangaul.graph.einfaches_format;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.bschlangaul.antlr.GraphLexer;
import org.bschlangaul.antlr.GraphParser;
import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.helfer.Tex;

class FehlerLauscher extends BaseErrorListener {
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
      String msg, RecognitionException e) {

    String sourceName = recognizer.getInputStream().getSourceName();
    if (!sourceName.isEmpty()) {
      sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
    }
    System.err.println(sourceName + "line " + line + ":" + charPositionInLine + " " + msg);
  }

}

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
public class GraphenFormat {

  HashMap<String, GraphenFormatKnoten> knoten;
  HashSet<GraphenFormatKante> kanten;

  public GraphenFormat() {
    knoten = new HashMap<String, GraphenFormatKnoten>();
    kanten = new HashSet<GraphenFormatKante>();
  }

  public static GraphenFormat lese(String inhalt) {
    GraphLexer serverGraphLexer = new GraphLexer(CharStreams.fromString(inhalt));
    CommonTokenStream tokens = new CommonTokenStream(serverGraphLexer);
    GraphParser graphParser = new GraphParser(tokens);

    graphParser.removeErrorListeners();
    graphParser.addErrorListener(new FehlerLauscher());
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
      knoten.put(name, new GraphenFormatKnoten(name));
    }
  }

  public void fügeKnotenEin(String name, String x, String y) {
    double xDouble = Double.parseDouble(x);
    double yDouble = Double.parseDouble(y);
    if (knoten.get(name) == null) {
      knoten.put(name, new GraphenFormatKnoten(name, xDouble, yDouble));
    } else {
      GraphenFormatKnoten k = knoten.get(name);
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
    fügeKnotenEin(von);
    fügeKnotenEin(nach);
    kanten.add(new GraphenFormatKante(von, nach, gewicht, gerichtet));
  }

  public int gibAnzahlKnoten() {
    return knoten.size();
  }

  public int gibAnzahlKanten() {
    return kanten.size();
  }

  public GraphenFormatKnoten[] gibKnoten() {
    GraphenFormatKnoten[] ausgabe = knoten.values().toArray(new GraphenFormatKnoten[0]);
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public String[] gibKnotenNamen() {
    String[] ausgabe = new String[knoten.size()];
    int zähler = 0;
    for (Map.Entry<String, GraphenFormatKnoten> entry : knoten.entrySet()) {
      GraphenFormatKnoten k = entry.getValue();
      ausgabe[zähler] = k.name;
      zähler++;
    }
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public GraphenFormatKante[] gibKanten() {
    GraphenFormatKante[] ausgabe = {};
    ausgabe = kanten.toArray(ausgabe);
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public void gibAusFürKommandozeile() {
    System.out.println(String.format("Anzahl an Knoten: %d", gibAnzahlKnoten()));
    System.out.println(String.format("Anzahl an Kanten: %d", gibAnzahlKanten()));

    for (GraphenFormatKnoten knoten : gibKnoten()) {
      System.out.println(knoten);
    }
    for (GraphenFormatKante kante : gibKanten()) {
      System.out.println(kante);
    }
  }

  public String toString() {
    String ausgabe = "";
    for (GraphenFormatKnoten knoten : gibKnoten()) {
      ausgabe += knoten.gibAlsEinfachesFormat();
    }
    for (GraphenFormatKante kante : gibKanten()) {
      ausgabe += kante.gibAlsEinfachesFormat();
    }
    return ausgabe;
  }

  public String gibAlsTexUmgebung() {
    return Tex.umgebung(GraphenFinder.umgebungsName, toString());
  }

}
