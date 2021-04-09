package org.bschlangaul.examen.examen_66115.jahr_2020.herbst.counter;

public class CountUp {

  public static int countup(int n, int m) {
    int x = n;
    int y = 0;
    while (y < m) {
      System.out.println(String.format("%s %s %s %s", n, m, x, y));
      x = x - 1;
      y = y + 1;
    }
    return x;
  }

  public static void main(String[] args) {
    System.out.println(countup(1000000, 100));
  }
}
