package org.bschlangaul.graph.algorithmen;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

class VergleichDijkstra {

  public KuerzesterPfadDijkstra djikstra;

  public VergleichDijkstra(String einfachesGraphenFormat, String startKnotenName) {
    djikstra = new KuerzesterPfadDijkstra(einfachesGraphenFormat);
    djikstra.sucheKürzestenPfadMatrix(startKnotenName);
  }

  public void vergleicheEntfernung(String knotenName, int erwartet) {
    assertEquals(erwartet, djikstra.gibEntfernung(knotenName));
  }

  public void vergleicheVorgänger(String knotenName, String erwartet) {
    assertEquals(erwartet, djikstra.gibVorgänger(knotenName));
  }
}

public class DijkstraTest {

  public void einfachStatisch() {
    assertArrayEquals(new int[] { 0, 2, 3 }, KuerzesterPfadDijkstra.sucheKürzestenPfad("a--b: 2; a--c: 3", "a"));
  }

  @Test
  public void dreiKnoten() {
    VergleichDijkstra v = new VergleichDijkstra("a--b: 2; a--c: 3", "a");
    v.vergleicheEntfernung("a", 0);
    v.vergleicheVorgänger("a", "a");

    v.vergleicheEntfernung("b", 2);
    v.vergleicheVorgänger("b", "a");

    v.vergleicheEntfernung("c", 3);
    v.vergleicheVorgänger("c", "a");
  }

  @Test
  public void fünfKnoten() {
    VergleichDijkstra v = new VergleichDijkstra("A->B:100; A->D:50; B->C:100; B->E:250; C->E:50; D->B:100; D->E:250;",
        "A");
    v.vergleicheEntfernung("A", 0);
    v.vergleicheVorgänger("A", "A");

    v.vergleicheEntfernung("B", 100);
    v.vergleicheVorgänger("B", "A");

    v.vergleicheEntfernung("C", 200);
    v.vergleicheVorgänger("C", "B");

    v.vergleicheEntfernung("D", 50);
    v.vergleicheVorgänger("D", "A");

    v.vergleicheEntfernung("E", 250);
    v.vergleicheVorgänger("E", "C");
  }

  @Test
  public void sechsKnoten() {
    VergleichDijkstra v = new VergleichDijkstra(
        "a->b: 1; a->e: 7; b->c: 3; c->d: 8; c->e: 3; e->f: 1; c->f: 6; f->c: 1; f->d: 3", "a");
    v.vergleicheEntfernung("a", 0);
    v.vergleicheEntfernung("b", 1);
    v.vergleicheEntfernung("c", 4);
    v.vergleicheEntfernung("d", 11);
    v.vergleicheEntfernung("e", 7);
    v.vergleicheEntfernung("f", 8);
  }

}
