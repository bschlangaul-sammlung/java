package org.bschlangaul.antlr.graph.model;

public class GraphKante {

  public String von;

  public String nach;

  public boolean gerichtet;

  public GraphKante(String von, String nach, boolean gerichtet) {
    this.von = von;
    this.nach = nach;
    this.gerichtet = gerichtet;
  }
}
