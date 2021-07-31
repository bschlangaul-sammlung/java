package org.bschlangaul.baum;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MaxHaldeTest {

  public static Comparable[] feld(Comparable... schlüssel) {
    return schlüssel;
  }

  private void vergleiche(Comparable[] eingefügt, Comparable[] erwartet) {
    Halde halde = new MaxHalde<Integer>();
    for (int i = 0; i < eingefügt.length; i++) {
      halde.fügeEin(eingefügt[i]);
    }
    assertArrayEquals(erwartet, halde.gibFeld());
  }

  @Test
  public void aufsteigendFünfZahlen() {
    vergleiche(feld(1, 2, 3, 4, 5), feld(5, 4, 2, 1, 3));
  }

  @Test
  public void absteigendFünfZahlen() {
    vergleiche(feld(5, 4, 3, 2, 1), feld(5, 4, 3, 2, 1));
  }

  @Test
  public void neunZahlen() {
    vergleiche(feld(9, 5, 7, 1, 6, 4, 8, 2, 3), feld(9, 6, 8, 3, 5, 4, 7, 1, 2));
  }

  @Test
  public void einfügen() {
    // https://www.studocu.com/de/document/fom-hochschule-fur-oekonomie-und-management/algorithmen-datenstrukturen/ubung-losung-algo-heapsort/3191573
    vergleiche(feld(5), feld(5));
    vergleiche(feld(5, 13), feld(13, 5));
    vergleiche(feld(5, 13, 2), feld(13, 5, 2));
    vergleiche(feld(5, 13, 2, 25), feld(25, 13, 2, 5));
    vergleiche(feld(5, 13, 2, 25, 7), feld(25, 13, 2, 5, 7));
    vergleiche(feld(5, 13, 2, 25, 7, 17), feld(25, 13, 17, 5, 7, 2));
    vergleiche(feld(5, 13, 2, 25, 7, 17, 20), feld(25, 13, 20, 5, 7, 2, 17));
    vergleiche(feld(5, 13, 2, 25, 7, 17, 20, 8), feld(25, 13, 20, 8, 7, 2, 17, 5));
    vergleiche(feld(5, 13, 2, 25, 7, 17, 20, 8, 4), feld(25, 13, 20, 8, 7, 2, 17, 5, 4));
  }

  @Test
  public void fügeMehrereEin() {
    MaxHalde halde = new MaxHalde<Integer>();
    halde.fügeEin(5, 13, 2, 25, 7, 17, 20, 8, 4);
    assertArrayEquals(halde.gibFeld(), feld(25, 13, 20, 8, 7, 2, 17, 5, 4));
  }

  @Test
  public void entferne() {
    MaxHalde halde = new MaxHalde<Integer>();
    halde.fügeEin(5, 13, 2, 25, 7, 17, 20, 8, 4);
    halde.entferne(13);
    assertArrayEquals(halde.gibFeld(), feld(25, 8, 20, 5, 7, 2, 17, 4));
  }
}
