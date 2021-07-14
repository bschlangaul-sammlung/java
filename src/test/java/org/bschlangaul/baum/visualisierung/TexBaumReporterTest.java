package org.bschlangaul.baum.visualisierung;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.TestHelfer;
import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.BinaerBaum;
import org.bschlangaul.baum.BinaererSuchBaum;
import org.junit.Test;

public class TexBaumReporterTest {

  BaumReporter reporter = new TexBaumReporter();

  private String lese(String dateiName) {
    return TestHelfer.leseDatei("baum/" + dateiName);
  }

  @Test
  public void binär() {
    BinaerBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3, 4, 5);
    System.out.println(reporter.erzeugeBaum(baum));
    assertEquals(lese("tex/binaer-baum.txt"), reporter.erzeugeBaum(baum) + "\n");
  }

  @Test
  public void avl() {
    BinaerBaum baum = new AVLBaum();
    baum.fügeEin(1, 2, 3, 4, 5);
    System.out.println(lese("tex/avl.txt"));
    System.out.println(reporter.erzeugeBaum(baum));

    assertEquals(lese("tex/avl.txt"), reporter.erzeugeBaum(baum) + "\n");
  }

}
