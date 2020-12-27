package org.bschlangaul.antlr;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.graph.EinfachesGraphenFormat;
import org.junit.Test;

public class GraphLeserTest {

  private EinfachesGraphenFormat lese(String inhalt) throws Exception {
    return GraphLeser.lese(inhalt);
  }

  private void vergleicheAnzahlKanten(int erwartet, String graphenFormat) throws Exception {
    EinfachesGraphenFormat einfach = lese(graphenFormat);
    assertEquals(erwartet, einfach.gibAnzahlKanten());
  }

  private void vergleicheAnzahlKnoten(int erwartet, String graphenFormat) throws Exception {
    EinfachesGraphenFormat einfach = lese(graphenFormat);
    assertEquals(erwartet, einfach.gibAnzahlKnoten());
  }

  @Test
  public void ungerichteteKanten() throws Exception {
    vergleicheAnzahlKanten(2, "a -- b\r\nc -- d\r\n");
    vergleicheAnzahlKanten(2, "a -- b;c -- d;");
    // vergleicheAnzahlKanten(2, "a--b;c--d;");

  }

  @Test
  public void knoten() throws Exception {
    vergleicheAnzahlKnoten(2, "a: 1 1;b: 2 2;");

  }


  @Test
  public void kantenUndKnoten() throws Exception {
    vergleicheAnzahlKnoten(2, "a: 1 1;b: 2 2;a -- b;c -- d;");

  }
}
