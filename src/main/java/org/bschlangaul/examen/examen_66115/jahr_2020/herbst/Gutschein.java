package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

import org.bschlangaul.helfer.Konsole;

public class Gutschein {
  /**
   * Das GUTSCHEIN-Betrag von 0, 1, ...
   */
  int gutscheinBetrag;

  /**
   * Das GUTSCHEIN-Problem ist gegeben durch eine Folge w1, ..., wn von
   * Warenwerten.
   */
  int[] warenWerten;

  boolean[][] tabelle;

  public Gutschein(int G, int[] w) {
    tabelle = new boolean[w.length][G + 1];
  }

  /**
   * Helfer-Methode, die größere Zahl zurückgibt.
   *
   * @param a Die Zahl a.
   * @param b Die Zahl b
   *
   * @return Die größere Zahl.
   */
  static int max(int a, int b) {
    return (a > b) ? a : b;
  }

  static int gutscheinDP(int gutscheinBetrag, int warenWerten[], int werte[]) {
    int w, g;
    int n = warenWerten.length;
    int tabelle[][] = new int[n + 1][gutscheinBetrag + 1];

    for (w = 0; w <= n; w++) {
      for (g = 0; g <= gutscheinBetrag; g++) {
        if (w == 0 || g == 0)
          tabelle[w][g] = 0;
        else if (warenWerten[w - 1] <= g)
          tabelle[w][g] = max(werte[w - 1] + tabelle[w - 1][g - warenWerten[w - 1]], tabelle[w - 1][g]);
        else
          tabelle[w][g] = tabelle[w - 1][g];
      }
    }

    Konsole.zeige2DIntFeld(tabelle);

    return tabelle[n][gutscheinBetrag];
  }


  public static void main(String[] args) {
    //new Gutschein(50, new int[] { 10, 30, 40, 20, 15 });

    System.out.println(gutscheinDP(51, new int[] { 10, 30, 40, 20, 15 }, new int[] { 1, 1, 1, 1, 1 }));
  }

}
