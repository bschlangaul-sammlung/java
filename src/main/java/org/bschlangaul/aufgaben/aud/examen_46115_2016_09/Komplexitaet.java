package org.bschlangaul.aufgaben.aud.examen_46115_2016_09;

public class Komplexitaet {

  int matrixSumme(int n, int[][] feld) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sum += feld[i][j];
      }
    }
    return sum;
  }

  int find(int key, int[][] keys) {
    int a = 0, o = keys.length;
    while (o - a > 1) {
      int m = (a + o) / 2;
      if (keys[m][0] > key)
        o = m;
      else
        a = m;
    }
    return a;
  }

}
