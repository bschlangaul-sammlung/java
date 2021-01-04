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
    BinaererSuchBaum baum = new BinaererSuchBaum();
    assertEquals(null, baum.kopf.gibSchlüssel());
  }

  @Test
  public void testeMethodeGibKopf() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1);
    assertEquals(1, baum.gibKopf().gibSchlüssel());
  }

  @Test
  public void testeMethodeFügeEin() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    assertEquals(true, baum.fügeEin(1));
    assertEquals(true, baum.fügeEin(2));
    assertEquals(true, baum.fügeEin(3));
    assertEquals(false, baum.fügeEin(3));
  }

  @Test
  public void testeMethodeFügeMehrereEin() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    assertEquals(true, baum.finde(1));
    assertEquals(true, baum.finde(2));
    assertEquals(true, baum.finde(3));
    assertEquals(false, baum.finde(4));
  }

  @Test
  public void testeMethodeFügeEinString() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin("a", "b", "c");
    BaumKnoten kopf = baum.gibKopf();
    assertEquals("a", kopf.schlüssel);
    assertEquals("b", kopf.rechts.schlüssel);
    assertEquals("c", kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void testeMethodeFügeEinInt() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    BaumKnoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(2, kopf.rechts.schlüssel);
    assertEquals(3, kopf.rechts.rechts.schlüssel);
  }

  @Test
  public void testeMethodeEntferne() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    assertEquals(false, baum.entferne(1));
    assertEquals(true, baum.fügeEin(1));
    assertEquals(true, baum.entferne(1));
    assertEquals(false, baum.entferne(1));
  }

  @Test
  public void testeMethodeEntferneWurzel() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(1);
    BaumKnoten kopf = baum.gibKopf();
    assertEquals(2, kopf.schlüssel);
    assertEquals(3, kopf.rechts.schlüssel);
  }

  @Test
  public void testeMethodeEntferneBlatt() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(3);
    BaumKnoten kopf = baum.gibKopf();
    assertEquals(1, kopf.schlüssel);
    assertEquals(2, kopf.rechts.schlüssel);
  }

  @Test
  public void testeMethodeEntferneMitte() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(1, 2, 3);
    baum.entferne(2);
    BaumKnoten kopf = baum.gibKopf();
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
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.INORDER);
    assertTrue(schlüssel.equals(generiereListe(1, 2, 4, 5, 7, 8)));
  }

  @Test
  public void testeMethodeBesuchePreorder() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.PREORDER);
    assertTrue(schlüssel.equals(generiereListe(5, 2, 1, 4, 7, 8)));
  }

  @Test
  public void testeMethodeBesuchePostorder() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.POSTORDER);
    assertTrue(schlüssel.equals(generiereListe(1, 4, 2, 8, 7, 5)));
  }

  @Test
  public void testeMethodeBesucheLevelorder() {
    BinaererSuchBaum baum = new BinaererSuchBaum();
    baum.fügeEin(5, 2, 7, 4, 8, 1);
    ArrayList<Comparable> schlüssel = baum.traversiere(BinaererSuchBaum.LEVELORDER);
    assertTrue(schlüssel.equals(generiereListe(5, 2, 7, 1, 4, 8)));
  }

}
