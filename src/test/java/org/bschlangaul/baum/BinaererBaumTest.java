package org.bschlangaul.baum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

@SuppressWarnings("rawtypes")
public class BinaererBaumTest {

  @Test
  public void testeKonstruktor() {
    BinaerBaum baum = new BinaerBaum();
    assertEquals(null, baum.kopf.gibSchlüssel());
    assertEquals(null, baum.nullKnoten.gibSchlüssel());
  }

  @Test
  public void testeMethodeGibKopf() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(1);
    assertEquals(1, baum.gibKopf().gibSchlüssel());
  }

  @Test
  public void testeMethodeFügeEin() {
    BinaerBaum baum = new BinaerBaum();
    assertEquals(true, baum.fügeEin(1));
    assertEquals(true, baum.fügeEin(2));
    assertEquals(true, baum.fügeEin(3));
    assertEquals(false, baum.fügeEin(3));
  }

  @Test
  public void testeMethodeFügeMehrereEin() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(1, 2, 3);
    assertEquals(true, baum.finde(1));
    assertEquals(true, baum.finde(2));
    assertEquals(true, baum.finde(3));
    assertEquals(false, baum.finde(4));
  }

  @Test
  public void testeMethodeFügeEinString() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin("a", "b", "c");
    Knoten kopf = baum.gibKopf();
    assertEquals("a", kopf.schlüssel);
    assertEquals("b", kopf.rechts.schlüssel);
    assertEquals("c", kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void testeMethodeFügeEinInt() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(1, 2, 3);
    Knoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(2, kopf.rechts.schlüssel);
    assertEquals(3, kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void testeMethodeEntferne() {
    BinaerBaum baum = new BinaerBaum();
    assertEquals(false, baum.entferne(1));
    assertEquals(true, baum.fügeEin(1));
    assertEquals(true, baum.entferne(1));
    assertEquals(false, baum.entferne(1));
  }

  @Test
  public void testeMethodeEntferneWurzel() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(1);
    Knoten kopf = baum.gibKopf();
    assertEquals(2, kopf.schlüssel);
    assertEquals(3, kopf.rechts.schlüssel);
  }

  @Test
  public void testeMethodeEntferneBlatt() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(3);
    Knoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(2, kopf.rechts.schlüssel);
  }

  @Test
  public void testeMethodeEntferneMitte() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(2);
    Knoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(3, kopf.rechts.schlüssel);
  }

  private ArrayList<Comparable> generiereListe(Comparable... zahlen) {
    ArrayList<Comparable> erwarte = new ArrayList<>();
    Collections.addAll(erwarte, zahlen);
    return erwarte;
  }

  @Test
  public void testeMethodeBesucheInorder() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.INORDER);
    assertTrue(schlüssel.equals(generiereListe(1, 2, 4, 5, 7, 8)));
  }

  @Test
  public void testeMethodeBesuchePreorder() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.PREORDER);
    assertTrue(schlüssel.equals(generiereListe(5, 2, 1, 4, 7, 8)));
  }

  @Test
  public void testeMethodeBesuchePostorder() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.POSTORDER);
    assertTrue(schlüssel.equals(generiereListe(1, 4, 2, 8, 7, 5)));
  }

  @Test
  public void testeMethodeBesucheLevelorder() {
    BinaerBaum baum = new BinaerBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaerBaum.LEVELORDER);
    assertTrue(schlüssel.equals(generiereListe(5, 2, 7, 1, 4, 8)));
  }

}
