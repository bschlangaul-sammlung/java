package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UngeradeGeradeTest {

  private void assertSucheUnGerade(int[] feld, int suche, boolean ergebnis) {
    assertEquals(ergebnis, UngeradeGerade.sucheBinärUngeradeGerade(feld, suche));
  }

  @Test
  public void assertSucheUnGerade() {
    int[] feld = new int[] { 1, 3, 5, 7, 9, 10, 8, 6, 4, 2 };
    assertSucheUnGerade(feld, 4, true);
    assertSucheUnGerade(feld, 11, false);
    assertSucheUnGerade(feld, 0, false);
    assertSucheUnGerade(feld, 3, true);
  }

  private void assertLetzteUngerade(int[] feld, int index) {
    assertEquals(index, UngeradeGerade.sucheBinärLetzteUngerade(feld));
  }

  @Test
  public void letzteUngerade() {
    assertLetzteUngerade(new int[] { 1, 3, 5, 7, 9, 10, 8, 6, 4, 2 }, 4);
    assertLetzteUngerade(new int[] { 1, 3, 5, 7, 9 }, 4);
    assertLetzteUngerade(new int[] { 10, 8, 6, 4, 2 }, -1);
    assertLetzteUngerade(new int[] { 1, 10, 8, 6, 4, 2 }, 0);
    assertLetzteUngerade(new int[] { 1, 49, 10, 8, 6, 4, 2 }, 1);
    assertLetzteUngerade(new int[] { }, -1);
  }

}
