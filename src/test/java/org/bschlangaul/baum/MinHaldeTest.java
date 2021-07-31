package org.bschlangaul.baum;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MinHaldeTest {
  public static Comparable[] feld(Comparable... schlüssel) {
    return schlüssel;
  }

  private void vergleiche(Comparable[] eingefügt, Comparable[] erwartet) {
    MinHalde halde = new MinHalde<Integer>();
    for (int i = 0; i < eingefügt.length; i++) {
      halde.fügeEin(eingefügt[i]);
    }
    assertArrayEquals(erwartet, halde.gibFeld());
  }

  @Test
  public void aufsteigendFünfZahlen() {
    vergleiche(feld(1, 2, 3, 4, 5), feld(1, 2, 3, 4, 5));
  }

  @Test
  public void absteigendFünfZahlen() {
    vergleiche(feld(5, 4, 3, 2, 1), feld(1, 2, 4, 5, 3));
  }

  @Test
  public void achtZahlen1() {
    vergleiche(feld(9, 5, 7, 1, 6, 4, 8, 2, 3), feld(1, 2, 4, 3, 6, 7, 8, 9, 5));
  }

  @Test
  public void achtZahlen2() {
    vergleiche(feld(11, 1, 2, 13, 9, 10, 7, 5), feld(1, 5, 2, 9, 11, 10, 7, 13));
  }

  @Test
  public void examen66116jahr2015HerbstA7() {
    vergleiche(feld(7, 1, 12, 8, 10, 3, 5), feld(1, 7, 3, 8, 10, 12, 5));
  }
}
