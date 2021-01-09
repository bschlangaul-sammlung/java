package org.bschlangaul.examen.examen_46115.jahr_2016.herbst;

public class DynamischeProgrammierung {
  public long a(int n, int m) {
    if (m == 0) {
      return n + (n / 2);
    } else if (n == 0 && m != 0) {
      return a(1, m - 1);
    } else {
      return a(n + ((int) Math.sqrt(a(n - 1, m))), m - 1);
    }
  }

  long[][] tmp = new long[100001][26];
  // mit For - Schleife durchiterieren und anfangs mit -1 fuellen

  public long aDp(int n, int m) {
    if (n <= 100000 && m <= 25 && tmp[n][m] != -1) {
      return tmp[n][m];
    } else {
      long merker;
      if (m == 0) {
        merker = n + (n / 2);
      } else if (n == 0 && m != 0) {
        merker = a(1, m - 1);
      } else {
        merker = a(n + ((int) Math.sqrt(a(n - 1, m))), m - 1);
      }
      if (n <= 100000 && m <= 25) {
        tmp[n][m] = merker;
      }
      return merker;
    }
  }
}
