package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr;

class Rectangle {
  private int width;
  private int length;

  Rectangle(int w, int l) {
    width = w;
    length = l;
  }

  public int getWidth() {
    return this.width;
  }

  public int getLength() {
    return this.length;
  }
}

class RectangleDemo {
  public static void main(String args[]) {
    Rectangle rectanglel = new Rectangle(10, 20);
    Rectangle rectangle2 = new Rectangle(3, 90);
    Rectangle example = new Rectangle(1, 2);
    int area;

    // Compute area of first box
    area = rectanglel.getWidth() * rectanglel.getLength();
    System.out.println("Area is " + area);

    // Compute area for second box
    area = rectangle2.getWidth() * rectangle2.getLength();
    System.out.println("Area is " + area);

    // Compute area for third box
    area = example.getWidth() * example.getLength();
    System.out.println("Area is " + area);
  }
}
