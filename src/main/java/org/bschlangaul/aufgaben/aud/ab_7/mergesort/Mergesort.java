package org.bschlangaul.aufgaben.aud.ab_7.mergesort;

import org.bschlangaul.helfer.Konsole;

/**
 * https://www.studon.fau.de/file2617983_download.html
 *
 * Es muss nur die Methode merge(int[] s, int [] r) implemetiert werden.
 * Weiterhin steht die Testklasse "MergesortText" zur Verfügung.
 */
public class Mergesort {

  public int[] mergesort(int[] s) {
    int[] left = new int[s.length / 2];
    int[] right = new int[s.length - (s.length / 2)];
    int[] result;

    if (s.length <= 1) {
      result = s;
    } else {
      for (int i = 0; i < s.length / 2; i++) {
        left[i] = s[i];
      }
      int a = 0;
      for (int j = (s.length / 2); j < s.length; j++) {
        right[a++] = s[j];
      }
      result = merge(mergesort(left), mergesort(right));
    }
    return result;
  }

  public int[] merge(int[] s, int[] r) {
    int[] ergebnis = new int[s.length + r.length];
    int indexLinks = 0;
    int indexRechts = 0;
    int indexErgebnis = 0;

    // Im Reisverschlussverfahren s und r sortiert zusammenfügen.
    while (indexLinks < s.length && indexRechts < r.length) {
      if (s[indexLinks] < r[indexRechts]) {
        ergebnis[indexErgebnis] = s[indexLinks++];
      } else {
        ergebnis[indexErgebnis] = r[indexRechts++];
      }
      indexErgebnis++;
    }

    // Übrig gebliebene Elemente von s einfügen.
    while (indexLinks < s.length) {
      ergebnis[indexErgebnis++] = s[indexLinks++];
    }

    // Übrig gebliebene Elemente von r einfügen.
    while (indexRechts < r.length) {
      ergebnis[indexErgebnis++] = r[indexRechts++];
    }

    return ergebnis;
  }

  /**
   * Näher an Saake, aber nicht wirklich schön.
   */
  public int[] mergeNg(int[] s, int[] r) {
    int[] hilfsFeld = new int[s.length + r.length];
    int[] zahlen = new int[s.length + r.length];

    int index = 0, indexLinks = 0, indexRechts = 0, zähler = 0;
    int mitte = s.length - 1;
    int rechteGrenze = hilfsFeld.length - 1;
    indexRechts = rechteGrenze;

    // Die beiden Felder s und r zusammenfügen.
    for (index = 0; index <= mitte; index++) {
      hilfsFeld[index] = s[index];
    }
    // r wird umgedreht eingefügt.
    for (index = mitte; index < rechteGrenze; index++) {
      hilfsFeld[rechteGrenze + mitte - index] = r[zähler++];
    }

    // Der eigentliche Sortiervorgang
    for (index = 0; index <= rechteGrenze; index++) {
      if (hilfsFeld[indexLinks] < hilfsFeld[indexRechts]) {
        zahlen[index] = hilfsFeld[indexLinks++];
      } else {
        zahlen[index] = hilfsFeld[indexRechts--];
      }
    }

    return zahlen;
  }

  public static void main(String[] args) {
    Mergesort m = new Mergesort();
    int[] testArray = { -5, 5, -26, 42, 8, 78, -1, 0, -74 };
    int[] ergebnis = m.mergesort(testArray);
    Konsole.zeigeZahlenFeld(ergebnis);
  }
}
