package org.bschlangaul.aufgaben.aud.ab_1;

public class Rekursion {

  public int function(int b, int e) {
    if (e == 1) {
      return b * 1;
    } else {
      e = e - 1;
      return b * function(b, e);
    }
  }

  public static void main(String[] args) {
    Rekursion r = new Rekursion();
    System.out.println(r.function(2, 1));
    System.out.println(r.function(2, 2));
    System.out.println(r.function(2, 3));
    System.out.println(r.function(2, 4));
  }

}
