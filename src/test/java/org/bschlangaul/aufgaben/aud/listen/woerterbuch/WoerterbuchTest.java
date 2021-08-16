package org.bschlangaul.aufgaben.aud.listen.woerterbuch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WoerterbuchTest {

  @Test
  public void methodeÜbersetze() {
    Woerterbuch wörterbuch = new Woerterbuch();
    assertEquals("Noch keine Wörter im Wörterbuch.", wörterbuch.übersetze("Wassermelone"));
  }


  @Test
  public void methodeEinfügen() {
    Woerterbuch wörterbuch = new Woerterbuch();
    wörterbuch.einfügen("Wassermelone", "Watermelon");
    assertEquals("Watermelon", wörterbuch.übersetze("Wassermelone"));
  }

  @Test
  public void sortierung() {
    Woerterbuch wörterbuch = new Woerterbuch();
    wörterbuch.einfügen("Wassermelone", "Watermelon");
    wörterbuch.einfügen("Apfel", "Apple");
    wörterbuch.einfügen("Zitrone", "Lemon");
    wörterbuch.einfügen("Birne", "Pear");
    wörterbuch.einfügen("Klementine", "Clementine");

    WortPaar paar;
    paar = wörterbuch.gibNächstes();
    assertEquals("Apfel", paar.gibDeutschesWort());

    paar = paar.gibNächstes();
    assertEquals("Birne", paar.gibDeutschesWort());

    paar = paar.gibNächstes();
    assertEquals("Klementine", paar.gibDeutschesWort());

    paar = paar.gibNächstes();
    assertEquals("Wassermelone", paar.gibDeutschesWort());

    paar = paar.gibNächstes();
    assertEquals("Zitrone", paar.gibDeutschesWort());
  }
}
