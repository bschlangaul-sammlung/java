package org.bschlangaul.aufgaben.tech_info.monte_carlo;

public class Kreis implements Function {

  @Override
  public double compute(double x) {
    return Math.sqrt(1 - x * x);
  }
}
