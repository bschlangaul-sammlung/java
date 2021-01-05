package org.bschlangaul.baum;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

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

  @Test
  public void methodeGibWurzel() {
    AVLBaum baum = new AVLBaum();

    baum.fügeEin(1);
    assertEquals(1, baum.gibKopf().schlüssel);

    baum.fügeEin(2);
    assertEquals(1, baum.gibKopf().schlüssel);

    baum.fügeEin(3);
    assertEquals(2, baum.gibKopf().schlüssel);

    baum.entferne(3);
    assertEquals(2, baum.gibKopf().schlüssel);

    baum.entferne(2);
    assertEquals(1, baum.gibKopf().schlüssel);
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
