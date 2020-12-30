package org.bschlangaul.graph.einfaches_format;

import org.bschlangaul.antlr.GraphBaseListener;
import org.bschlangaul.antlr.GraphParser;

class AntlrListener extends GraphBaseListener {

  private GraphenFormat graph = new GraphenFormat();

  @Override
  public void enterKante(GraphParser.KanteContext ctx) {
    boolean gerichtet = ctx.gerichtet() != null ? true : false;
    double gewicht = ctx.gewicht() != null ? Double.parseDouble(ctx.gewicht().getText()) : 1;
    graph.fügeKanteEin(formatiereKnotenName(ctx.von().getText()), formatiereKnotenName(ctx.nach().getText()), gewicht,
        gerichtet);
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
