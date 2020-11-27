package org.bschlangaul.aufgaben.aud.pu_7.aufgabe_8;

public class Hashing {
  public static int hashen(int x) {
    return (x % 8);
  }

  public static int sondieren(int x, int i) {
    int ergebnis = (hashen(x) + i * 5) % 8;
    System.out.println(String.format("h(%s,%s) = %s", x, i, ergebnis));
    return ergebnis;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      sondieren(17, i);
    }

    for (int i = 0; i < 7; i++) {
      sondieren(4, i);
    }
  }
}
