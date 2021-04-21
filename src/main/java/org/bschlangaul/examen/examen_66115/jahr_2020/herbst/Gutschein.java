package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

// import java.util.Arrays;

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
    // Der Eintrag in der Tabelle tabelle[i][k] ist wahr,
    // wenn es eine Teilsumme der
    // warenWerte[0..i-1] gibt, die gleich k ist.
    int n = warenWerte.length;
    boolean tabelle[][] = new boolean[n + 1][gutscheinBetrag + 1];

    // Wenn der Gutschein-Betrag größer als 0 ist und es keine
    // Warenwerte (n = 0) gibt, kann der Gutschein nicht eingelöst
    // werden.
    for (int k = 1; k <= gutscheinBetrag; k++)
      tabelle[0][k] = false;

    // Ist der Gutscheinbetrag 0, dann kann er immer eingelöst werden.
    for (int i = 0; i <= n; i++)
      tabelle[i][0] = true;

    for (int i = 1; i <= n; i++) {
      for (int k = 1; k <= gutscheinBetrag; k++) {
        tabelle[i][k] = tabelle[i - 1][k];
        if (k >= warenWerte[i - 1])
          tabelle[i][k] = tabelle[i][k] || tabelle[i - 1][k - warenWerte[i - 1]];
      }
    }
    // System.out.println(Arrays.deepToString(tabelle));
    return tabelle[n][gutscheinBetrag];
  }

  public static void main(String[] args) {
    System.out.println(gutscheinDP(10, new int[] { 10, 30, 40, 20, 15 }));
    System.out.println(gutscheinDP(3, new int[] { 1, 2, 3 }));
  }
}
