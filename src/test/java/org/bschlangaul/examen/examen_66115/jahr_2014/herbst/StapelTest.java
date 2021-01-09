package org.bschlangaul.examen.examen_66115.jahr_2014.herbst;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * https://www.studon.fau.de/file2860850_download.html
 */
public class StapelTest {

  @Test
  public void testeMethodenPushPop() {
    Stapel stapel = new Stapel();
    stapel.push(new Element(1));
    stapel.push(new Element(2));
    stapel.push(new Element(3));

    assertEquals(3, stapel.pop().value);
    assertEquals(2, stapel.pop().value);
    assertEquals(1, stapel.pop().value);
  }

  @Test
  public void testeMethodeMerge() {
    Stapel sa = new Stapel();
    sa.push(new Element(1));
    sa.push(new Element(3));
    sa.push(new Element(5));

    Stapel sb = new Stapel();
    sb.push(new Element(2));
    sb.push(new Element(4));

    Stapel sc = Stapel.merge(sa, sb);

    assertEquals(5, sc.pop().getValue());
    assertEquals(4, sc.pop().getValue());
    assertEquals(3, sc.pop().getValue());
    assertEquals(2, sc.pop().getValue());
    assertEquals(1, sc.pop().getValue());
  }

  @Test
  public void testeMethodeMergeMehrWerte() {
    Stapel sa = new Stapel();
    sa.push(new Element(1));
    sa.push(new Element(2));
    sa.push(new Element(4));
    sa.push(new Element(5));
    sa.push(new Element(7));
    sa.push(new Element(8));
    Stapel sb = new Stapel();
    sb.push(new Element(2));
    sb.push(new Element(3));
    sb.push(new Element(6));
    sb.push(new Element(9));
    sb.push(new Element(10));

    Stapel sc = Stapel.merge(sa, sb);

    assertEquals(10, sc.pop().getValue());
    assertEquals(9, sc.pop().getValue());
    assertEquals(8, sc.pop().getValue());
    assertEquals(7, sc.pop().getValue());
    assertEquals(6, sc.pop().getValue());
    assertEquals(5, sc.pop().getValue());
    assertEquals(4, sc.pop().getValue());
    assertEquals(3, sc.pop().getValue());
    assertEquals(2, sc.pop().getValue());
    assertEquals(2, sc.pop().getValue());
    assertEquals(1, sc.pop().getValue());
  }

  @Test
  public void testeMethodeMergeBLeer() {
    Stapel sa = new Stapel();
    sa.push(new Element(1));
    sa.push(new Element(3));
    sa.push(new Element(5));

    Stapel sb = new Stapel();

    Stapel sc = Stapel.merge(sa, sb);

    assertEquals(5, sc.pop().getValue());
    assertEquals(3, sc.pop().getValue());
    assertEquals(1, sc.pop().getValue());
  }

  @Test
  public void testeMethodeMergeALeer() {
    Stapel sa = new Stapel();

    Stapel sb = new Stapel();
    sb.push(new Element(2));
    sb.push(new Element(4));

    Stapel sc = Stapel.merge(sa, sb);

    assertEquals(4, sc.pop().getValue());
    assertEquals(2, sc.pop().getValue());
  }

}
