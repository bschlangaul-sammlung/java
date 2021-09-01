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

  private static void assert2DArray(double[][] a, int m) throws Exception {
    if (a.length < m) {
      throw new Exception("Das 2D-Feld muss mindestens m Zeilen/Felder haben.");
    }

    for (int i = 0; i < a.length; i++) {
      double[] row = a[i];
      if (row.length < m) {
        throw new Exception("Jede Zeile im 2D-Feld muss mindestens m Einträge/Spalten haben.");
      }
    }
  }

  public static double[][] magicWithAssertions(double[][] A, double[][] B, int m) throws Exception {
    if (m < 0) {
      throw new Exception("m darf nicht negativ sein.");
    }
    assert2DArray(A, m);
    assert2DArray(B, m);
    double[][] C = new double[m][m];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < m; j++)
        for (int k = 0; k < m; k++) {
          C[i][j] += A[i][k] * B[k][j];
        }
    return C;
  }

  /**
   * Duplikat der magic-Methode mit einer println-Anweisung, um die Funktionsweise besser untersuchen zu können.
   *
   * @param A Ein zwei-dimensionales Feld des Datentyps double. Sollte mindestens m x m Einträge haben.
   * @param B Ein zwei-dimensionales Feld des Datentyps double. Sollte mindestens m x m Einträge haben.
   * @param m Ein nicht negative Ganzzahl.
   *
   * @return Ein zwei-dimensionales Feld mit m x m Einträgen.
   */
  public static double[][] magicPrint(double[][] A, double[][] B, int m) {
    double[][] C = new double[m][m];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < m; j++)
        for (int k = 0; k < m; k++) {
          C[i][j] += A[i][k] * B[k][j];
          if (i == 2 && j == 2) {
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
    try {
      print(magicPrint(A, B, m));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    printMagic(new double[][] {
      { 1, 2, 3 },
      { 4, 5, 6 },
      { 7, 8, 9 },
    }, new double[][] {
      { 11, 12, 13 },
      { 16, 15, 14 },
      { 17, 18, 19 },
    }, 3);
  }
}
