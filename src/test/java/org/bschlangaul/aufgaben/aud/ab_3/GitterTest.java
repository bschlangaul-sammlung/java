package org.bschlangaul.aufgaben.aud.ab_3;

import static org.junit.Assert.*;
import org.junit.Test;

public class GitterTest {
  @Test
  public void zweiMailZwei() {
    Gitter gitter = new Gitter(2, 2);
    assertEquals(6, gitter.berechneAnzahlWege());
  }

  @Test
  public void zehnMalZwanzig() {
    Gitter gitter = new Gitter(10, 20);
    assertEquals(30045015, gitter.berechneAnzahlWege());
  }
}
