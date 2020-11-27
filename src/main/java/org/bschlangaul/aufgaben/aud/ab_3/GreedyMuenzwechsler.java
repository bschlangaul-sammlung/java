package org.bschlangaul.aufgaben.aud.ab_3;

public class GreedyMuenzwechsler {

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

    System.out.println(String.format(
        "Für den Betrag von %s Cent werden \n" +
        "%s Fünf-Cent-Münzen, \n" +
        "%s Zwei-Cent-Münzen und \n" +
        "%s Ein-Cent-Münzen ausgegeben.",
        betrag, c5, c2, c1));
  }

  public static void main(String[] args) {
    wechsle(1);
    wechsle(20);
    wechsle(23);
    wechsle(42);
  }

}
