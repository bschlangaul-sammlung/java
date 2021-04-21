package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UngeradeGeradeTest {

  private void assertSucheUnGerade(int[] feld, int suche, boolean ergebnis) {
    assertEquals(ergebnis, UngeradeGerade.suche(feld, suche));
  }

  @Test
  public void assertSucheUnGerade() {
    int[] feld = new int[] { 1, 3, 5, 7, 9, 10, 8, 6, 4, 2 };
    assertSucheUnGerade(feld, 4, true);
    assertSucheUnGerade(feld, 11, false);
    assertSucheUnGerade(feld, 0, false);
    assertSucheUnGerade(feld, 3, true);
  }

}
