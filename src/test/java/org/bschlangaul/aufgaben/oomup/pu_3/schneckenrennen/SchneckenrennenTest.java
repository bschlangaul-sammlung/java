package org.bschlangaul.aufgaben.oomup.pu_3.schneckenrennen;

import org.junit.Before;
import org.junit.Test;

public class SchneckenrennenTest {
  private Schneckenrennen schnecke1;
  private Rennschnecke rennschn1;
  private Rennschnecke rennschn2;
  private Rennschnecke rennschn3;

  /**
   * Konstruktor fuer die Test-Klasse SchneckenrennenTest
   */
  public SchneckenrennenTest() {
  }

  /**
   * Setzt das Testgerüst fuer den Test.
   *
   * Wird vor jeder Testfall-Methode aufgerufen.
   */
  @Before
  public void setUp() {
    schnecke1 = new Schneckenrennen();
    rennschn1 = new Rennschnecke("a", "b", 1);
    rennschn2 = new Rennschnecke("b", "b", 2);
    rennschn3 = new Rennschnecke("c", "c", 3);
  }

  public void renntestGlobal() {
    schnecke1.rennen();
    rennschn1.kriechen();
    rennschn2.kriechen();
    rennschn3.kriechen();
  }

  @Test
  public void renntest() {
    Rennschnecke rennschn1 = new Rennschnecke("Anna", "Apfelschnecke", 4);
    Rennschnecke rennschn2 = new Rennschnecke("Bert", "Waldschnecke", 3);
    rennschn1.kriechen();
    rennschn2.kriechen();
  }
}
