package org.bschlangaul.muster;

/**
 * <a href=
 * "https://de.wikiversity.org/wiki/Kurs:Algorithmen_und_Datenstrukturen/Vorlesung/Greedyalgorithmen_Wechselgeldalgorithmus">Kurs:Algorithmen
 * und Datenstrukturen/Vorlesung/Greedyalgorithmen Wechselgeldalgorithmus</a>
 */
public class Wechselgeld {

  public static int[] berechneWechselgeld(int[] münzen, int betrag) {
    int[] wechselgeld = new int[münzen.length];
    int aktuelleMünze = münzen.length - 1;
    while (betrag > 0) {
      while (betrag < münzen[aktuelleMünze] && aktuelleMünze > 0)
        aktuelleMünze--;
      if (betrag >= münzen[aktuelleMünze] && aktuelleMünze >= 0) {
        betrag -= münzen[aktuelleMünze];
        wechselgeld[aktuelleMünze]++;
      } else
        return null;
    }
    return wechselgeld;
  }

  public static void main(String[] args) {
    int[] münzen = { 1, 2, 5, 10, 20, 50 };
    int betrag = 78;
    int[] wechselgeld = berechneWechselgeld(münzen, betrag);

    System.out.println(String.format("Der Betrag von %s Cent wird gewechselt in:", betrag));

    for (int i = 0; i < wechselgeld.length; i++) {
      System.out.println(String.format("%s x %s Cent", wechselgeld[i], münzen[i]));
    }
  }
}
