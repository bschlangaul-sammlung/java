package org.bschlangaul.aufgaben.sosy.eklausur;

class WPKalkuel {
  int blub(int a) {
    if (a > 15) {
      a = a - 42;
    } else {
      a = -a;
    }
    return a;
  }

  public static void main(String[] args) {
    WPKalkuel w = new WPKalkuel();
    for (int i = -100; i < 100; i++) {
      System.out.println(i + ": " + w.blub(i));
    }
  }
}
