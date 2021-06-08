package org.bschlangaul.examen.examen_66116.jahr_2015.fruehjahr.reverse;

import java.awt.Color;

public class RGBDisplay extends Display {
  public RGBDisplay(HardwareMatrix matrix) {
    super(matrix);
  }

  public void set(int x, int y, Color color) {
  }

  protected int getWidthFactor() {
    return 3;
  }

  protected int getHeightFactor() {
    return 1;
  }
}
