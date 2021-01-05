package org.bschlangaul.baum;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class AVLBaumTest {
  public void konstruktor() {
    AVLBaum baum = new AVLBaum();
    baum.fügeEin(1);
  }

  @Test
  public void methodeGibKopf() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(1);
    assertEquals(1, baum.gibKopf().gibSchlüssel());

    baum.fügeEin(2);
    assertEquals(1, baum.gibKopf().gibSchlüssel());

    baum.fügeEin(3);
    assertEquals(2, baum.gibKopf().gibSchlüssel());

    baum.entferne(3);
    assertEquals(2, baum.gibKopf().gibSchlüssel());

    baum.entferne(2);
    assertEquals(1, baum.gibKopf().gibSchlüssel());
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

    assertEquals(2, kopf.gibSchlüssel());
    assertEquals(1, kopf.gibLinks().gibSchlüssel());
    assertEquals(4, kopf.gibRechts().gibSchlüssel());
    assertEquals(3, kopf.gibRechts().gibLinks().gibSchlüssel());
    assertEquals(5, kopf.gibRechts().gibRechts().gibSchlüssel());
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

    assertEquals(19, kopf.gibSchlüssel());
    assertEquals(1, kopf.gibLinks().gibSchlüssel());
    assertEquals(21, kopf.gibRechts().gibSchlüssel());
    assertEquals(6, kopf.gibLinks().gibRechts().gibSchlüssel());
    assertEquals(41, kopf.gibRechts().gibRechts().gibSchlüssel());
  }

  @Test
  public void testeEinfügenBuchstaben() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin("a", "b", "c", "d", "e", "f");

    AVLBaumKnoten kopf = baum.gibKopf();

    assertEquals("d", kopf.gibSchlüssel());
    assertEquals("b", kopf.gibLinks().gibSchlüssel());
    assertEquals("e", kopf.gibRechts().gibSchlüssel());
    assertEquals("a", kopf.gibLinks().gibLinks().gibSchlüssel());
    assertEquals("c", kopf.gibLinks().gibRechts().gibSchlüssel());
    assertEquals("f", kopf.gibRechts().gibRechts().gibSchlüssel());
  }


  @Test
  public void methodeEntferne() {
    AVLBaum baum = new AVLBaum();
    assertEquals(false, baum.entferne(1));
    assertEquals(false, baum.entferne(2));

    assertEquals(true, baum.fügeEin(1));
    assertEquals(1, baum.gibKopf().gibSchlüssel());
    assertEquals(true, baum.entferne(1));
    assertEquals(false, baum.entferne(1));
    assertEquals(null, baum.gibKopf());
    assertEquals(true, baum.fügeEin(1, 3, 4, 6, 8, 10, 34, 2));
    assertEquals(true, baum.entferne(2));
    assertEquals(true, baum.entferne(34));
    assertEquals(true, baum.entferne(1));
    assertEquals(false, baum.entferne(178));
    assertEquals(true, baum.entferne(10));
    assertEquals(true, baum.entferne(4));
    assertEquals(true, baum.entferne(8));
    assertEquals(true, baum.entferne(3));
    assertEquals(true, baum.entferne(6));
    assertEquals(false, baum.entferne(6));
  }

  @Test
  public void entfernen() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(21);
    AVLBaumKnoten root = baum.gibKopf();

    assertEquals(19, root.gibSchlüssel());
    assertEquals(1, root.gibLinks().gibSchlüssel());
    assertEquals(41, root.gibRechts().gibSchlüssel());
    assertEquals(6, root.gibLinks().gibRechts().gibSchlüssel());
  }

  @Test
  public void entferneWurzel() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(19);
    AVLBaumKnoten kopf = baum.gibKopf();
    // https://visualgo.net/en/bst
    assertEquals(21, kopf.gibSchlüssel());
    assertEquals(1, kopf.gibLinks().gibSchlüssel());
    assertEquals(41, kopf.gibRechts().gibSchlüssel());
    assertEquals(6, kopf.gibLinks().gibRechts().gibSchlüssel());
  }

  @Test
  public void entferneWurzelRechts() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(19, "rechts");
    AVLBaumKnoten kopf = baum.gibKopf();
    // https://visualgo.net/en/bst
    assertEquals(21, kopf.gibSchlüssel());
    assertEquals(1, kopf.gibLinks().gibSchlüssel());
    assertEquals(41, kopf.gibRechts().gibSchlüssel());
    assertEquals(6, kopf.gibLinks().gibRechts().gibSchlüssel());
  }

  @Test
  public void entferneWurzelLinks() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(21);
    baum.fügeEin(19);
    baum.fügeEin(1);
    baum.fügeEin(41);
    baum.fügeEin(6);

    baum.entferne(19, "links");
    AVLBaumKnoten kopf = baum.gibKopf();
    // https://www.cs.usfca.edu/~galles/visualization/AVLbaum.html
    assertEquals(6, kopf.gibSchlüssel());
    assertEquals(1, kopf.gibLinks().gibSchlüssel());
    assertEquals(21, kopf.gibRechts().gibSchlüssel());
    assertEquals(41, kopf.gibRechts().gibRechts().gibSchlüssel());
  }

  @SuppressWarnings("rawtypes")
  private ArrayList<Comparable> generiereListe(Comparable... zahlen) {
    ArrayList<Comparable> erwarte = new ArrayList<>();
    Collections.addAll(erwarte, zahlen);
    return erwarte;
  }


  @SuppressWarnings("rawtypes")
  @Test
  public void traversiereInorder() {
    AVLBaum baum = new AVLBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.INORDER);
    assertEquals(generiereListe(1, 2, 4, 5, 7, 8), schlüssel);
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void traversierePreorder() {
    AVLBaum baum = new AVLBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.PREORDER);
    assertEquals(generiereListe(5, 2, 1, 4, 7, 8), schlüssel);
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void traversierePostorder() {
    AVLBaum baum = new AVLBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.POSTORDER);
    assertEquals(generiereListe(1, 4, 2, 8, 7, 5), schlüssel);
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void traversiereLevelorder() {
    AVLBaum baum = new AVLBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.LEVELORDER);
    assertEquals(generiereListe(5, 2, 7, 1, 4, 8), schlüssel);
  }
}
