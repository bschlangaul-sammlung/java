package org.bschlangaul.aufgaben.tech_info.monte_carlo;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Die Klasse {@link MonteCarloImpl} berechnet parallel, d. h. mit Hilfe von
 * Threading, für eine übergebene Funktion (Klasse {@link Function}) das
 * Integral im angegebenen Intervall [xMin, xMax] mittels
 * Monte-Carlo-Integration.
 */
public class MonteCarloImpl implements MonteCarlo {
  /**
   * Die Zufälligkeit muss mithilfe der Klasse Random von Java
   * ({@code java.util.Random}) erzeugt werden ({@code nextDouble()}) Hier darf
   * {@code Math.random()} nicht genutzt werden, denn dieser Methodenaufruf ist
   * synchronisiert und somit wird die parallele Implementierung langsamer sein
   * als die sequentielle.
   */
  private Random zufall = new Random();

  private AtomicInteger treffer;

  /**
   * Die Klasse {@link PunkteRechner} implementiert das Interface {@link Runnable}
   * und wird deshalb dazu verwendet, in einem eigenen Thread zufällige Punkte zu
   * erzeugen, um das Integral zu berechnen.
   */
  public class PunkteRechner implements Runnable {
    private Function f;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private long iterations;
    private double ergebnis;

    /**
     * Standard-Konstruktor für den die Klasse {@link PunkteRechner}.
     *
     * @param funktion       Eine Instance einer Klasse, die das Interface
     *                       {@link Function} implementiert.
     * @param xMin           Der kleinste X-Wert des Integrals.
     * @param xMax           Der größte X-Wert des Integrals.
     * @param yMin           Der kleinste Y-Wert der Funktion im Ausschnitt.
     * @param yMax           Der größte Y-Wert der Funktion im Ausschnitt.
     * @param wiederholungen Die Gesamtzahl an zufälligen Punkten, die zur
     *                       Berechnung herangezogen werden sollen.
     */
    public PunkteRechner(Function funktion, double xMin, double xMax, double yMin, double yMax, long wiederholungen) {
      this.f = funktion;
      this.xMin = xMin;
      this.xMax = xMax;
      this.yMin = yMin;
      this.yMax = yMax;
      this.iterations = wiederholungen;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
      for (int i = 0; i <= iterations; i++) {
        double x = gibZufallsZahl(xMin, xMax);
        double y = gibZufallsZahl(yMin, yMax);
        double berechntesY = f.compute(x);
        // Ich glaube die “=” Zeichen bei “>=” sind nicht relevant. Die Test werden so
        // oder so bestanden. Sie schaden aber auch nicht.
        if (berechntesY >= y && y >= 0) {
          treffer.incrementAndGet();
        } else if (berechntesY < y && y < 0) {
          // Das Integral einer Funktion entspricht der Fläche zwischen dem
          // Graphen der Funktion und der x-Achse. Hierbei zählen die
          // Flächenstücke unterhalb der x-Achse negativ. Wir ziehen also von der Zahl
          // treffer ab.
          treffer.decrementAndGet();
        }
      }
    }

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
     * Gib das Ergebnis der Monte-Carlo-Integral-Rechnung.
     *
     * @return Das Ergebnis der Monte-Carlo-Integral-Rechnung.
     */
    public double gibErgebnis() {
      return ergebnis;
    }
  }

  /**
   * Berechne das Integral der Funktion {@code funktion} im Ausschnitt von
   * {@code xMin} nach {@code xMax} mit Hilfe verschiedener Threads. Die Anzahl
   * der Threads kann angegeben werden.
   *
   * @param funktion       Eine Instance einer Klasse, die das Interface
   *                       {@link Function} implementiert.
   * @param xMin           Der kleinste X-Wert des Integrals.
   * @param xMax           Der größte X-Wert des Integrals.
   * @param yMin           Der kleinste Y-Wert der Funktion im Ausschnitt.
   * @param yMax           Der größte Y-Wert der Funktion im Ausschnitt.
   * @param anzahlThreads  Die Anzahl der zusätzlichen Threads, die zur Berechnung
   *                       verwendet werden sollen.
   * @param wiederholungen Die Gesamtzahl an zufälligen Punkten, die zur
   *                       Berechnung herangezogen werden sollen.
   *
   * @return Die Größe des Integrals.
   */
  public double computeIntegral(Function funktion, double xMin, double xMax, double yMin, double yMax,
      int anzahlThreads, long wiederholungen) {
    treffer = new AtomicInteger();
    PunkteRechner[] rechner = new PunkteRechner[anzahlThreads];

    // Berechnung des Bereichs für die Zufallszahlen auf der Y-Achse.
    // Das Integral ist ja die Fläche von der X-Achse (y=0) zur Linie des Graphen.
    // Es muss mindestens einmal 0 vorkommen, entweder beim minimalen Y oder beim
    // maximalen Y.
    yMin = yMin > 0 ? 0 : yMin;
    yMax = yMax < 0 ? 0 : yMax;

    for (int i = 0; i < anzahlThreads; i++) {
      rechner[i] = new PunkteRechner(funktion, xMin, xMax, yMin, yMax, wiederholungen / anzahlThreads);
      Thread thread = new Thread(rechner[i]);
      thread.start();
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // In diesem Viereck wurden an zufälligen Stellen Werte platziert.
    // Wir berechnen die Größe des Vierecks.
    double flächeZufallsViereck = (xMax - xMin) * (yMax - yMin);
    // Diesen cast braucht man, da int bzw. long durch int/long wieder int/long
    // ergibt, hier also 0.
    double anteilTreffer = (double) treffer.get() / wiederholungen;

    return flächeZufallsViereck * anteilTreffer;

  }
}
