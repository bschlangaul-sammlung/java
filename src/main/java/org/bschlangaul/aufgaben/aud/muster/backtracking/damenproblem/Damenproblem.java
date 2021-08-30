package org.bschlangaul.aufgaben.aud.muster.backtracking.damenproblem;

public class Damenproblem {
  static int n = 8;
  static int[][] spielBrett = new int[n][n];
  static int DAME = 1;
  static int LEER = 0;

  public static boolean istGültig(int zeile, int spalte) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (spielBrett[i][j] == 1) {
          if (i == zeile || j == spalte) {
            return false;
          }
        }
      }
    }
    for (int i = 0; i < n; i++) {
      if (zeile + i < n && spalte + i < n && spielBrett[zeile + i][spalte + i] == 1)
        return false;
      if (zeile - i > -1 && spalte - i > -1 && spielBrett[zeile - i][spalte - i] == 1)
        return false;
      if (zeile + i < n && spalte - i > -1 && spielBrett[zeile + i][spalte - i] == 1)
        return false;
      if (zeile - i > -1 && spalte + i < n && spielBrett[zeile - i][spalte + i] == 1)
        return false;
    }
    return true;
  }

  public static boolean löse(int zeile) {
    if (zeile == n) {
      return true;
    }

    for (int i = 0; i < n; i++) {
      if (istGültig(zeile, i) == true) {
        spielBrett[zeile][i] = DAME;

        if (löse(zeile + 1) == true) {
          return true;
        }
        spielBrett[zeile][i] = LEER;
      }
    }

    return false;
  }

  public static void zeigeSpielBrett() {
    for (int i = 0; i < spielBrett.length; i++) {
      for (int j = 0; j < spielBrett[i].length; j++) {
        System.out.print(spielBrett[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void fülleFeld() {
    for (int i = 0; i < spielBrett.length; i++) {
      for (int j = 0; j < spielBrett[i].length; j++) {
        spielBrett[i][j] = 0;
      }
    }
  }

  /**
   * Das Fenster wird irgendwie nicht in Sway WM angezeigt.
   *
   * @param args Kommandozeilen-Arguemnte
   */
  public static void main(String[] args) {
    fülleFeld();
    löse(0);
    zeigeSpielBrett();
    new Ausgabe(n, spielBrett);
  }
}
