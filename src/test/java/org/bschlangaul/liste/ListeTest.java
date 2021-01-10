package org.bschlangaul.liste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ListeTest {
  @Test
  public void main() {
    Liste liste = new Liste();
    liste.setzeErstes(1);
    liste.setzeErstes(2);
    liste.setzeErstes(3);
    assertEquals(3, liste.gibAnzahl());
  }
}
