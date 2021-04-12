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

  public static void main(String[] args) {

  }
}
