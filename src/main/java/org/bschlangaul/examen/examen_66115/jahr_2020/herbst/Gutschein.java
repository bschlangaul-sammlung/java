package org.bschlangaul.examen.examen_66115.jahr_2020.herbst;

/**
 * Nach <a href="https://www.geeksforgeeks.org/subset-sub-problem-dp-25"> Subset
 * Sum Problem auf geeksforgeeks.org</a>
 */
public class Gutschein {
  /**
   * @param G Die Indizes der GUTSCHEIN-Beträge.
   *
   * @param W Das GUTSCHEIN-Problem ist gegeben durch eine Folge w1, ..., wn von
   *          Warenwerten.
   *
   * @return Wahr, wenn der Gutscheinbetrag vollständig in Warenwerten eingelöst
   *         werden kann, falsch wenn der Betrag nicht vollständig eingelöst
   *         werden kann.
   */
  public static boolean gutscheinDP(int G, int W[]) {
    // Der Eintrag in der Tabelle tabelle[i][k] ist wahr,
    // wenn es eine Teilsumme der
    // W[0..i-1] gibt, die gleich k ist.
    int n = W.length;
    boolean table[][] = new boolean[n + 1][G + 1];

    // Wenn der Gutschein-Betrag größer als 0 ist und es keine
    // Warenwerte (n = 0) gibt, kann der Gutschein nicht eingelöst
    // werden.
    for (int k = 1; k <= G; k++) {
      table[0][k] = false;
    }

    // Ist der Gutscheinbetrag 0, dann kann er immer eingelöst werden.
    for (int i = 0; i <= n; i++) {
      table[i][0] = true;
    }

    for (int i = 1; i <= n; i++) {
      for (int k = 1; k <= G; k++) {
        table[i][k] = table[i - 1][k];
        // Warenwert
        int w = W[i - 1];
        if (k >= w && !table[i][k]) {
          table[i][k] = table[i - 1][k - w];
        }
      }
    }
    return table[n][G];
  }

  public static void main(String[] args) {
    System.out.println(gutscheinDP(50, new int[] { 10, 30, 40, 20, 15 }));
    System.out.println(gutscheinDP(41, new int[] { 10, 30, 40, 20, 15 }));

    System.out.println(gutscheinDP(3, new int[] { 1, 2, 3 }));
    System.out.println(gutscheinDP(5, new int[] { 1, 2, 3 }));
    System.out.println(gutscheinDP(6, new int[] { 1, 2, 3 }));
    System.out.println(gutscheinDP(2, new int[] { 1, 2, 3 }));
    System.out.println(gutscheinDP(1, new int[] { 1, 2, 3 }));
    System.out.println(gutscheinDP(7, new int[] { 1, 2, 3 }));
  }
}
