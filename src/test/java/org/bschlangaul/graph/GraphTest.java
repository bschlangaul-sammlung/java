package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class GraphHülle extends Graph {

  public void setzeKante(String von, String nach, double gewicht, boolean gerichtet) {
  }

  public double gibMaximalesGewicht() {
    return 0;
  }

  public double gibMinimalesGewicht() {
    return 0;
  }
}

public class GraphTest {

  @Test
  public void methodeSetze() {
    Graph g = new GraphHülle();
    g.setzeKnoten("A");
    assertEquals(0, g.gibKnotenNummer("A"));
  }

  @Test
  public void mehrmalsDerGleicheName() {
    Graph g = new GraphHülle();
    assertEquals(0, g.setzeKnoten("A"));
    assertEquals(0, g.setzeKnoten("A"));
    assertEquals(0, g.setzeKnoten("A"));
    assertEquals(1, g.gibKnotenAnzahl());
  }

  @Test
  public void methodeGibKnotenName() {
    Graph g = new GraphHülle();
    g.setzeKnoten("A");
    g.setzeKnoten("B");
    g.setzeKnoten("C");
    assertEquals("A", g.gibKnotenName(0));
    assertEquals("B", g.gibKnotenName(1));
    assertEquals("C", g.gibKnotenName(2));
  }

  @Test
  public void methodeGibKnotenAnzahl() {
    Graph g = new GraphHülle();
    assertEquals(0, g.gibKnotenAnzahl());

    g.setzeKnoten("A");
    assertEquals(1, g.gibKnotenAnzahl());

    g.setzeKnoten("B");
    assertEquals(2, g.gibKnotenAnzahl());

    g.setzeKnoten("C");
    assertEquals(3, g.gibKnotenAnzahl());
  }
}
