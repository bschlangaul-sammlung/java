package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GraphAdjazenzListeTest {

  @Test
  public void methodeGibKantenGewicht() {
    GraphAdjazenzListe liste = new GraphAdjazenzListe(2);
    liste.setzeKante("A", "B", 42, true);
    assertEquals(42, liste.gibKanteGewicht("A", "B"));
    assertEquals(-1, liste.gibKanteGewicht("A", "C"));
  }

  @Test
  public void gerichteteKante() {
    GraphAdjazenzListe liste = new GraphAdjazenzListe(2);
    liste.setzeKante("A", "B", 42, true);
    assertEquals(42, liste.gibKanteGewicht("A", "B"));
    assertEquals(-1, liste.gibKanteGewicht("B", "A"));
  }

  @Test
  public void ungerichteteKante() {
    GraphAdjazenzListe liste = new GraphAdjazenzListe(2);
    liste.setzeKante("A", "B", 42, false);
    assertEquals(42, liste.gibKanteGewicht("A", "B"));
    assertEquals(42, liste.gibKanteGewicht("B", "A"));
  }

}
