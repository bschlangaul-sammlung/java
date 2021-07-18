package org.bschlangaul.examen.examen_66116.jahr_2018.herbst;

/**
 * https://stackoverflow.com/a/14186622
 */
public class Rucksack {
  // static int[] werte = new int[] { 894, 260, 392, 281, 27 };
  // static int[] gewichte = new int[] { 8, 6, 4, 0, 21 };
  // static int[] werte = new int[] { 4, 2, 10, 1, 2 };
  // static int[] gewichte = new int[] { 12, 1, 4, 1, 2 };
  static int werte[] = new int[] { 60, 100, 120 };
  static int gewichte[] = new int[] { 10, 20, 30 };



  /**
   * Gewichtsschranke
   */
  //static int B = 30;
  //static int B = 15;
  static int B = 50;

  /**
   * Diejenigen Objekte aus U, die zur Maximierung des Nutzwerts beigetragen
   * haben.
   */
  static boolean[] auswahl = new boolean[werte.length];

  private static int berechne(int i, int W) {
    if (i < 0) {
      return 0;
    }
    int alt = berechne(i - 1, W);
    if (gewichte[i] > W) {
      // Backtracking!
      auswahl[i] = false;
      return alt;
    } else {

      int neu = berechne(i - 1, W - gewichte[i]) + werte[i];
      if (alt >= neu) {
        // Backtracking!
        auswahl[i] = false;
        return alt;
      } else {
        auswahl[i] = true;
        return neu;
      }
    }
  }

  static void werteAus() {
    System.out.println(berechne(werte.length - 1, B));

    int gesamtGewicht = 0;
    int gesamtWert = 0;

    for (int i = 0; i < auswahl.length; i++) {
      if (auswahl[i]) {
        gesamtGewicht += gewichte[i];
        gesamtWert += werte[i];
        System.out.println("Objekt-Nr. " + i + " Gewicht: " + gewichte[i] + " Wert: " + werte[i]);
      }
    }

    System.out.println("Gesamtgewicht: " + gesamtGewicht);
    System.out.println("Gesamtwert: " + gesamtWert);
  }

  public static void main(String[] args) {
    werteAus();
  }
}
