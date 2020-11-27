package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdjazenzMatrixTest {

  @Test
  public void testeMethodeFügeKnotenEin() {
    AdjazenzMatrix matrix = new AdjazenzMatrix(5);
    matrix.fügeKnotenEin("A");
    assertEquals(1, matrix.gibKnotenAnzahl());
    matrix.fügeKnotenEin("B");
    assertEquals(2, matrix.gibKnotenAnzahl());
    matrix.fügeKnotenEin("C");
    assertEquals(3, matrix.gibKnotenAnzahl());
  }

  @Test
  public void fügeKnotenUndKantenEin() {
    AdjazenzMatrix matrix = new AdjazenzMatrix(5);
    matrix.fügeKnotenUndKantenEin("A-B A-C");
    assertEquals(3, matrix.gibKnotenAnzahl());
  }

}
