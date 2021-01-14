package org.bschlangaul.aufgaben.tech_info.monte_carlo;

public interface MonteCarlo {

  /**
   * Berechne das Integral der Funktion f von a nach b.
   *
   * @param f          Die Integral-Funktion.
   * @param a          interval a
   * @param b          interval b
   * @param minY       Der kleines Y-Wert der Funktion im Ausschnitt.
   * @param maxY       Der größte Y-Wert der Funktion im Ausschnitt.
   * @param threads    Anzahl der zusätzlichen Threads, die zur Berechnung
   *                   verwendent werden soll.
   * @param iterations Die Gesamtzahl an zufälligen Punkten, die zur Berechnung
   *                   herangezogen werden sollen.
   *
   * @return Die Größe des Integrals.
   */
  public double computeIntegral(Function f, double a, double b, double minY, double maxY, int threads, long iterations);

}
