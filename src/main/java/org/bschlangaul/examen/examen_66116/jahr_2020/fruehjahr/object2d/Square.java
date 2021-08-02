package org.bschlangaul.examen.examen_66116.jahr_2020.fruehjahr.object2d;

public class Square extends Object2D {
  Point topLeft;
  Point bottomRight;

  public Square(int top, int left, int bottom, int right) {
    topLeft = new Point(left, top);
    bottomRight = new Point(right, bottom);
  }

  public void shift(int xShift, int yShift) {
    topLeft.shift(xShift, yShift);
    bottomRight.shift(xShift, yShift);
  }

  public void draw() {
    topLeft.draw();
    bottomRight.draw();
  }

}
