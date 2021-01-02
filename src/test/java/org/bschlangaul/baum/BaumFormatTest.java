package org.bschlangaul.baum;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.bschlangaul.TestHelfer;
import org.junit.Test;

public class BaumFormatTest {
  private BaumFormat lese(String textFormat) {
    return new BaumFormat(textFormat);
  }

  private BaumFormat lade(String dateiName) {
    return lese(TestHelfer.leseDatei("baum/" + dateiName));
  }

  @Test
  public void einfach() {
    BaumFormat format = lade("einfach.txt");

    BaumFormat.BaumArt binär = format.bäume[0];
    assertEquals("binär", binär.art);
    assertEquals("setze", binär.aktionen[0].befehl);
    assertArrayEquals(new int[] { 1, 2, 3 }, binär.aktionen[0].werte);

    BaumFormat.BaumArt avl = format.bäume[1];
    assertEquals("avl", avl.art);

    assertEquals("setze", avl.aktionen[0].befehl);

    assertArrayEquals(new int[] { 1, 2, 3, 4 }, avl.aktionen[0].werte);
    assertEquals("drucke", avl.aktionen[1].befehl);
    assertArrayEquals(new int[] {}, avl.aktionen[1].werte);

    assertEquals("lösche", avl.aktionen[2].befehl);
    assertArrayEquals(new int[] { 1, 2, 3 }, avl.aktionen[2].werte);
  }

}
