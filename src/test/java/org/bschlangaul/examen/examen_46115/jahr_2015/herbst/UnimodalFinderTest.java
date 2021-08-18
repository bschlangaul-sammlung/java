package org.bschlangaul.examen.examen_46115.jahr_2015.herbst;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnimodalFinderTest {

  private void testeMaxItertiv(int[] feld, int max) {
    assertEquals(max, UnimodalFinder.findeMaxIterativ(feld));
  }

  private void testeMaxRekursiv(int[] feld, int max) {
    assertEquals(max, UnimodalFinder.findeMaxRekursiv(feld));
  }

  private void testeMax(int[] feld, int max) {
    testeMaxItertiv(feld, max);
    testeMaxRekursiv(feld, max);
  }

  @Test
  public void findeMax() {
    testeMax(new int[] { 1, 2, 3, 1 }, 3);
  }

  @Test
  public void findeMaxLaengeresFeld() {
    testeMax(new int[] { 1, 3, 4, 6, 7, 8, 9, 11, 6, 5, 4, 3, 2 }, 11);
  }

  @Test
  public void keinMaxAufsteigend() {
    testeMaxItertiv(new int[] { 1, 2, 3 }, UnimodalFinder.KEIN_MAX);
  }

  @Test
  public void keinMaxAbsteigend() {
    testeMaxItertiv(new int[] { 3, 2, 1 }, UnimodalFinder.KEIN_MAX);
  }

  @Test
  public void maxNegativeZahlen() {
    testeMax(new int[] { -2, -1, 3, 1 }, 3);
  }

  private void testeUnimodalität(int[] feld, boolean wahr) {
    assertEquals(wahr, UnimodalFinder.testeUnimodalität(feld));
  }

  @Test
  public void unimodalität() {
    testeUnimodalität(new int[] { 1, 2, 3, 1 }, true);
  }

  @Test
  public void unimodalitätFalsch() {
    testeUnimodalität(new int[] { 1, -2, 3, 1, 2 }, false);
    testeUnimodalität(new int[] { 1, 2, 3, 1, 2 }, false);
    testeUnimodalität(new int[] { 3, 2, 1 }, false);
  }

}
