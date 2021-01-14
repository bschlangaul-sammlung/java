package org.bschlangaul.aufgaben.tech_info.monte_carlo;

import java.util.Random;

public class MonteCarloImpl implements MonteCarlo {

  Random zufall = new Random();

  private double gibZufallsZahl(double min, double max) {
    return min + (max - min) * zufall.nextDouble();
  }

  /**
   * {@inheritDoc}
   */
  public double computeIntegral(Function f, double minX, double maxX, double minY, double maxY, int threads,
      long iterations) {

    long treffer = 0;
    for (int i = 0; i <= iterations; i++) {
      double x = gibZufallsZahl(minX, maxX);
      double y = gibZufallsZahl(minY, maxY);
      double berechntesY = f.compute(x);
      //System.out.println(berechntesY);
      if (berechntesY > y && y > 0) {
        treffer++;
      } else if (berechntesY < y && y < 0) {
        treffer--;
      }
    }
          System.out.println(treffer);

    return (double) treffer / iterations;
  }

  public static void main(String[] args) {
    MonteCarloImpl m = new MonteCarloImpl();
    Kreis k = new Kreis();
    Polynomial p1 = new Polynomial(new double[] { 0, 1 });
    //double ergebnis = m.computeIntegral(k, -1, 1, 0, k.compute(1), 8, 1000000);
    double ergebnis = m.computeIntegral(p1, 0, 1, 0, p1.compute(1), 8, 1000000);
    System.out.printf("Ergebnis: %f", ergebnis);

  }
}
