package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class Vergleich {

  public GraphAdjazenzListe liste;
  public GraphAdjazenzMatrix matrix;

  public Vergleich(String einfachesGraphenFormat) {
    liste = new GraphAdjazenzListe(einfachesGraphenFormat);
    matrix = new GraphAdjazenzMatrix(einfachesGraphenFormat);
  }

}

public class GraphenImplementationsVergleichTest {

  String graph1 = "a->b: 1; a->e: 7; b->c: 3; c->d: 8; c->e: 3; e->f: 1; c->f: 6; f->c: 1; f->d: 3";

  @Test
  public void testGraph1() {
    Vergleich v = new Vergleich(graph1);
    assertEquals(6, v.liste.gibKnotenAnzahl());
    assertEquals(6, v.matrix.gibKnotenAnzahl());

    assertEquals(8, v.liste.gibMaximalesGewicht());
    assertEquals(8, v.matrix.gibMaximalesGewicht());

    assertEquals(0, v.liste.gibMinimalesGewicht());
    //assertEquals(-1, v.matrix.gibMinimalesGewicht());
  }

}
