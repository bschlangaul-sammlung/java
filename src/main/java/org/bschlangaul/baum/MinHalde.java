package org.bschlangaul.baum;

/**
 * Feld-Implementation einer minimalen Halde (nach
 * <a href="https://codegym.cc/groups/posts/min-heap-in-java">codegym.cc</a>)
 */
public class MinHalde extends Halde {

  public MinHalde(int kapazität) {
    super(HaldenTyp.MIN, kapazität);
  }

}
