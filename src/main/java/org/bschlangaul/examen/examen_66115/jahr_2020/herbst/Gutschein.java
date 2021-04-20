package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

/**
 * https://www.geeksforgeeks.org/subset-gutscheinBetrag-problem-dp-25/
 */
public class Gutschein {
  /**
   * @param gutscheinBetrag Das GUTSCHEIN-Betrag von 0, 1, ...
   *
   * @param warenWerte      Das GUTSCHEIN-Problem ist gegeben durch eine Folge w1,
   *                        ..., wn von Warenwerten.
   *
   * @return Wahr, wenn der Gutscheinbetrag vollständig in Warenwerten eingelöst
   *         werden kann, falsch wenn der Betrag nicht vollständig eingelöst
   *         werden kann.
   */
  public static boolean gutscheinDP(int gutscheinBetrag, int warenWerte[]) {
    // The value of subset[i][j] will be
    // true if there is a subset of
    // set[0..j-1] with gutscheinBetrag equal to i
    int n = warenWerte.length;
    boolean tabelle[][] = new boolean[gutscheinBetrag + 1][n + 1];

    // If gutscheinBetrag is 0, then answer is true
    for (int i = 0; i <= n; i++)
      tabelle[0][i] = true;

    // If gutscheinBetrag is not 0 and set is empty,
    // then answer is false
    for (int k = 1; k <= gutscheinBetrag; k++)
      tabelle[k][0] = false;

    // Fill the subset table in botton
    // up manner
    for (int k = 1; k <= gutscheinBetrag; k++) {
      for (int i = 1; i <= n; i++) {
        tabelle[k][i] = tabelle[k][i - 1];
        if (k >= warenWerte[i - 1])
          tabelle[k][i] = tabelle[k][i] || tabelle[k - warenWerte[i - 1]][i - 1];
      }
    }

    /*
     * // uncomment this code to print table for (int i = 0; i <= gutscheinBetrag;
     * i++) { for (int j = 0; j <= n; j++) System.out.println (subset[i][j]); }
     */

    return tabelle[gutscheinBetrag][n];
  }

  public static void main(String[] args) {
    // new Gutschein(50, new int[] { 10, 30, 40, 20, 15 });

    System.out.println(gutscheinDP(10, new int[] { 10, 30, 40, 20, 15 }));
  }

}
