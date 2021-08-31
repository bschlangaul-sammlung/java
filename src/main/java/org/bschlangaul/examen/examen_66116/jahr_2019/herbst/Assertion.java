package org.bschlangaul.examen.examen_66116.jahr_2019.herbst;

public class Assertion {
  public static double[][] magic(double[][] A, double[][] B, int m) {
    double[][] C = new double[m][m];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < m; j++)
        for (int k = 0; k < m; k++)
          C[i][j] += A[i][k] * B[k][3];
    return C;
  }
}
