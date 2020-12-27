package org.bschlangaul.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.bschlangaul.graph.EinfachesGraphenFormat;

import org.bschlangaul.antlr.graph.GraphListener;
import org.bschlangaul.antlr.graph.model.GraphKante;

public class GraphLeser {
  public static EinfachesGraphenFormat lese(String inhalt) throws Exception {
    GraphLexer serverGraphLexer = new GraphLexer(CharStreams.fromString(inhalt));
    CommonTokenStream tokens = new CommonTokenStream(serverGraphLexer);
    GraphParser graphParser = new GraphParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    GraphListener graph = new GraphListener();
    walker.walk(graph, graphParser.kanten());

    EinfachesGraphenFormat einfaches = new EinfachesGraphenFormat();
    for (GraphKante kante : graph.gibKanten()) {
      einfaches.fügeUngerichteteKanteEin(kante.von, kante.nach, "1");
    }
    return einfaches;
  }
}
