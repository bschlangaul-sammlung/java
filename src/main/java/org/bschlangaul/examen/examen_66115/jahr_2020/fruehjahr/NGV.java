package org.bschlangaul.examen.examen_66115.jahr_2020.fruehjahr;

/**
 * NBV = Niedrigster gemeinsamer Vorfahre.
 *
 * https://afteracademy.com/blog/lowest-common-ancestor-of-a-binary-tree
 */
public class NGV {
  static class Knoten {
    int schlüssel;
    Knoten links;
    Knoten rechts;

    public Knoten(int schlüssel) {
      this.schlüssel = schlüssel;
    }
  }

  /**
   * ngV = niedrigster gemeinsamer Vorfahre
   *
   * @param wurzel  Der Wurzelkonten dies Binärbaums.
   * @param knoten1 Der erste Knoten, dessen niedrigster gemeinsamer Vorfahre
   *                gesucht werden soll.
   * @param knoten2 Der zweite Knoten, dessen niedrigster gemeinsamer Vorfahre
   *                gesucht werden soll.
   *
   * @return Der niedrigste gemeinsae Vorfahre der Knoten 1 und 2.
   */
  public static Knoten ngVRekursiv(Knoten wurzel, Knoten knoten1, Knoten knoten2) {
    if (wurzel == null)
      return null;
    if (wurzel.equals(knoten1) || wurzel.equals(knoten2))
      return wurzel;
    Knoten links = ngVRekursiv(wurzel.links, knoten1, knoten2);
    Knoten rechts = ngVRekursiv(wurzel.rechts, knoten1, knoten2);
    if (links == null)
      return rechts;
    else if (rechts == null)
      return links;
    else
      return wurzel;
  }

  /**
   * <pre>
   * {@code
   *     20
   *    / \
   *   8   22
   *  / \
   * 4   12
   *    / \
   *   10  14
   * }
   * </pre>
   *
   * Beispiele von
   * https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
   *
   * @param args Kommandozeilen-Argumente
   */
  public static void main(String[] args) {
    Knoten wurzel = new Knoten(20);

    Knoten knoten8 = new Knoten(8);
    Knoten knoten22 = new Knoten(22);
    Knoten knoten4 = new Knoten(4);
    Knoten knoten12 = new Knoten(12);
    Knoten knoten10 = new Knoten(10);
    Knoten knoten14 = new Knoten(14);

    wurzel.links = knoten8;
    wurzel.rechts = knoten22;
    wurzel.links.links = knoten4;
    wurzel.links.rechts = knoten12;
    wurzel.links.rechts.links = knoten10;
    wurzel.links.rechts.rechts = knoten14;

    System.out.println(ngVRekursiv(wurzel, knoten10, knoten14).schlüssel); // 12
    System.out.println(ngVRekursiv(wurzel, knoten14, knoten8).schlüssel); // 8
    System.out.println(ngVRekursiv(wurzel, knoten10, knoten22).schlüssel); // 20
  }
}
