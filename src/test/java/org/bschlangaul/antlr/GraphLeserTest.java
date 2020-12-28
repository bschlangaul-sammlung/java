package org.bschlangaul.antlr;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.graph.EinfachesGraphenFormat;
import org.junit.Test;

public class GraphLeserTest {

  private EinfachesGraphenFormat lese(String graphenFormat) throws Exception {
    return GraphLeser.lese(graphenFormat);
  }

  private EinfachesGraphenFormat.Knoten gibErstenKnoten(String graphenFormat) throws Exception {
    EinfachesGraphenFormat einfach = lese(graphenFormat);
    return einfach.gibKnoten()[0];
  }

  private EinfachesGraphenFormat.Kante gibErsteKante(String graphenFormat) throws Exception {
    EinfachesGraphenFormat einfach = lese(graphenFormat);
    return einfach.gibKanten()[0];
  }

  private void vergleicheAnzahlKanten(int erwartet, String graphenFormat) throws Exception {
    EinfachesGraphenFormat einfach = lese(graphenFormat);
    assertEquals(erwartet, einfach.gibAnzahlKanten());
  }

  private void vergleicheAnzahlKnoten(int erwartet, String graphenFormat) throws Exception {
    EinfachesGraphenFormat einfach = lese(graphenFormat);
    assertEquals(erwartet, einfach.gibAnzahlKnoten());
  }

  private void vergleicheErstenKnoten(String name, double x, double y, String graphenFormat) throws Exception {
    EinfachesGraphenFormat.Knoten knoten = gibErstenKnoten(graphenFormat);
    assertEquals(name, knoten.name);
    assertEquals(x, knoten.x, 0);
    assertEquals(y, knoten.y, 0);
  }

  private void vergleicheErsteKante(String von, String nach, double gewicht, boolean gerichtet, String graphenFormat)
      throws Exception {
    EinfachesGraphenFormat.Kante kante = gibErsteKante(graphenFormat);
    assertEquals(von, kante.von);
    assertEquals(nach, kante.nach);
    assertEquals(gewicht, kante.gewicht, 0);
    assertEquals(gerichtet, kante.gerichtet);
  }

  @Test
  public void ungerichteteKanten() throws Exception {
    vergleicheAnzahlKanten(2, "a -- b\r\nc -- d\r\n");
    vergleicheAnzahlKanten(2, "a -- b;c -- d;");
    vergleicheAnzahlKanten(2, "a--b;c--d;");
  }

  @Test
  public void kanten() throws Exception {
    vergleicheErsteKante("a", "b", 1, true, "a -> b;");
    vergleicheErsteKante("a", "b", 1, false, "a--b;");
    // vergleicheErsteKante("a", "b", false, "a -- b;");
    vergleicheErsteKante("a", "b", 1, true, "a -> b;");
    vergleicheErsteKante("a", "b", 3.5, true, "a -> b: 3.5;");
    vergleicheErsteKante("a", "b", -2.567, false, "a -- b: -2.567;");
    vergleicheErsteKante("a", "b", -123, true, "a->b:-123;");
  }

  @Test
  public void knoten() throws Exception {
    vergleicheAnzahlKnoten(2, "a: 1 1;b: 2 2;");
    vergleicheErstenKnoten("a", 1, -1, "a: 1 -1;");
    vergleicheErstenKnoten("a", 1.1, -1.1, "a: 1.1 -1.1;");
    vergleicheErstenKnoten("a", -1, -123, "a :-1 -123;");
    vergleicheErstenKnoten("a", 12.123, 0.2, "a:12.123 0.2;");
  }

  @Test
  public void kantenUndKnoten() throws Exception {
    vergleicheAnzahlKnoten(2, "a: 1 1;b: 2 2;a -- b;c -- d;");
  }
}
