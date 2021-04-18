package org.bschlangaul.examen.examen_66115.jahr_2020.herbst.o_notation;

@SuppressWarnings("unused")
public class Mystery2 {
  int mystery(int n) {
    int r = 0;
    while (n > 0) {
      int y = n;
      int x = n;
      for (int i = 0; i < y; i++) {
        for (int j = 0; j < i; j++) {
          r = r + 1;
        }
        r = r - 1;
      }
      n = n - 1;
    }
    return r;
  }

  static int mysteryToCount(int n) {
    int r = 0;
    int counter = 0;
    int whileCounter = 0;
    int for1Counter = 0;
    int for2Counter = 0;
    while (n > 0) {
      whileCounter++;
      int y = n;
      int x = n;
      System.out.println("while");
      for (int i = 0; i < y; i++) {
        System.out.println("  for1");
        for1Counter++;
        for (int j = 0; j < i; j++) {
          System.out.println("    for2");
          for2Counter++;
          counter++;
        }
        r = r - 1;
      }
      n = n - 1;
    }
    System.out.println(whileCounter);
    System.out.println(for1Counter);
    System.out.println(for2Counter);
    return counter;
  }

  public static void main(String[] args) {
    System.out.println(mysteryToCount(3));
  }
}
