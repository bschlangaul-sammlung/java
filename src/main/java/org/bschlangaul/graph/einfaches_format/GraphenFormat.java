package org.bschlangaul.graph.einfaches_format;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.bschlangaul.antlr.GraphBaseListener;
import org.bschlangaul.antlr.GraphParser;

import org.bschlangaul.antlr.GraphLexer;
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

class AntlrListener extends GraphBaseListener {
  private GraphenFormat graph = new GraphenFormat();

  public AntlrListener(GraphenFormat graph) {
    this.graph = graph;
  }

  @Override
  public void enterKante(GraphParser.KanteContext ctx) {
    boolean gerichtet = ctx.gerichtet() != null ? true : false;
    double gewicht = ctx.gewicht() != null ? Double.parseDouble(ctx.gewicht().getText()) : 1;
    boolean markiert = ctx.markiert() != null ? true : false;
    graph.fügeKanteEin(formatiereKnotenName(ctx.von().getText()), formatiereKnotenName(ctx.nach().getText()), gewicht,
        gerichtet, markiert);
  }

  @Override
  public void enterKnoten(GraphParser.KnotenContext ctx) {
    String name = formatiereKnotenName(ctx.name().getText());
    double x = 0d;
    double y = 0d;
    boolean markiert = false;
    if (ctx.x() != null && ctx.y() != null) {
      x = Double.parseDouble(ctx.x().getText());
      y = Double.parseDouble(ctx.y().getText());
    }
    if (ctx.markiert() != null)
      markiert = true;
    graph.fügeKnotenEin(name, x, y, markiert);
  }

  private String formatiereKnotenName(String eingabe) {
    return eingabe.replaceAll("(^['\"]|['\"]$)", "").replace("\\\"", "\"").replace("\\\'", "\'");
  }

  public GraphenFormat gibGraph() {
    return graph;
  }

}

/**
 * Ein sehr einfaches Dateiformat, um einen Graph zu spezifizieren.
 *
 * <p>
 * Eigenschaften:
 * <ul>
 * <li>Es werden nur die Kanten angegeben
 * <li>Knotennamen dürfen keine Leerzeichen haben
 * <li>Die Richtung wird durch {@code --} für ungerichtete Kanten und durch {@code ->} für
 * gerichtete Kanten angegeben.
 * </ul>
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

  Map<String, GraphenFormatKnoten> knoten;
  Set<GraphenFormatKante> kanten;

  public GraphenFormat() {
    knoten = new LinkedHashMap<String, GraphenFormatKnoten>();
    kanten = new HashSet<GraphenFormatKante>();
  }

  public GraphenFormat(String inhalt) {
    this();
    leseTextFormat(inhalt);
  }

  public GraphenFormat(String... formatSegmente) {
    this();
    String separator = ";\n";
    leseTextFormat(String.join(separator, formatSegmente) + separator);
  }

  private void leseTextFormat(String inhalt) {
    GraphLexer serverGraphLexer = new GraphLexer(CharStreams.fromString(inhalt));
    CommonTokenStream tokens = new CommonTokenStream(serverGraphLexer);
    GraphParser graphParser = new GraphParser(tokens);

    graphParser.removeErrorListeners();
    graphParser.addErrorListener(new FehlerLauscher());
    ParseTreeWalker walker = new ParseTreeWalker();
    AntlrListener antlrListener = new AntlrListener(this);
    walker.walk(antlrListener, graphParser.graph());
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

  public void fügeKnotenEin(String name, double x, double y) {
    fügeKnotenEin(name, x, y, false);
  }

  public void fügeKnotenEin(String name, String x, String y) {
    fügeKnotenEin(name, Double.parseDouble(x), Double.parseDouble(y));
  }

  public void fügeKnotenEin(String name, double x, double y, boolean markiert) {
    if (knoten.get(name) == null) {
      knoten.put(name, new GraphenFormatKnoten(name, x, y, markiert));
    } else {
      GraphenFormatKnoten k = knoten.get(name);
      k.x = x;
      k.y = y;
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
    fügeKanteEin(von, nach, gewicht, gerichtet, false);
  }

  public void fügeKanteEin(String von, String nach, double gewicht, boolean gerichtet, boolean markiert) {
    fügeKnotenEin(von);
    fügeKnotenEin(nach);
    kanten.add(new GraphenFormatKante(von, nach, gewicht, gerichtet, markiert));
  }

  public int gibAnzahlKnoten() {
    return knoten.size();
  }

  public int gibAnzahlKanten() {
    return kanten.size();
  }

  public GraphenFormatKnoten[] gibKnoten() {
    GraphenFormatKnoten[] ausgabe = knoten.values().toArray(new GraphenFormatKnoten[0]);
    // nicht sortieren: In manchen Aufgaben sind die Knotennamen nicht alphabetisch angegeben.
    // Arrays.sort(ausgabe);
    return ausgabe;
  }

  public GraphenFormatKnoten gibKnoten(String knotenName) {
    return knoten.get(knotenName);
  }

  public String[] gibKnotenNamen() {
    String[] ausgabe = new String[knoten.size()];
    int zähler = 0;
    for (Map.Entry<String, GraphenFormatKnoten> entry : knoten.entrySet()) {
      GraphenFormatKnoten k = entry.getValue();
      ausgabe[zähler] = k.name;
      zähler++;
    }
    // nicht sortieren: In manchen Aufgaben sind die Knotennamen nicht alphabetisch angegeben.
    // Arrays.sort(ausgabe);
    return ausgabe;
  }

  public GraphenFormatKante[] gibKanten() {
    GraphenFormatKante[] ausgabe = {};
    ausgabe = kanten.toArray(ausgabe);
    // Kann sortiert werden
    Arrays.sort(ausgabe);
    return ausgabe;
  }

  public GraphenFormatKante gibKante(String von, String nach) {
    GraphenFormatKante ausgabe = null;
    for (GraphenFormatKante kante : kanten) {
      if (kante.von.equals(von) && kante.nach.equals(nach))
        ausgabe = kante;
    }
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
