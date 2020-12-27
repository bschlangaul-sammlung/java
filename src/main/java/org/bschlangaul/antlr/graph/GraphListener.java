package org.bschlangaul.antlr.graph;

import java.util.ArrayList;
import java.util.List;

import org.bschlangaul.antlr.GraphBaseListener;
import org.bschlangaul.antlr.GraphParser;

import org.bschlangaul.antlr.graph.model.GraphKante;

public class GraphListener extends GraphBaseListener {

  private List<GraphKante> kanten = new ArrayList<>();

  @Override
  public void enterKante(GraphParser.KanteContext ctx) {
    kanten.add(new GraphKante(ctx.von().getText(), ctx.nach().getText()));
  }

  @Override
  public void exitKante(GraphParser.KanteContext ctx) {
  }

  public GraphKante[] gibKanten() {
    return kanten.toArray(new GraphKante[0]);
  }

}
