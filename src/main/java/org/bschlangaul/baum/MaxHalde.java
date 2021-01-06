package org.bschlangaul.baum;

/**
 * Feld-Implementation einer maximalen Halde (nach
 * <a href="https://codegym.cc/groups/posts/min-heap-in-java">codegym.cc</a>)
 */
public class MaxHalde extends Halde {

  public MaxHalde(int kapazität) {
    super(HaldenTyp.MAX, kapazität);
  }

}
