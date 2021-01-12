package org.bschlangaul.aufgaben.tech_info.monte_carlo;

public interface MonteCarlo {

  /**
   *
   * Computes the integral over f from a to b.
   *
   * @param f          the function to integrate
   * @param a          interval a
   * @param b          interval b
   * @param minY       lowest function value in the interval
   * @param maxY       highest function value in the interval
   * @param threads    number of additional threads to use
   * @param iterations the total number of random points used for the calculation
   * @return the integral size
   */
  public double computeIntegral(Function f, double a, double b, double minY, double maxY, int threads, long iterations);

}
