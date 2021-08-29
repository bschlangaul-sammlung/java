package org.bschlangaul.examen.examen_66115.jahr_2019.fruehjahr;

import java.util.Arrays;

public class Muffin {

  /**
   * Diese Hilfemethode berechnet die Gesamtzahl der Muffins.
   *
   * @param muffins Ein Feld mit der Gesamtzahl der Muffins je Sorte.
   *
   * @return Gesamtzahl an Muffins (Alle Muffins egal von welcher Sorte addiert)
   */
  public static int berechneGesamtzahl(int[] muffins) {
    int gesamtzahl = 0;
    for (int i = 0; i < muffins.length; i++) {
      gesamtzahl += muffins[i];

    }
    return gesamtzahl;
  }

  /**
   * Sortiere die Anzahl je Muffinsorte aufsteigend. Das letzte Element im Feld
   * ist die Sorte mit den meisten Muffins. Diese Methode gibt das Feld nach der
   * Sortierung auf der Konsole aus. So können wir den Greedy-Algorithmus besser
   * nachvollziehen.
   *
   * @param muffins Ein Feld mit der Gesamtzahl der Muffins je Sorte.
   */
  public static void sortiere(int[] muffins) {
    Arrays.sort(muffins);
    System.out.println(Arrays.toString(muffins));
  }

  /**
   * Implementiert den Greedy-Algorithmus für 4 Muffinsorten und 3 Muffinentnahmen
   * pro Teller.
   *
   * @return Die Anzahl der übrig gebliebenen Muffins.
   */
  public static int berechneRest4Sorten3ProTeller() {
    int[] muffins = new int[] { 10, 18, 12, 9 };

    // Anzahl an Muffinsorten.
    int n = muffins.length;
    sortiere(muffins);

    // Wir nehmen uns 3 verschiedene Muffins solange, wie die dritthäufigste
    // Muffinsorte noch Muffins hat.
    while (muffins[n - 3] > 0) {
      muffins[n - 1]--;
      muffins[n - 2]--;
      muffins[n - 3]--;
      sortiere(muffins);
    }
    return berechneGesamtzahl(muffins);
  }

  /**
   * Implementiert den Greedy-Algorithmus für allgemeine Werte.
   *
   * @param muffins Ein Feld mit der Gesamtzahl der Muffins je Sorte.
   * @param k       Die Anzahl an verschiedenen Muffings, die pro Teller entnommen
   *                werden.
   *
   * @return Die Anzahl der übrig gebliebenen Muffins.
   */
  public static int berechneRestAllgemein(int[] muffins, int k) {
    // Anzahl an Muffinsorten.
    int n = muffins.length;
    sortiere(muffins);

    // Wir nehmen uns 3 verschiedene Muffins solange, wie die dritthäufigste
    // Muffinsorte noch Muffins hat.
    while (muffins[n - k] > 0) {
      for (int i = 1; i <= k; i++) {
        muffins[n - i]--;
      }
      sortiere(muffins);
    }
    return berechneGesamtzahl(muffins);
  }

  public static void main(String[] args) {
    System.out.println(berechneRest4Sorten3ProTeller());
    System.out.println(berechneRestAllgemein(new int[] { 10, 18, 12, 9, 11 }, 4));
  }

}
