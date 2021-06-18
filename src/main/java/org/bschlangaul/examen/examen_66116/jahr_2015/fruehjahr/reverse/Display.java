package org.bschlangaul.examen.examen_66116.jahr_2015.fruehjahr.reverse;

public abstract class Display implements PixelPainter {
  protected HardwareMatrix hardwareMatrix;
  protected int lastPaintedX;
  protected int lastPaintedY;

  public Display(HardwareMatrix hardwareMatrix) {
    this.hardwareMatrix = hardwareMatrix;

  }

  public int getWidth() {
    return hardwareMatrix.getWidth() / getWidthFactor();
  }

  public int getHeight() {
    return hardwareMatrix.getHeight() / getHeightFactor();
  }

  public void clear() {
    // some longer code
  }

  protected abstract int getWidthFactor();

  protected abstract int getHeightFactor();
}
