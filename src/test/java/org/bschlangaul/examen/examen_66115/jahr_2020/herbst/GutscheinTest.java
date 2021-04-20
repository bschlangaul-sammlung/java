package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

import static org.junit.Assert.*;
import org.junit.Test;

public class GutscheinTest {

  int[] warenWerte = new int[] { 10, 30, 40, 20, 15 };

  private void assertEingelöst(int gutscheinBetrag, int[] warenWerte) {
    assertEquals(Gutschein.gutscheinDP(gutscheinBetrag, warenWerte), true);
  }

  private void assertNichtEingelöst(int gutscheinBetrag, int[] warenWerte) {
    assertEquals(Gutschein.gutscheinDP(gutscheinBetrag, warenWerte), false);
  }

  @Test
  public void eingelöst() {
    assertEingelöst(0, warenWerte);
    assertEingelöst(10, warenWerte);
    assertEingelöst(100, warenWerte);
    assertEingelöst(115, warenWerte);
    assertEingelöst(15, warenWerte);
    assertEingelöst(20, warenWerte);
    assertEingelöst(30, warenWerte);
    assertEingelöst(40, warenWerte);
    assertEingelöst(60, warenWerte);
    assertEingelöst(70, warenWerte);
  }

  @Test
  public void nichtEingelöst() {
    assertNichtEingelöst(11, warenWerte);
    assertNichtEingelöst(31, warenWerte);
    assertNichtEingelöst(41, warenWerte);
    assertNichtEingelöst(21, warenWerte);
    assertNichtEingelöst(16, warenWerte);
    assertNichtEingelöst(999, warenWerte);

  }
}
