package org.bschlangaul.graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DijkstraAdjazenzMatrixTest {

  @Test
  public void einfachStatisch() {
    assertArrayEquals(new int[] { 0, 2, 3 }, DijkstraAdjazenzMatrix.sucheKürzestenPfad("a--b: 2; a--c: 3", "a"));
  }

  @Test
  public void einfachObjekt() {
    DijkstraAdjazenzMatrix d = new DijkstraAdjazenzMatrix("a--b: 2; a--c: 3");
    assertArrayEquals(new int[] { 0, 2, 3 }, d.sucheKürzestenPfad("a"));
    assertEquals(0, d.gibEntfernung("a"));
    assertEquals("a", d.gibVorgänger("a"));
    assertEquals(2, d.gibEntfernung("b"));
    assertEquals("a", d.gibVorgänger("b"));
    assertEquals(3, d.gibEntfernung("c"));
    assertEquals("a", d.gibVorgänger("c"));
  }

  @Test
  public void sechsKnoten() {
    DijkstraAdjazenzMatrix d = new DijkstraAdjazenzMatrix(
        "a->b: 1; a->e: 7; b->c: 3; c->d: 8; c->e: 3; e->f: 1; c->f: 6; f->c: 1; f->d: 3");

    d.sucheKürzestenPfad("a");

    assertEquals(0, d.gibEntfernung("a"));
    assertEquals(1, d.gibEntfernung("b"));
    assertEquals(4, d.gibEntfernung("c"));
    assertEquals(10, d.gibEntfernung("d"));
    assertEquals(7, d.gibEntfernung("e"));
    assertEquals(8, d.gibEntfernung("f"));
  }
}
