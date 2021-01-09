package org.bschlangaul.examen.examen_66116.jahr_2012.herbst.getraenke;

/**
 * „Implementieren Sie eine Klasse Kasten zur Beschreibung eines Getränkekastens
 * mit den folgenden Eigenschaften. Entscheiden Sie dabei jeweils ob eine
 * Realisierung als Objekt- oder Klassenfeld sinnvoll ist.“
 */
public class Kasten {
  /**
   * Wir verwenden static, also ein sogenanntes Klassenfeld, da das Kastenpfand
   * für alle Kästen gleich ist: „Es existiert ein einheitliches Kastenpfand in
   * Höhe von 1,50 Euro.“
   */
  static double kastenPfad = 1.5;

  /**
   * Wir verwenden ein Objektfeld, d.h. ein nicht statisches Feld: „Für alle
   * Flaschen in einem Kasten gelte ein einheitliches Flaschenpfand, das jedoch
   * von Kasten zu Kasten verschieden sein kann.“
   */
  double flaschenPfad;

  /**
   * „Während das Flaschenpfand für alle Flaschen eines Kastens gleich ist, sind
   * die Einzelpreise der Flaschen je nach Inhalt unterschiedlich. Die
   * Einzelpreise (ohne Flaschenpfand) der im Kasten enthaltenen Flaschen sollen
   * in einem 2-dimensionalen Array abgelegt werden.“
   */
  double[][] flaschen;

  /**
   * „sowie eine einfach verkettete Liste der bestellten Getränkekästen. “
   */
  Kasten nächsterKasten = null;

  /**
   * „Geben Sie für die Klasse Kasten einen geeigneten Konstruktor an.“
   *
   * @param flaschen     Die Belegung des Kasten mit Flaschen als
   *                     zweidimensionales Feld der Flaschenpreise ohne
   *                     Flaschenpfand.
   * @param flaschenPfad Die Höhe des Flaschenpfads, dass für alle Flaschen in
   *                     diesem Kasten gleich ist.
   */
  public Kasten(double[][] flaschen, double flaschenPfad) {
    this.flaschen = flaschen;
    this.flaschenPfad = flaschenPfad;
  }

  /**
   * „Ergänzen Sie in der Klasse Kasten eine Objektmethode zur Berechnung des
   * Gesamtpreises des Getränkekastens inklusive Kasten- und Flaschenpfand.“
   *
   * @return Der Gesamtpreis des Getränkekastens inklusive Kasten- und
   *         Flaschenpfand.
   */
  double berechneGesamtPreis() {
    double gesamtPreis = kastenPfad;
    for (int i = 0; i < flaschen.length; i++) {
      double[] reihe = flaschen[i];
      for (int j = 0; j < reihe.length; j++) {
        double flaschenPreis = flaschen[i][j];
        // Nur im Kasten vorhandene Flaschen kosten auch Flaschenpfand.
        if (flaschenPreis > 0)
          gesamtPreis += flaschenPfad + flaschen[i][j];
      }
    }
    return gesamtPreis;
  }
}
