package org.bschlangaul.antlr.graph;

import java.util.ArrayList;
import java.util.List;

import org.bschlangaul.antlr.GraphBaseListener;
import org.bschlangaul.antlr.GraphParser;

import org.bschlangaul.antlr.graph.model.Kante;

public class GraphListener extends GraphBaseListener {

  private List<Kante> kanten = new ArrayList<>();

  @Override
  public void enterKante(GraphParser.KanteContext ctx) {
    kanten.add(new Kante(ctx.von().getText(), ctx.nach().getText()));
  }

  @Override
  public void exitKante(GraphParser.KanteContext ctx) {
  }

  public Kante gibErsteKante() {
    return kanten.get(0);
  }

}
