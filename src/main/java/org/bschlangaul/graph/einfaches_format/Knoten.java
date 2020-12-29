package org.bschlangaul.graph.einfaches_format;

public class Knoten implements Comparable<Knoten> {
  public String name;
  public double x;
  public double y;

  public Knoten(String name) {
    this.name = name;
  }

  public Knoten(String name, double x, double y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }

  /**
   * Diese Methode wird benötigt, um keine doppelten Knoten in dem HashSet knoten
   * zu haben.
   */
  @Override
  public int hashCode() {
    return name.hashCode();
  }

  /**
   * Diese Methode wird benötigt, um keine doppelten Knoten in dem HashSet knoten
   * zu haben.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Knoten) {
      Knoten knoten = (Knoten) o;
      return name.equals(knoten.name);
    }
    return false;
  }

  /**
   * Diese Methode wird benötigt, um die Knoten sortieren zu können.
   *
   * @param knoten Eine andere Knoten, der sortiert werden soll.
   *
   * @return 0, -1, 1
   */
  @Override
  public int compareTo(Knoten knoten) {
    return name.compareTo(knoten.name);
  }

  public String gibAlsEinfachesFormat() {
    return String.format("%s: %s %s\n", name, Graph.formatiereZahl(x), Graph.formatiereZahl(y));
  }

  public String toString() {
    return String.format("Knoten (name: %s, x: %s, y: %s)", name, Graph.formatiereZahl(x), Graph.formatiereZahl(y));
  }
}
