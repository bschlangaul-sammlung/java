package org.bschlangaul.examen.examen_66115.jahr_2020.fruehjahr;

enum Color {
  RED, BLUE
}

class Point {
  double x;
  Color color;

  public Point(double x, Color color) {
    this.x = x;
    this.color = color;
  }
}

public class RedBluePairCollection {
  Point[] points;
  int latestIndex;

  public RedBluePairCollection(int size) {
    points = new Point[size];
  }

  public void addPoint(double x, Color color) {
    points[latestIndex] = new Point(x, color);
    latestIndex++;
  }

  public double findMinimalDistance() {
    double distanceMin = Double.MAX_VALUE;
    for (int i = 0; i < latestIndex - 1; i++) {
      if (points[i].color != points[i + 1].color) {
        double distance = points[i + 1].x - points[i].x;
        if (distance < distanceMin) {
          distanceMin = distance;
        }
      }
    }
    return distanceMin;
  }

  public static void main(String[] args) {
    RedBluePairCollection pairs = new RedBluePairCollection(11);

    pairs.addPoint(0, Color.RED);
    pairs.addPoint(0.2, Color.RED);
    pairs.addPoint(1.5, Color.BLUE);
    pairs.addPoint(3.1, Color.RED);
    pairs.addPoint(4.0, Color.BLUE);
    pairs.addPoint(4.2, Color.BLUE);
    pairs.addPoint(5.1, Color.RED);
    pairs.addPoint(6, Color.BLUE);
    pairs.addPoint(6.1, Color.BLUE);
    pairs.addPoint(6.2, Color.BLUE);
    pairs.addPoint(7.2, Color.RED);

    System.out.println(pairs.findMinimalDistance());
  }
}
