package org.bschlangaul.graph.einfaches_format;

/**
 * Diese Klasse dient als eine Art Zwischenspeicher für Kanteninformationen.
 */
public class GraphenFormatKante implements Comparable<GraphenFormatKante> {
  public String von;
  public String nach;
  public double gewicht;
  public boolean gerichtet;

  /**
   * Möglicherweise ist dieses Attribut nützlich um in TikZ-Grafen einzelne Kanten
   * zu markieren, z. B. durch dickeren Linien, um Spannbäume etc. zu
   * kennzeichnen.
   */
  public boolean markiert;

  public GraphenFormatKante(String von, String nach, double gewicht, boolean gerichtet) {
    this.von = von;
    this.nach = nach;
    this.gewicht = gewicht;
    this.gerichtet = gerichtet;
  }

  public GraphenFormatKante(String von, String nach, double gewicht, boolean gerichtet, boolean markiert) {
    this.von = von;
    this.nach = nach;
    this.gewicht = gewicht;
    this.gerichtet = gerichtet;
    this.markiert = markiert;
  }

  /**
   * Diese Methode wird benötigt, um keine doppelten Kanten in dem HashSet kanten
   * zu haben.
   */
  @Override
  public int hashCode() {
    String ausgabe = von + ":" + nach;
    return ausgabe.hashCode();
  }

  /**
   * Diese Methode wird benötigt, um keine doppelten Kanten in dem HashSet kanten
   * zu haben.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof GraphenFormatKante) {
      GraphenFormatKante kante = (GraphenFormatKante) o;
      if (von.equals(kante.von) && nach.equals(kante.nach))
        return true;
    }
    return false;
  }

  /**
   * Diese Methode wird benötigt, um die Kanten sortieren zu können.
   *
   * @param object Eine andere einfache Kante, die verglichen werden soll.
   *
   * @return 0, -1, 1
   */
  @Override
  public int compareTo(GraphenFormatKante kante) {
    int ersterVergleich = von.compareTo(kante.von);
    if (ersterVergleich != 0)
      return ersterVergleich;
    return nach.compareTo(kante.nach);
  }

  public String gibAlsEinfachesFormat() {
    String ausgabe;
    String gerichtetZeichen = gerichtet ? ">" : "-";
    ausgabe = String.format("%s -%s %s", von, gerichtetZeichen, nach);
    if (gewicht != 1)
      ausgabe = String.format("%s: %s", ausgabe, GraphenFormat.formatiereZahl(gewicht));
    return ausgabe + "\n";
  }

  public String toString() {
    return String.format("Kante (von: %s, nach: %s, gewicht: %s, gerichtet: %b)", von, nach,
        GraphenFormat.formatiereZahl(gewicht), gerichtet);
  }
}
