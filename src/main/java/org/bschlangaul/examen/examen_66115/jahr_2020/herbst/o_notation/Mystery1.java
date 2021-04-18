package org.bschlangaul.examen.examen_66115.jahr_2020.herbst.o_notation;

public class Mystery1 {
  int mystery(int n) {
    int a = 0, b = 0;
    int i = 0;
    while (i < n) {
      a = b + i;
      b = a;
      i = i + 1;
    }
    return a;
  }
}
