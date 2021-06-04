package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr;

public class Box {
  double length;

  public Box(double length) {
    this.length = length;
  }

  public double size() {
    return length;
  }

  public double size(double width) {
    return this.length * width;
  }

  public double size(double num, double f) {
    return this.length - num * f;
  }

  public static void main(String[] args) {
    Box example = new Box(15);

    double mysize = example.size();
    System.out.println(mysize);

    mysize = example.size(10);
    System.out.println(mysize);

    mysize = example.size(5, 2);
    System.out.println(mysize);
  }

}
