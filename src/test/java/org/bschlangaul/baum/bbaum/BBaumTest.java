package org.bschlangaul.baum.bbaum;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BBaumTest {

  @Test
  public void testeEinfügen() {
    BBaum baum = new BBaum(2);
    baum.fügeEin(1);
    baum.fügeEin(2);
    baum.fügeEin(3);
    baum.fügeEin(4);
    baum.fügeEin(5);
    baum.fügeEin(6);
    baum.fügeEin(7);
    baum.fügeEin(8);

    Comparable schlüssel = baum.find(8);
    assertEquals(schlüssel, 8);
  }

  @Test
  public void gibWurzel() {
    BBaum baum = new BBaum(2);
    baum.fügeEin(1);
    BBaum.Knoten wurzel = baum.gibWurzel();
    assertEquals(wurzel.gibAnzahlSchlüssel(), 1);
    assertEquals(wurzel.gibSchlüssel(0), 1);
  }

  @Test
  public void gibZeiger() {
    BBaum baum = new BBaum(2);
    baum.fügeEin(1);
    baum.fügeEin(2);
    baum.fügeEin(3);
    baum.fügeEin(4);
    baum.fügeEin(5);
    baum.fügeEin(6);

    BBaum.Knoten wurzel = baum.gibWurzel();
    Vector<BBaum.Knoten> zeiger = wurzel.gibZeiger();
    assertEquals(zeiger.size(), 2);
  }

}
