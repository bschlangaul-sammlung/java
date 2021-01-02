package org.bschlangaul.baum.tex;

import static org.junit.Assert.assertEquals;

import org.bschlangaul.TestHelfer;
import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.Baum;
import org.bschlangaul.baum.BinaerBaum;
import org.junit.Test;

public class TexBaumTikzTest {

  private String lese(String dateiName) {
    return TestHelfer.leseDatei("baum/" + dateiName);
  }

  @Test
  public void binär() {
    Baum baum = new BinaerBaum();
    baum.fügeEin(1, 2, 3, 4, 5);
    System.out.println(TexBaumTikz.generiere(baum));
    assertEquals(lese("tex/binaer-baum.txt"), TexBaumTikz.generiere(baum) + "\n");
  }

  @Test
  public void avl() {
    Baum baum = new AVLBaum();
    baum.fügeEin(1, 2, 3, 4, 5);
    assertEquals(lese("tex/avl.txt"), TexBaumTikz.generiere(baum) + "\n");
  }

}
