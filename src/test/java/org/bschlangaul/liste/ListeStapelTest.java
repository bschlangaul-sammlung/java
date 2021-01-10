package org.bschlangaul.liste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Nutzung des Datentyps Stapel.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 317/318), heißt im
 * Original „StackExample“.
 */
public class ListeStapelTest {
  @Test
  public void methodeEntnimm() throws StapelFehler {
    ListeStapel stapel = new ListeStapel();

    // Elemente auf Stack ablegen
    stapel.fügeHinzu("Eins");
    stapel.fügeHinzu("Zwei");
    stapel.fügeHinzu("Drei");

    assertEquals("Drei", (String) stapel.entnimm());
    assertEquals("Zwei", (String) stapel.entnimm());
    assertEquals("Eins", (String) stapel.entnimm());
  }

  @Test
  public void istLeer() throws StapelFehler {
    ListeStapel stapel = new ListeStapel();
    stapel.fügeHinzu(Integer.valueOf(1));
    stapel.fügeHinzu(Integer.valueOf(2));
    stapel.fügeHinzu(Integer.valueOf(3));
    assertEquals(false, stapel.istLeer());
    while (!stapel.istLeer()) {
      stapel.entnimm();
    }
    assertEquals(true, stapel.istLeer());
  }
}
