package org.bschlangaul.baum;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MaxHaldeTest {

  public static Comparable[] feld(Comparable... schlüssel) {
    return schlüssel;
  }

  private void vergleicheHaldenFeld(Comparable[] eingefügt, Comparable[] erwartet) {
    Halde halde = new MaxHalde<Integer>();
    for (int i = 0; i < eingefügt.length; i++) {
      halde.fügeEin(eingefügt[i]);
    }
    assertArrayEquals(erwartet, halde.gibHaldenFeld());
  }

  @Test
  public void methodeGibHaldenFeld() {
    vergleicheHaldenFeld(feld(1, 2, 3, 4, 5), feld(5, 4, 2, 1, 3));
    vergleicheHaldenFeld(feld(5, 4, 3, 2, 1), feld(5, 4, 3, 2, 1));
    vergleicheHaldenFeld(feld(9, 5, 7, 1, 6, 4, 8, 2, 3), feld(9, 6, 8, 3, 5, 4, 7, 1, 2));
  }

}
