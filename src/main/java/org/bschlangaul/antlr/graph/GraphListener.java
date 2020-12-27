package org.bschlangaul.antlr.graph;

import java.util.ArrayList;
import java.util.List;

import org.bschlangaul.antlr.GraphBaseListener;
import org.bschlangaul.antlr.GraphParser;

import org.bschlangaul.antlr.graph.model.GraphKante;
import org.bschlangaul.antlr.graph.model.GraphKnoten;

public class GraphListener extends GraphBaseListener {

  private List<GraphKante> kanten = new ArrayList<>();
  private List<GraphKnoten> knoten = new ArrayList<>();

  @Override
  public void enterKante(GraphParser.KanteContext ctx) {
    kanten.add(new GraphKante(ctx.von().getText(), ctx.nach().getText()));
  }


  public GraphKante[] gibKanten() {
    return kanten.toArray(new GraphKante[0]);
  }

}
