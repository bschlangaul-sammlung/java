package org.bschlangaul.graph.algorithmen;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.TestHelfer;
import org.junit.Test;

public class MinimalerSpannbaumKruskalTest {

  private void vergleiche(int erwartet, String dateiName) {
    MinimalerSpannbaumKruskal k = new MinimalerSpannbaumKruskal(TestHelfer.leseDatei("graph/" + dateiName));
    assertEquals(erwartet, k.führeAus(), 0);
  }

  @Test
  public void graphAudFs6() {
    vergleiche(39, "aud-fs-6.txt");
  }

  @Test
  public void gateVidyalay() {
    vergleiche(99, "gate-vidyalay.txt");
  }

  @Test
  public void slideshareAdilAslam() {
    vergleiche(37, "slideshare-adil-aslam.txt");
  }


}
