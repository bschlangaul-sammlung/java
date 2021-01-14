package org.bschlangaul.aufgaben.tech_info.monte_carlo;

public class MonteCarloImpl implements MonteCarlo {
  /**
   * {@inheritDoc}
   */
  public double computeIntegral(Function f, double a, double b, double minY, double maxY, int threads,
      long iterations) {
    return 1d;
  }

  public static void main(String[] args) {

    // Gesamtzahl der Tropfen
    int g = 10000000;
    // Tropfen im Viertelkreis
    int v = 0;
    // Koordinaten des Punktes P
    double x, y;
    System.out.println(" Monte Carlo Methode");
    System.out.println(" Naeherung fuer Pi:");
    for (int i = 1; i <= g; i++) {
      x = Math.random();
      y = Math.random();
      if (Math.hypot(x, y) <= 1)
        v = v + 1;
    }
    double pi = 4 * (double) v / g;
    System.out.printf(" %d Tropfen, davon %d Tropfen im Viertelkreis, Pi etwa %g%n", g, v, pi);

  }
}
