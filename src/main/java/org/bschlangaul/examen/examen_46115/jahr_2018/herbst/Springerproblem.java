package org.bschlangaul.examen.examen_46115.jahr_2018.herbst;

/**
 * Nach <a href=
 * "https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1">geeksforgeeks.org</a>
 */
public class Springerproblem {
  static int felderAnzahl = 8;

  static int lösung[][];

  static int xBewegungen[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
  static int yBewegungen[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

  static void druckeLösung() {
    for (int x = 0; x < felderAnzahl; x++) {
      for (int y = 0; y < felderAnzahl; y++) {
        System.out.print(lösung[x][y] + " ");
      }
      System.out.println();
    }
  }

  /**
   * Versuche zum angegeben Feld zu hüpfen.
   *
   * @param x Die x-Koordinate des als nächstes anzuspringenden Feldes.
   * @param y Die y-Koordinate des als nächstes anzuspringenden Feldes.
   * @param z Die aktuelle Rekursionstiefe.
   *
   * @return Wahr wenn das „angehüpfte“ Feld in der Lösung ist, sonst falsch.
   */
  static boolean huepf(int x, int y, int z) {
    // nächste x-Koordinate
    int xN;
    // nächste y-Koordinate
    int yN;
    if (z == felderAnzahl * felderAnzahl) {
      return true;
    }

    for (int i = 0; i < 8; i++) {
      xN = x + xBewegungen[i];
      yN = y + yBewegungen[i];
      if (xN >= 0 && xN < felderAnzahl && yN >= 0 && yN < felderAnzahl && lösung[xN][yN] == -1) {
        lösung[xN][yN] = z;
        if (huepf(xN, yN, z + 1)) {
          return true;
        } else {
          // backtracking
          lösung[xN][yN] = -1;
        }
      }
    }
    return false;
  }

  static boolean löseSpringerproblem(int x, int y) {
    lösung = new int[8][8];

    for (int i = 0; i < felderAnzahl; i++) {
      for (int j = 0; j < felderAnzahl; j++) {
        lösung[i][j] = -1;
      }
    }

    lösung[x][y] = 0;

    if (!huepf(x, y, 1)) {
      System.out.println("Es konnte keine Lösung gefunden werden.");
      return false;
    } else {
      druckeLösung();
    }

    return true;
  }

  public static void main(String args[]) {
    löseSpringerproblem(0, 0);
  }
}
