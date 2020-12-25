package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class VergleichDijkstra {

  public DijkstraAdjazenzListe liste;
  public DijkstraAdjazenzMatrix matrix;

  public VergleichDijkstra(String einfachesGraphenFormat, String startKnotenName) {
    liste = new DijkstraAdjazenzListe(einfachesGraphenFormat);
    liste.sucheKürzestenPfad(startKnotenName);
    matrix = new DijkstraAdjazenzMatrix(einfachesGraphenFormat);
    matrix.sucheKürzestenPfad(startKnotenName);
  }

  public void vergleicheEntfernung(String knotenName, int erwartet) {
    assertEquals(erwartet, liste.gibEntfernung(knotenName));
    assertEquals(erwartet, matrix.gibEntfernung(knotenName));
  }
}

public class VergleichDijkstraTest {

  @Test
  public void dreiKnoten() {
    VergleichDijkstra v = new VergleichDijkstra("a--b: 2; a--c: 3", "a");
    v.vergleicheEntfernung("a", 0);
    v.vergleicheEntfernung("b", 2);
    v.vergleicheEntfernung("c", 3);
  }

  @Test
  public void fünfKnoten() {
    VergleichDijkstra v = new VergleichDijkstra("A->B:100; A->D:50; B->C:100; B->E:250; C->E:50; D->B:100; D->E:250;",
        "A");
    v.vergleicheEntfernung("A", 0);
    v.vergleicheEntfernung("B", 100);
    v.vergleicheEntfernung("C", 200);
    v.vergleicheEntfernung("D", 50);
    v.vergleicheEntfernung("E", 250);
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
