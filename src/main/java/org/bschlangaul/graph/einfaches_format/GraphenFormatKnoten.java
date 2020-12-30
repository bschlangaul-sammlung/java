package org.bschlangaul.graph.einfaches_format;

public class GraphenFormatKnoten implements Comparable<GraphenFormatKnoten> {
  public String name;
  public double x;
  public double y;
  /**
   * Möglicherweise ist dieses Attribut nützlich um in TikZ-Grafen einzelne Knoten
   * zu markieren, z. B. durch dickeren Linien, um Besuchsreihenfolgen etc. zu
   * kennzeichnen.
   */
  public boolean markiert;

  public GraphenFormatKnoten(String name) {
    this.name = name;
  }

  public GraphenFormatKnoten(String name, double x, double y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }

  public GraphenFormatKnoten(String name, double x, double y, boolean markiert) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.markiert = markiert;
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
    if (o instanceof GraphenFormatKnoten) {
      GraphenFormatKnoten knoten = (GraphenFormatKnoten) o;
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
  public int compareTo(GraphenFormatKnoten knoten) {
    return name.compareTo(knoten.name);
  }

  public String gibAlsEinfachesFormat() {
    return String.format("%s: %s %s\n", name, GraphenFormat.formatiereZahl(x), GraphenFormat.formatiereZahl(y));
  }

  public String toString() {
    return String.format("Knoten (name: %s, x: %s, y: %s)", name, GraphenFormat.formatiereZahl(x),
        GraphenFormat.formatiereZahl(y));
  }
}
