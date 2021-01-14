package org.bschlangaul.aufgaben.tech_info.monte_carlo;

import java.util.Random;

public class MonteCarloImpl implements MonteCarlo {

  Random zufall = new Random();

  /**
   * Kleine Hilfsfunktions, um einen Bereich spezifizeren zu könnten, aus den man
   * Zufallszahlen will.
   *
   * @param min Die Zufallszahl soll größer gleich als diese Zahl sein.
   * @param max Die Zufallszahl soll kleiner als diese Zahl sein.
   *
   * @return Eine zufällige Zahl.
   */
  private double gibZufallsZahl(double min, double max) {
    return min + (max - min) * zufall.nextDouble();
  }

  /**
   * {@inheritDoc}
   */
  public double computeIntegral(Function f, double xMin, double xMax, double yMin, double yMax, int threads,
      long iterations) {
    // Berechnung des Bereichs für die Zufallszahlen auf der Y-Achse.
    // Das Integral ist ja die Fläche von der X-Achse (y=0) zur Linie des Graphen.
    // Es muss mindestens einmal null vorkommen, entweder beim minimalen Y oder beim
    // maximalen Y.
    yMin = yMin > 0 ? 0 : yMin;
    yMax = yMax < 0 ? 0 : yMax;

    long treffer = 0;
    for (int i = 0; i <= iterations; i++) {
      double x = gibZufallsZahl(xMin, xMax);
      double y = gibZufallsZahl(yMin, yMax);
      double berechntesY = f.compute(x);
      if (berechntesY >= y && y >= 0) {
        treffer++;
      } else if (berechntesY < y && y < 0) {
        treffer--;
      }
    }
    double flächeZufallsViereck = (xMax - xMin) * (yMax - yMin);
    // Diesen cast braucht man, da int bzw. long durch int/long wieder int/long
    // ergibt, hier also 0.
    double anteilTreffer = (double) treffer / iterations;
    return flächeZufallsViereck * anteilTreffer;
  }

}
