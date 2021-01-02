package org.bschlangaul.baum;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AVLBaumTest {
  public void testeKonstruktor() {
    AVLBaum baum = new AVLBaum();
    baum.fügeEin(1);
  }

  @Test
  public void testeEinfügen() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(1);
    baum.fügeEin(2);
    baum.fügeEin(3);
    baum.fügeEin(4);
    baum.fügeEin(5);

    AVLBaumKnoten kopf = baum.gibKopf();

    assertEquals(2, kopf.schlüssel);
    assertEquals(1, kopf.links.schlüssel);
    assertEquals(4, kopf.rechts.schlüssel);
    assertEquals(3, kopf.rechts.links.schlüssel);
    assertEquals(5, kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void testeEinfügen2() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    AVLBaumKnoten kopf = baum.gibKopf();

    assertEquals(19, kopf.schlüssel);
    assertEquals(1, kopf.links.schlüssel);
    assertEquals(21, kopf.rechts.schlüssel);
    assertEquals(6, kopf.links.rechts.schlüssel);
    assertEquals(41, kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void testeEinfügenBuchstaben() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin("a", "b", "c", "d", "e", "f");

    AVLBaumKnoten kopf = baum.gibKopf();

    assertEquals("d", kopf.schlüssel);
    assertEquals("b", kopf.links.schlüssel);
    assertEquals("e", kopf.rechts.schlüssel);
    assertEquals("a", kopf.links.links.schlüssel);
    assertEquals("c", kopf.links.rechts.schlüssel);
    assertEquals("f", kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void testeLöschen() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(21);
    AVLBaumKnoten root = baum.gibKopf();

    assertEquals(19, root.schlüssel);
    assertEquals(1, root.links.schlüssel);
    assertEquals(41, root.rechts.schlüssel);
    assertEquals(6, root.links.rechts.schlüssel);
  }

  @Test
  public void testeLöschenWurzel() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(19);
    AVLBaumKnoten kopf = baum.gibKopf();
    // https://visualgo.net/en/bst
    assertEquals(21, kopf.schlüssel);
    assertEquals(1, kopf.links.schlüssel);
    assertEquals(41, kopf.rechts.schlüssel);
    assertEquals(6, kopf.links.rechts.schlüssel);
  }

  @Test
  public void testeLöschenWurzelRechts() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(19, "rechts");
    AVLBaumKnoten kopf = baum.gibKopf();
    // https://visualgo.net/en/bst
    assertEquals(21, kopf.schlüssel);
    assertEquals(1, kopf.links.schlüssel);
    assertEquals(41, kopf.rechts.schlüssel);
    assertEquals(6, kopf.links.rechts.schlüssel);
  }

  @Test
  public void testeLöschenWurzelLinks() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(19, "links");
    AVLBaumKnoten kopf = baum.gibKopf();
    // https://www.cs.usfca.edu/~galles/visualization/AVLbaum.html
    assertEquals(6, kopf.schlüssel);
    assertEquals(1, kopf.links.schlüssel);
    assertEquals(21, kopf.rechts.schlüssel);
    assertEquals(41, kopf.rechts.rechts.schlüssel);
  }
}
