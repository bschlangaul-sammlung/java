package org.bschlangaul.aufgaben.aud.induktion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaurolicusTest {

  private void teste(int n, int erwartet) {
    assertEquals(Maurolicus.oddSum(n), erwartet);
  }

  @Test
  public void teste() {
    teste(0, 0);
    teste(1, 1);
    teste(2, 4);
    teste(3, 9);
    teste(4, 16);
    teste(5, 25);
    teste(6, 36);
    teste(7, 49);
    teste(8, 64);
    teste(9, 81);
    teste(10, 100);
    teste(11, 121);
  }

}
