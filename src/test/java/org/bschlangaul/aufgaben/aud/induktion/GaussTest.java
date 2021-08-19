package org.bschlangaul.aufgaben.aud.induktion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GaussTest {

  private void teste(int n, int erwartet) {
    assertEquals(Gauss.sum(n), erwartet);
  }

  @Test
  public void teste() {
    teste(0, 0);
    teste(1, 1);
    teste(2, 3);
    teste(3, 6);
    teste(4, 10);
    teste(5, 15);
    teste(6, 21);
    teste(7, 28);
    teste(8, 36);
    teste(9, 45);
    teste(10, 55);
    teste(11, 66);
  }

}
