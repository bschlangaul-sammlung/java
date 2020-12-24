package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KnotenSpeicherTest {

  @Test
  public void methodeSetze() {
    KnotenSpeicher k = new KnotenSpeicher();
    k.setzeKnoten("A");
    assertEquals(0, k.gibKnotenNummer("A"));
  }

  @Test
  public void mehrmalsDerGleicheName() {
    KnotenSpeicher k = new KnotenSpeicher();
    assertEquals(0, k.setzeKnoten("A"));
    assertEquals(0, k.setzeKnoten("A"));
    assertEquals(0, k.setzeKnoten("A"));
    assertEquals(1, k.gibKnotenAnzahl());
  }

  @Test
  public void methodeGibKnotenName() {
    KnotenSpeicher k = new KnotenSpeicher();
    k.setzeKnoten("A");
    k.setzeKnoten("B");
    k.setzeKnoten("C");
    assertEquals("A", k.gibKnotenName(0));
    assertEquals("B", k.gibKnotenName(1));
    assertEquals("C", k.gibKnotenName(2));
  }

  @Test
  public void methodeGibKnotenAnzahl() {
    KnotenSpeicher k = new KnotenSpeicher();
    assertEquals(0, k.gibKnotenAnzahl());

    k.setzeKnoten("A");
    assertEquals(1, k.gibKnotenAnzahl());

    k.setzeKnoten("B");
    assertEquals(2, k.gibKnotenAnzahl());

    k.setzeKnoten("C");
    assertEquals(3, k.gibKnotenAnzahl());
  }
}
