package org.bschlangaul.aufgaben.aud.muster.dp;

import org.bschlangaul.helfer.Farbe;
import org.bschlangaul.helfer.Konsole;

/**
 * <a href="https://www.studon.fau.de/file2521908_download.html">Angabe: AB_3
 * Greedy_DP_Backtracking.pdf</a>
 * <a href="https://www.studon.fau.de/file2521907_download.html">Lösung: AB_3
 * Greedy_DP_Backtracking_Lsg.pdf</a>
 *
 * Qualifizierungsmaßnahme Informatik: Algorithmen und Datenstrukturen:
 * Aufgabenblatt 3: Algorithmenmuster.
 *
 * <a href="https://www.studon.fau.de/file2521908_download.html">Angabe: AB_3
 * Greedy_DP_Backtracking.pdf</a>
 * <a href="https://www.studon.fau.de/file2521907_download.html">Lösung: AB_3
 * Greedy_DP_Backtracking_Lsg.pdf</a>
 */
public class Gitter {

  /**
   * m + 1: Anzahl der Zeilen
   */
  private int m;

  /**
   * n + 1: Anzahl der Spalten
   */
  private int n;

  /**
   * anzahlWege[i][j]: Anzahl der Wege vom Punkt (0,0) zum Punkt (i,j)
   */
  private int anzahlWege[][];

  public Gitter(int m, int n) {
    this.m = m;
    this.n = n;
    anzahlWege = new int[m + 1][n + 1];
  }

  public int berechneAnzahlWege() {
    int i, j;
    for (i = 1; i <= m; i++) {
      anzahlWege[i][0] = 1;
    }
    for (j = 1; j <= n; j++) {
      anzahlWege[0][j] = 1;
    }
    for (i = 1; i <= m; i++) {
      for (j = 1; j <= n; j++) {
        anzahlWege[i][j] = anzahlWege[i - 1][j] + anzahlWege[i][j - 1];
      }
    }
    return anzahlWege[m][n];
  }

  /**
   * Zeige die Lösung in der Konsole.
   */
  public void zeigeLoesung() {
    System.out.println(
        String.format("Anzahl der Wege von %sx%s: %s", Farbe.gelb(m), Farbe.gelb(n), Farbe.grün(berechneAnzahlWege())));
    System.out.println(Farbe.rot("Gitter:"));
    Konsole.zeige2DIntFeld(anzahlWege);
    System.out.println();
  }

  public static void main(String args[]) {
    new Gitter(2, 2).zeigeLoesung();
    new Gitter(3, 3).zeigeLoesung();
    new Gitter(4, 4).zeigeLoesung();
    new Gitter(5, 5).zeigeLoesung();
  }
}
