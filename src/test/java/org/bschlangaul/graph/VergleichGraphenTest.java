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

  public void vergleicheKanteGewicht(String von, String nach, int erwartet) {
    assertEquals(erwartet, liste.gibKanteGewicht(von, nach));
    assertEquals(erwartet, matrix.gibKanteGewicht(von, nach));
  }

}

public class VergleichGraphenTest {

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

    v.vergleicheKanteGewicht("a", "b", 1);
    v.vergleicheKanteGewicht("a", "e", 7);
    v.vergleicheKanteGewicht("b", "c", 3);
    v.vergleicheKanteGewicht("c", "d", 8);
    v.vergleicheKanteGewicht("c", "e", 3);
    v.vergleicheKanteGewicht("e", "f", 1);
    v.vergleicheKanteGewicht("c", "f", 6);
    v.vergleicheKanteGewicht("f", "c", 1);
    v.vergleicheKanteGewicht("f", "d", 3);
    v.vergleicheKanteGewicht("x", "y", -1);
  }

}
