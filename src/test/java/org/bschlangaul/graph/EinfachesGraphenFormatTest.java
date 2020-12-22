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
    assertEquals(1, graph.gibAnzahlKanten());
  }

  @Test
  public void doppelteKanten() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("a-b;a-b;a-b");
    assertEquals(2, graph.gibAnzahlKnoten());
    assertEquals(1, graph.gibAnzahlKanten());
  }

  @Test
  public void methodeGibKnoten() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("z-a;b-c;x-y");
    assertArrayEquals(new String[] { "a", "b", "c", "x", "y", "z" }, graph.gibKnotenNamen());
  }

  @Test
  public void methodeGibKanten() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("z>a;a>c;a>b");
    EinfachesGraphenFormat.Kante[] kanten = graph.gibKanten();
    assertEquals("a", kanten[0].von);
    assertEquals("b", kanten[0].nach);
    assertEquals("a", kanten[1].von);
    assertEquals("c", kanten[1].nach);
    assertEquals("z", kanten[2].von);
    assertEquals("a", kanten[2].nach);
  }

  private int gibAnzahlKnoten(String einfachesFormat) {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat(einfachesFormat);
    return graph.gibAnzahlKnoten();
  }

  @Test
  public void strichpunktAlsTrenner() {
    assertEquals(4, gibAnzahlKnoten("a>b\na>c\na>d"));
    assertEquals(4, gibAnzahlKnoten("a>b\n\na>c\n\na>d"));
    assertEquals(4, gibAnzahlKnoten("a>b\ra>c\ra>d"));
    assertEquals(4, gibAnzahlKnoten("a>b;a>c;a>d"));
    assertEquals(4, gibAnzahlKnoten("a>b;\na>c;\na>d"));
  }

  private EinfachesGraphenFormat.Kante gibErsteKante(String einfachesFormat) {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat(einfachesFormat);
    EinfachesGraphenFormat.Kante[] kanten = graph.gibKanten();
    return kanten[0];
  }

  @Test
  public void kante() {
    EinfachesGraphenFormat.Kante k = gibErsteKante("a-b");
    assertEquals("a", k.von);
    assertEquals("b", k.nach);
    assertEquals(1, k.gewicht, 0);
    assertEquals(false, k.gerichtet);

    k = gibErsteKante("anton>berta");
    assertEquals("anton", k.von);
    assertEquals("berta", k.nach);
    assertEquals(1, k.gewicht, 0);
    assertEquals(true, k.gerichtet);

    k = gibErsteKante("a-b 1.23");
    assertEquals(1.23, k.gewicht, 0);

    k = gibErsteKante("a-b:-1");
    assertEquals(-1, k.gewicht, 0);

    k = gibErsteKante("a-b: 123");
    assertEquals(123, k.gewicht, 0);

    k = gibErsteKante("a-b :123");
    assertEquals(123, k.gewicht, 0);

    k = gibErsteKante("a-b : 123");
    assertEquals(123, k.gewicht, 0);
  }

  private EinfachesGraphenFormat.Knoten gibErstenKnoten(String einfachesFormat) {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat(einfachesFormat);
    EinfachesGraphenFormat.Knoten[] knoten = graph.gibKnoten();
    return knoten[0];
  }

  @Test
  public void knoten() {
    EinfachesGraphenFormat.Knoten k = gibErstenKnoten("v: a 1 2");
    assertEquals("a", k.name);
    assertEquals(1, k.x, 0);
    assertEquals(2, k.y, 0);

    k = gibErstenKnoten("v: abc -1 2.6");
    assertEquals("abc", k.name);
    assertEquals(-1, k.x, 0);
    assertEquals(2.6, k.y, 0);

    k = gibErstenKnoten("a>b;v: a 0.1 2.6");
    assertEquals("a", k.name);
    assertEquals(0.1, k.x, 0);
    assertEquals(2.6, k.y, 0);
  }

  @Test
  public void knotenNamen() {
    EinfachesGraphenFormat graph = new EinfachesGraphenFormat("v: z 1 2; v: a 2 3; v: b 4 5");
    assertArrayEquals(new String[] {"a", "b", "z"}, graph.gibKnotenNamen());
  }

}
