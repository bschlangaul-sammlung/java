package org.bschlangaul.aufgaben.aud.muster.greedy;

/**
 * Qualifizierungsmaßnahme Informatik: Algorithmen und Datenstrukturen:
 * Aufgabenblatt 3: Algorithmenmuster.
 *
 * <a href="https://www.studon.fau.de/file2521908_download.html">Angabe: AB_3
 * Greedy_DP_Backtracking.pdf</a>
 * <a href="https://www.studon.fau.de/file2521907_download.html">Lösung: AB_3
 * Greedy_DP_Backtracking_Lsg.pdf</a>
 */
public class Muenzwechsler {

  /**
   * Wechsle einen Cent-Betrag in die Münzen 5-Cent, 2-Cent and 1-Cent.
   *
   * @param betrag Geldbetrag in Cent.
   */
  public static void wechsle(int betrag) {
    int rest;
    int c5 = betrag / 5;
    rest = betrag % 5;
    int c2 = rest / 2;
    int c1 = rest % 2;

    System.out.println(String.format("Für den Betrag von %s Cent werden \n" + "%s Fünf-Cent-Münzen, \n"
        + "%s Zwei-Cent-Münzen und \n" + "%s Ein-Cent-Münzen ausgegeben.", betrag, c5, c2, c1));
  }

  public static void main(String[] args) {
    wechsle(1);
    wechsle(20);
    wechsle(23);
    wechsle(42);
  }

}
