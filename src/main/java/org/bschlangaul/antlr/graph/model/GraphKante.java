package org.bschlangaul.antlr.graph.model;

public class GraphKante {

  public String von;

  public String nach;

  public boolean gerichtet;

  public double gewicht;

  public GraphKante(String von, String nach, double gewicht, boolean gerichtet) {
    this.von = von;
    this.nach = nach;
    this.gewicht = gewicht;
    this.gerichtet = gerichtet;
  }
}
