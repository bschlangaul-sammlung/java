package org.bschlangaul.examen.examen_66116.jahr_2015.fruehjahr.reverse;

import java.awt.Color;

@SuppressWarnings({ "unused" })
public class DisplayUnion extends RGBDisplay {
  public static final int MAX_DISPLAY_COUNT = 50;

  private int currentDisplayCount;

  private Display[] displays;

  public DisplayUnion(Display[] displays) {
    super(null);
  }

  public int getDisplayCount() {
    return 0;
  }

  protected int getWidthFactor() {
    return 1;
  }

  protected int getHeightFactor() {
    return 1;
  }

  public void set(int x, int y, Color color) {
  }
}
