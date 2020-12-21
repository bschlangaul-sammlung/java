package org.bschlangaul.graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EinfachesGraphenFormatTest {

  @Test
  public void gerichtet() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("a>b");
    assertEquals(2, graph.gibAnzahlKnoten());
    assertEquals(1, graph.gibAnzahlKanten());
  }

  @Test
  public void ungerichtet() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("a-b");
    assertEquals(2, graph.gibAnzahlKnoten());
    assertEquals(2, graph.gibAnzahlKanten());
  }

  @Test
  public void doppelteKanten() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("a-b\na-b\n-b");
    assertEquals(2, graph.gibAnzahlKnoten());
    assertEquals(2, graph.gibAnzahlKanten());
  }

  @Test
  public void methodeGibKnoten() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("z-a\nb-c\nx-y");
    assertArrayEquals(new String[] { "a", "b", "c", "x", "y", "z" }, graph.gibKnoten());
  }

  @Test
  public void methodeGibKanten() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("z>a\na>c\na>b");
    EinfachesGraphenFormat.Kante[] kanten = graph.gibKanten();
    assertEquals("a", kanten[0].von);
    assertEquals("b", kanten[0].nach);
    assertEquals("a", kanten[1].von);
    assertEquals("c", kanten[1].nach);
    assertEquals("z", kanten[2].von);
    assertEquals("a", kanten[2].nach);
  }

}
