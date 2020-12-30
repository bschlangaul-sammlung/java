package org.bschlangaul.graph.algorithmen;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.TestHelfer;
import org.junit.Test;

public class MinimalerSpannbaumKruskalTest {

  @Test
  public void graphAudFs6 () {
    TestHelfer.leseDatei("graph/aud-fs-6.txt");
    MinimalerSpannbaumKruskal k = new MinimalerSpannbaumKruskal(TestHelfer.leseDatei("graph/aud-fs-6.txt"));
    assertEquals(39, k.führeAus());
  }

}
