package org.bschlangaul.examen.examen_46115.jahr_2016.herbst;

public class DynamischeProgrammierung {
  public static long a(int n, int m) {
    if (m == 0) {
      return n + (n / 2);
    } else if (n == 0 && m != 0) {
      return a(1, m - 1);
    } else {
      return a(n + ((int) Math.sqrt(a(n - 1, m))), m - 1);
    }
  }

  static long[][] tmp = new long[100001][26];

  public static long aDp(int n, int m) {
    if (n <= 100000 && m <= 25 && tmp[n][m] != -1) {
      return tmp[n][m];
    } else {
      long merker;
      if (m == 0) {
        merker = n + (n / 2);
      } else if (n == 0 && m != 0) {
        merker = aDp(1, m - 1);
      } else {
        merker = aDp(n + ((int) Math.sqrt(aDp(n - 1, m))), m - 1);
      }
      if (n <= 100000 && m <= 25) {
        tmp[n][m] = merker;
      }
      return merker;
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100001; i++) {
      for (int j = 0; j < 26; j++) {
        tmp[i][j] = -1;
      }
    }
    System.out.println("schnell mit DP: " + aDp(7,7));
    System.out.println("langsam ohne DP: " + a(7,7));
  }
}
