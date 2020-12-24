package org.bschlangaul.graph;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdjazenzListeTest {

  @Test
  public void methodeGibKantenGewicht() {
    AdjazenzListe liste = new AdjazenzListe(2);
    liste.setzeKante("A", "B", 42, true);
    assertEquals(42, liste.gibKanteGewicht("A", "B"));
  }


}
