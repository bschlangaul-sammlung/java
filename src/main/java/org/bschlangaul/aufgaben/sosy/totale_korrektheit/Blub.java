package org.bschlangaul.aufgaben.sosy.totale_korrektheit;

class Blub {
  int blub(int a) {
    // R:
    if (a > 15) {
      a = a - 42;
    } else {
      a = -a;
    }
    return a;
  }

  public static void main(String[] args) {
    Blub b = new Blub();
    for (int i = -100; i < 100; i++) {
      System.out.println(i + ": " + b.blub(i));
    }
  }
}
