package org.bschlangaul.graph.einfaches_format;

import org.bschlangaul.antlr.GraphBaseListener;
import org.bschlangaul.antlr.GraphParser;

class AntlrListener extends GraphBaseListener {

  private GraphenFormat graph = new GraphenFormat();

  @Override
  public void enterKante(GraphParser.KanteContext ctx) {
    boolean gerichtet = ctx.gerichtet() != null ? true : false;
    double gewicht = ctx.gewicht() != null ? Double.parseDouble(ctx.gewicht().getText()) : 1;
    graph.fügeKanteEin(ctx.von().getText(), ctx.nach().getText(), gewicht, gerichtet);
  }

  @Override
  public void enterKnoten(GraphParser.KnotenContext ctx) {
    graph.fügeKnotenEin(ctx.name().getText(), ctx.x().getText(), ctx.y().getText());
  }

  public GraphenFormat gibGraph() {
    return graph;
  }

}
