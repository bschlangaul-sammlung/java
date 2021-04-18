package org.bschlangaul.examen.examen_66115.jahr_2020.herbst.counter;

public class CountDown {

  public static int countdown(int n, int m) {
    int x = n;
    int y = 0;
    while (n > 0) {
      System.out.println(String.format("%s %s %s %s", n, m, x, y));
      if (y < m) {
        x = x - 1;
        y = y + 1;
      } else {
        y = 0;
        n = n / 2; /* Ganzzahldivision */
      }
    }
    return x;
  }

  public static void main(String[] args) {
    System.out.println(countdown(3, 2));
  }

}
