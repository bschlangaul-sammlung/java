package org.bschlangaul.graph.einfaches_format;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.bschlangaul.graph.EinfachesGraphenFormat;
import org.bschlangaul.antlr.GraphLexer;
import org.bschlangaul.antlr.GraphParser;
import org.bschlangaul.antlr.graph.GraphListener;
import org.bschlangaul.antlr.graph.model.GraphKante;
import org.bschlangaul.antlr.graph.model.GraphKnoten;

public class GraphLeser {
  public static EinfachesGraphenFormat lese(String inhalt) throws Exception {
    GraphLexer serverGraphLexer = new GraphLexer(CharStreams.fromString(inhalt));
    CommonTokenStream tokens = new CommonTokenStream(serverGraphLexer);
    GraphParser graphParser = new GraphParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    GraphListener graph = new GraphListener();
    walker.walk(graph, graphParser.graph());

    EinfachesGraphenFormat einfaches = new EinfachesGraphenFormat();
    for (GraphKnoten knoten : graph.gibKnoten()) {
      einfaches.fügeKnotenEin(knoten.name, knoten.x, knoten.y);
    }
    for (GraphKante kante : graph.gibKanten()) {
      einfaches.fügeKanteEin(kante.von, kante.nach, kante.gewicht, kante.gerichtet);
    }
    return einfaches;
  }
}
