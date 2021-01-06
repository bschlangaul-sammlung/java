package org.bschlangaul.baum;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

@SuppressWarnings("rawtypes")
public class BinaererSuchBaumTest {

  @Test
  public void konstruktor() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    assertEquals(null, baum.kopf.gibSchlüssel());
  }

  @Test
  public void methodeGibKopf() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1);
    assertEquals(1, baum.gibKopf().gibSchlüssel());

    baum.fügeEin(2);
    assertEquals(1, baum.gibKopf().gibSchlüssel());

    baum.fügeEin(3);
    assertEquals(1, baum.gibKopf().gibSchlüssel());

    baum.entferne(1);
    assertEquals(2, baum.gibKopf().gibSchlüssel());

    baum.entferne(2);
    assertEquals(3, baum.gibKopf().gibSchlüssel());
  }

  @Test
  public void methodeFügeEin() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    assertEquals(true, baum.fügeEin(1));
    assertEquals(true, baum.fügeEin(2));
    assertEquals(true, baum.fügeEin(3));
    assertEquals(false, baum.fügeEin(3));
  }

  @Test
  public void methodeFügeMehrereEin() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    assertEquals(true, baum.finde(1));
    assertEquals(true, baum.finde(2));
    assertEquals(true, baum.finde(3));
    assertEquals(false, baum.finde(4));
  }

  @Test
  public void methodeFügeEinString() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin("a", "b", "c");
    BaumKnoten kopf = baum.gibKopf();
    assertEquals("a", kopf.schlüssel);
    assertEquals("b", kopf.rechts.schlüssel);
    assertEquals("c", kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void methodeFügeEinInt() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    BaumKnoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(2, kopf.rechts.schlüssel);
    assertEquals(3, kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void methodeEntferne() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    assertEquals(false, baum.entferne(1));
    assertEquals(true, baum.fügeEin(1));
    assertEquals(true, baum.entferne(1));
    assertEquals(false, baum.entferne(1));
  }

  @Test
  public void entferneWurzel() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(1);
    BaumKnoten kopf = baum.gibKopf();
    assertEquals(2, kopf.schlüssel);
    assertEquals(3, kopf.rechts.schlüssel);
  }

  @Test
  public void entferneBlatt() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(3);
    BaumKnoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(2, kopf.rechts.schlüssel);
  }

  @Test
  public void entferneMitte() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(2);
    BaumKnoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(3, kopf.rechts.schlüssel);
  }

  @Test
  public void traversiereInorder() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.INORDER);
    assertEquals(BaumTestHelfer.macheListe(1, 2, 4, 5, 7, 8), schlüssel);
  }

  @Test
  public void traversierePreorder() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.PREORDER);
    assertEquals(BaumTestHelfer.macheListe(5, 2, 1, 4, 7, 8), schlüssel);
  }

  @Test
  public void traversierePostorder() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.POSTORDER);
    assertEquals(BaumTestHelfer.macheListe(1, 4, 2, 8, 7, 5), schlüssel);
  }

  @Test
  public void traversiereLevelorder() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.LEVELORDER);
    assertEquals(BaumTestHelfer.macheListe(5, 2, 7, 1, 4, 8), schlüssel);
  }

}
