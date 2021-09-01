package org.bschlangaul.examen.examen_66116.jahr_2019.herbst;

public class Assertion {
  public static double[][] magic(double[][] A, double[][] B, int m) {
    double[][] C = new double[m][m];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < m; j++)
        for (int k = 0; k < m; k++)
          C[i][j] += A[i][k] * B[k][j];
    return C;
  }

  public static double[][] magic2(double[][] A, double[][] B, int m) {
    double[][] C = new double[m][m];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < m; j++)
        for (int k = 0; k < m; k++) {
          C[i][j] += A[i][k] * B[k][j];

          if (i == 2  && j == 2) {
            System.out.println(String.format("A[%d][%d] * B[%d][%d] = %s * %s", i, k, k, j, A[i][k], B[k][j]));
          }
        }

    return C;
  }

  public static void print(double[][] A) {
    for (int i = 0; i < A.length; i++) {
      double[] reihe = A[i];
      for (int j = 0; j < reihe.length; j++) {
        System.out.print(reihe[i] + " ");
      }
      System.out.println();
    }
  }

  public static void printMagic(double[][] A, double[][] B, int m) {
    print(magic2(A, B, m));
  }

  public static void main(String[] args) {
    printMagic(new double[][] {
      { 1, 2, 3 },
      { 4, 5, 6 },
      { 7, 8, 9 },
    }, new double[][] {
      { 11, 12, 13, 1000 },
      { 16, 15, 14 },
      { 17, 18, 19 },
    }, 3);
  }
}
