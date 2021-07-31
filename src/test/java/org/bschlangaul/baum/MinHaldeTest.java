package org.bschlangaul.baum;
//https://www.studocu.com/de/document/fom-hochschule-fur-oekonomie-und-management/algorithmen-datenstrukturen/ubung-losung-algo-heapsort/3191573

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MinHaldeTest {
  public static Comparable[] feld(Comparable... schlüssel) {
    return schlüssel;
  }

  private void vergleicheHaldenFeld(Comparable[] eingefügt, Comparable[] erwartet) {
    MinHalde halde = new MinHalde<Integer>();
    for (int i = 0; i < eingefügt.length; i++) {
      halde.fügeEin(eingefügt[i]);
    }
    assertArrayEquals(erwartet, halde.gibHaldenFeld());
  }

  @Test
  public void aufsteigendFünfZahlen() {
    vergleicheHaldenFeld(feld(1, 2, 3, 4, 5), feld(1, 2, 3, 4, 5));
  }

  @Test
  public void absteigendFünfZahlen() {
    vergleicheHaldenFeld(feld(5, 4, 3, 2, 1), feld(1, 2, 4, 5, 3));
  }

  @Test
  public void achtZahlen1() {
    vergleicheHaldenFeld(feld(9, 5, 7, 1, 6, 4, 8, 2, 3), feld(1, 2, 4, 3, 6, 7, 8, 9, 5));
  }

  @Test
  public void achtZahlen2() {
    vergleicheHaldenFeld(feld(11, 1, 2, 13, 9, 10, 7, 5), feld(1, 5, 2, 9, 11, 10, 7, 13));
  }
}
