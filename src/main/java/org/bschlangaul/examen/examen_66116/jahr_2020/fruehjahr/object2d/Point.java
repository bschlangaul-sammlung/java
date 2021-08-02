package org.bschlangaul.examen.examen_66116.jahr_2020.fruehjahr.object2d;

public class Point extends Object2D {
  int xPos;
  int yPos;

  public Point(int x, int y) {
    xPos = x;
    yPos = y;
  }

  public void shift(int xShift, int yShift) {
    xPos += xShift;
    yPos += yShift;
  }

  public void draw() {
    System.out.println(String.format("xPos: %s, yPos: %s", xPos, yPos));
  }

}
