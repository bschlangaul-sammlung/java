package org.bschlangaul.graph.einfaches_format;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphLeserTest {

  private Graph lese(String graphenFormat) {
    return Graph.lese(graphenFormat);
  }

  private Knoten gibErstenKnoten(String graphenFormat) {
    Graph einfach = lese(graphenFormat);
    return einfach.gibKnoten()[0];
  }

  private Kante gibErsteKante(String graphenFormat) {
    Graph einfach = lese(graphenFormat);
    return einfach.gibKanten()[0];
  }

  private void vergleicheAnzahlKanten(int erwartet, String graphenFormat) {
    Graph einfach = lese(graphenFormat);
    assertEquals(erwartet, einfach.gibAnzahlKanten());
  }

  private void vergleicheAnzahlKnoten(int erwartet, String graphenFormat) {
    Graph einfach = lese(graphenFormat);
    assertEquals(erwartet, einfach.gibAnzahlKnoten());
  }

  private void vergleicheErstenKnoten(String name, double x, double y, String graphenFormat) {
    Knoten knoten = gibErstenKnoten(graphenFormat);
    assertEquals(name, knoten.name);
    assertEquals(x, knoten.x, 0);
    assertEquals(y, knoten.y, 0);
  }

  private void vergleicheErsteKante(String von, String nach, double gewicht, boolean gerichtet, String graphenFormat) {
    Kante kante = gibErsteKante(graphenFormat);
    assertEquals(von, kante.von);
    assertEquals(nach, kante.nach);
    assertEquals(gewicht, kante.gewicht, 0);
    assertEquals(gerichtet, kante.gerichtet);
  }

  @Test
  public void ungerichteteKanten() {
    vergleicheAnzahlKanten(2, "a -- b\r\nc -- d\r\n");
    vergleicheAnzahlKanten(2, "a -- b;c -- d;");
    vergleicheAnzahlKanten(2, "a--b;c--d;");
  }

  @Test
  public void kanten() {
    vergleicheErsteKante("a", "b", 1, true, "a -> b;");
    vergleicheErsteKante("a", "b", 1, false, "a--b;");
    vergleicheErsteKante("a", "b", 19, false, "a  --      b  : 19;");
    vergleicheErsteKante("anton", "berta", 17, false, "    anton\t--\tberta\t \t: 17;");
    vergleicheErsteKante("a", "b", 1, true, "a -> b;");
    vergleicheErsteKante("a", "b", 3.5, true, "a -> b: 3.5;");
    vergleicheErsteKante("a", "b", -2.567, false, "a -- b: -2.567;");
    vergleicheErsteKante("a", "b", -123, true, "a->b:-123;");
  }

  @Test
  public void knoten() {
    vergleicheAnzahlKnoten(2, "a: 1 1;b: 2 2;");
    vergleicheErstenKnoten("a", 1, -1, "a: 1 -1;");
    vergleicheErstenKnoten("a", 1.1, -1.1, "a: 1.1 -1.1;");
    vergleicheErstenKnoten("a", -1, -123, "a :-1 -123;");
    vergleicheErstenKnoten("a", 12.123, 0.2, "a:12.123 0.2;");
  }

  @Test
  public void knotenAmObjekt() {
    Knoten k = gibErstenKnoten("a: 1 2;");
    assertEquals("a", k.name);
    assertEquals(1, k.x, 0);
    assertEquals(2, k.y, 0);

    k = gibErstenKnoten("abc: -1 2.6;");
    assertEquals("abc", k.name);
    assertEquals(-1, k.x, 0);
    assertEquals(2.6, k.y, 0);

    k = gibErstenKnoten("a->b;a: 0.1 2.6;");
    assertEquals("a", k.name);
    assertEquals(0.1, k.x, 0);
    assertEquals(2.6, k.y, 0);
  }

  @Test
  public void kantenUndKnoten() {
    vergleicheAnzahlKnoten(4, "a: 1 1;b: 2 2;a -- b;c -- d;");
  }

  @Test
  public void gerichtet() {
    Graph graph = Graph.lese("a->b;");
    assertEquals(2, graph.gibAnzahlKnoten());
    assertEquals(1, graph.gibAnzahlKanten());
  }

  @Test
  public void ungerichtet() {
    Graph graph = Graph.lese("a--b;");
    assertEquals(2, graph.gibAnzahlKnoten());
    assertEquals(1, graph.gibAnzahlKanten());
  }

  @Test
  public void doppelteKanten() {
    Graph graph = Graph.lese("a--b;a--b;a--b;");
    assertEquals(2, graph.gibAnzahlKnoten());
    assertEquals(1, graph.gibAnzahlKanten());
  }

  @Test
  public void methodeGibKnoten() {
    Graph graph = Graph.lese("z--a;b--c;x--y;");
    assertArrayEquals(new String[] { "a", "b", "c", "x", "y", "z" }, graph.gibKnotenNamen());
  }

  @Test
  public void methodeGibKanten() {
    Graph graph = Graph.lese("z->a;a->c;a->b");
    Kante[] kanten = graph.gibKanten();
    assertEquals("a", kanten[0].von);
    assertEquals("b", kanten[0].nach);
    assertEquals("a", kanten[1].von);
    assertEquals("c", kanten[1].nach);
    assertEquals("z", kanten[2].von);
    assertEquals("a", kanten[2].nach);
  }

  private int gibAnzahlKnoten(String einfachesFormat) {
    Graph graph = Graph.lese(einfachesFormat);
    return graph.gibAnzahlKnoten();
  }

  @Test
  public void strichpunktAlsTrenner() {
    assertEquals(4, gibAnzahlKnoten("a->b\na->c\na->d;"));
    assertEquals(4, gibAnzahlKnoten("a->b\n\na->c\n\na->d;"));
    assertEquals(4, gibAnzahlKnoten("a->b\ra->c\ra->d;"));
    assertEquals(4, gibAnzahlKnoten("a->b;a->c;a->d;"));
    assertEquals(4, gibAnzahlKnoten("a->b;\na->c;\na->d;"));
  }

  @Test
  public void kante() {
    Kante k = gibErsteKante("a--b;");
    assertEquals("a", k.von);
    assertEquals("b", k.nach);
    assertEquals(1, k.gewicht, 0);
    assertEquals(false, k.gerichtet);

    k = gibErsteKante("anton->berta;");
    assertEquals("anton", k.von);
    assertEquals("berta", k.nach);
    assertEquals(1, k.gewicht, 0);
    assertEquals(true, k.gerichtet);

    k = gibErsteKante("a--b: 1.23;");
    assertEquals(1.23, k.gewicht, 0);

    k = gibErsteKante("a--b:-1;");
    assertEquals(-1, k.gewicht, 0);

    k = gibErsteKante("a--b : 123;");
    assertEquals(123, k.gewicht, 0);

    k = gibErsteKante("a--b :123;");
    assertEquals(123, k.gewicht, 0);

    k = gibErsteKante("a--b : 123;");
    assertEquals(123, k.gewicht, 0);
  }



  @Test
  public void knotenNamen() {
    Graph graph = Graph.lese("z: 1 2; a: 2 3; b: 4 5;");
    assertArrayEquals(new String[] { "a", "b", "z" }, graph.gibKnotenNamen());
  }

}
