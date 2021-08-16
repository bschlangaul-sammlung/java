package org.bschlangaul.aufgaben.aud.baum.kompositum;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinaerbaumTest {

  @Test
  public void teste(){
    Binaerbaum baum = new Binaerbaum();
    Inhalt[] inhalte = new Inhalt[16];
    Datenknoten[] datenknoten = new Datenknoten[16];
    inhalte[0] = new Inhalt("Inhalt 1");

    inhalte[1] = new Inhalt("Inhalt 2");
    inhalte[2] = new Inhalt("Inhalt 3");

    inhalte[3] = new Inhalt("Inhalt 4");
    inhalte[4] = new Inhalt("Inhalt 5");

    for (int i = 0; i < 5; i++) {
      datenknoten[i] = new Datenknoten(new Abschluss(), new Abschluss(), inhalte[i]);
    }
    baum.setzeWurzel(datenknoten[0]);

    datenknoten[0].setzteLinks(datenknoten[1]);
    datenknoten[0].setzeRechts(datenknoten[2]);

    datenknoten[1].setzteLinks(datenknoten[3]);
    datenknoten[1].setzeRechts(datenknoten[4]);

    assertEquals(5, baum.gibAnzahl());

    Inhalt inhalt = (Inhalt) baum.gibWurzel().gibLinks().gibLinks().gibInhalt();
    assertEquals("Inhalt 4", inhalt.gibInhalt());
  }

}
