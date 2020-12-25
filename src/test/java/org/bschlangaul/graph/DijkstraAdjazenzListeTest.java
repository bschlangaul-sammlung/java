package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DijkstraAdjazenzListeTest {


  @Test
  public void einfachObjekt() {
    DijkstraAdjazenzListe d = new DijkstraAdjazenzListe("a--b: 2; a--c: 3");
    d.sucheKürzestenPfad("a");
    //assertArrayEquals(new int[] { 0, 2, 3 }, d.sucheKürzestenPfad("a"));
    assertEquals(0, d.gibEntfernung("a"));
    //assertEquals("a", d.gibVorgänger("a"));
    assertEquals(2, d.gibEntfernung("b"));
    //assertEquals("a", d.gibVorgänger("b"));
    assertEquals(3, d.gibEntfernung("c"));
    //assertEquals("a", d.gibVorgänger("c"));
  }

  @Test
  public void sechsKnoten() {
    DijkstraAdjazenzListe d = new DijkstraAdjazenzListe(
        "a->b: 1; a->e: 7; b->c: 3; c->d: 8; c->e: 3; e->f: 1; c->f: 6; f->c: 1; f->d: 3");

    d.sucheKürzestenPfad("a");

    assertEquals(0, d.gibEntfernung("a"));
    assertEquals(1, d.gibEntfernung("b"));
    assertEquals(4, d.gibEntfernung("c"));
    assertEquals(11, d.gibEntfernung("d"));
    assertEquals(7, d.gibEntfernung("e"));
    assertEquals(8, d.gibEntfernung("f"));
  }
}
