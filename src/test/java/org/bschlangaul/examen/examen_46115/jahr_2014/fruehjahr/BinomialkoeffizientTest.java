package org.bschlangaul.examen.examen_46115.jahr_2014.fruehjahr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinomialkoeffizientTest {

  public void testeRek(int n, int k, int ergebnis) {
    assertEquals(ergebnis, Binomialkoeffizient.binIt(n, k));
  }

  public void testeIt(int n, int k, int ergebnis) {
    assertEquals(ergebnis, Binomialkoeffizient.binIt(n, k));
  }

  public void teste(int n, int k, int ergebnis) {
    testeRek(n, k, ergebnis);
    testeIt(n, k, ergebnis);
  }

  @Test
  public void teste() {
    teste(0, 0, 1);

    teste(1, 0, 1);
    teste(1, 1, 1);

    teste(2, 0, 1);
    teste(2, 1, 2);
    teste(2, 2, 1);

    teste(3, 0, 1);
    teste(3, 1, 3);
    teste(3, 2, 3);
    teste(3, 3, 1);

    teste(4, 0, 1);
    teste(4, 1, 4);
    teste(4, 2, 6);
    teste(4, 3, 4);
    teste(4, 4, 1);
  }
}
