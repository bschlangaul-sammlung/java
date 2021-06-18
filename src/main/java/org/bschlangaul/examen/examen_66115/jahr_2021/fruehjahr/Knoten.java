package org.bschlangaul.examen.examen_66115.jahr_2021.fruehjahr;

public class Knoten {
  Knoten links;
  Knoten rechts;

  String name;
  /**
   * Ebene
   */
  int y;

  /**
   * Bewegung bezüglich des Vorknotens. Relative Lage.
   */
  int x;

  void durchlaufenStart (Knoten knoten) {
    durchlaufen(knoten, knoten.x, knoten.y);
  }

  void durchlaufen (Knoten knoten, int x, int y) {
    System.out.println(knoten.name + " " + knoten.x + " " + knoten.y);

    if (links != null) {
      links.durchlaufen(links, x + links.x, y + 1);
    }
    if (rechts != null) {
      rechts.durchlaufen(rechts, x + rechts.x, y + 1);
    }
  }
}
