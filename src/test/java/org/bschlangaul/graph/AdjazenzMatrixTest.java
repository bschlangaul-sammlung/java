package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdjazenzMatrixTest {

  @Test
  public void methodeFügeKnotenEin() {
    AdjazenzMatrix matrix = new AdjazenzMatrix(5);
    matrix.setzeKnoten("A");
    assertEquals(1, matrix.gibKnotenAnzahl());
    matrix.setzeKnoten("B");
    assertEquals(2, matrix.gibKnotenAnzahl());
    matrix.setzeKnoten("C");
    assertEquals(3, matrix.gibKnotenAnzahl());
  }

  @Test
  public void methodeFügeKanteEinUngerichtet() {
    AdjazenzMatrix matrix = new AdjazenzMatrix(5);
    matrix.setzeKnoten("A");
    matrix.setzeKnoten("B");
    matrix.setzeUngerichteteKante("A", "B", 13);

    assertEquals(13, matrix.gibKanteGewicht("A", "B"));
    assertEquals(13, matrix.gibKanteGewicht("B", "A"));
  }

  @Test
  public void methodeFügeKanteEinGerichtet() {
    AdjazenzMatrix matrix = new AdjazenzMatrix(5);
    matrix.setzeKnoten("A");
    matrix.setzeKnoten("B");
    matrix.setzeGerichteteKante("A", "B", 13);

    assertEquals(13, matrix.gibKanteGewicht("A", "B"));
    assertEquals(-1, matrix.gibKanteGewicht("B", "A"));
  }

  @Test
  public void einfachesGraphenFormat() {
    AdjazenzMatrix matrix = new AdjazenzMatrix("a -> b: 7\na -- c: 3");
    assertEquals(3, matrix.gibKnotenAnzahl());
    assertEquals(7, matrix.gibKanteGewicht("a", "b"));
    assertEquals(3, matrix.gibKanteGewicht("a", "c"));
    assertEquals(3, matrix.gibKanteGewicht("c", "a"));
    assertEquals(-1, matrix.gibKanteGewicht("a", "x"));
  }

  @Test
  public void methodeGibMaximalesGewicht() {
    AdjazenzMatrix matrix = new AdjazenzMatrix("a -> b: 99;a -> c 3;a--d: 1");
    assertEquals(99, matrix.gibMaximalesGewicht());
  }

  @Test
  public void methodeGibMaximaleKnotennameTextbreite() {
    AdjazenzMatrix matrix = new AdjazenzMatrix("123--1234;123--12345;123--123456");
    assertEquals(6, matrix.gibMaximaleKnotennameTextbreite());
  }

  @Test
  public void methodeGibSpaltenBreite() {
    AdjazenzMatrix matrix = new AdjazenzMatrix("123--1234;123--12345;123--123456");
    assertEquals(6, matrix.gibSpaltenBreite());

    AdjazenzMatrix matrix2 = new AdjazenzMatrix("a--b:123; a--c:12; a--d:1");
    assertEquals(3, matrix2.gibSpaltenBreite());
  }

}
