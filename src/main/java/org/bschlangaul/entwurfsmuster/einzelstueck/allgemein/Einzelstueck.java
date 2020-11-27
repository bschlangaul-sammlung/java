package org.bschlangaul.entwurfsmuster.einzelstueck.allgemein;

public class Einzelstueck {
  /**
   * Da es nur eine Instanz der Klasse geben darf, wird diese in einer
   * statischen Variable in derselben Klasse gespeichert.
   */
  private static Einzelstueck instanz = null;

  /**
   * Die Klasse hat einen private-Konstruktor. Dadurch wird verhindert,
   * dass eine Instanz mit dem new()-Operator instanziiert wird,
   */
  private Einzelstueck() {
  };

  /**
   * Es gibt eine statische gibInstanz()-Methode, über die man die
   * Instanz beziehen kann.
   *
   * @return Die einzige Instanz des Einzelstücks.
   */
  public static Einzelstueck gibInstanz() {
    if (instanz == null)
      instanz = new Einzelstueck();
    return instanz;
  }

  public void macheIrgendwas() {
    // Verhalten des Objektes
  }
}
