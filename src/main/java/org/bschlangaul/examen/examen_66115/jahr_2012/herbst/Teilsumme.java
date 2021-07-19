package org.bschlangaul.examen.examen_66115.jahr_2012.herbst;

/**
 * Klasse zur Berechnung der maximalen Teilsumme einer Zahlenfolge.
 *
 * nach Teilsumme.java Klasse mit Algorithmen für die Berechnung des größten
 * gemeinsamen Teilers zweier Ganzzahlen Algorithmen und Datenstrukturen,
 * Auflage 4, Kapitel 2.1
 *
 * nach Prof. Grude, Prof. Solymosi, (c) 2000-2008: 22. April 2008
 * http://public.beuth-hochschule.de/oo-plug/A&D/prog/kap21/Teilsumme.java
 */
public class Teilsumme {

  /**
   * Berechne die maximale Teilsumme an der rechten Grenze. Die Eingabeparameter
   * müssen diese Werte aufweisen: 0 <= links <= rechts < folge.length.
   *
   * @param folge  Die Zahlenfolge, in der die maximale Zahlensumme gerechnet
   *               werden soll.
   * @param links  Die Index-Nummer der linken Grenze.
   * @param rechts Die Index-Nummer der rechten Grenze.
   *
   * @return Die maximale Teilsumme.
   */
  private static int berechneRandRechts(int[] folge, int links, int rechts) {
    int max = 0;
    int sum = 0;
    for (int i = rechts; i >= links; i--) {
      sum += folge[i];
      max = Math.max(max, sum);
    }
    return max;
  }

  /**
   * Berechne die maximale Teilsumme an der linken Grenze. Die Eingabeparameter
   * müssen diese Werte aufweisen: 0 <= links <= rechts < folge.length.
   *
   * @param folge  Die Zahlenfolge, in der die maximale Zahlensumme gerechnet
   *               werden soll.
   * @param links  Die Index-Nummer der linken Grenze.
   * @param rechts Die Index-Nummer der rechten Grenze.
   *
   * @return Die maximale Teilsumme.
   */
  private static int berechneRandLinks(int[] folge, int links, int rechts) {
    int max = 0;
    int sum = 0;
    for (int i = links; i <= rechts; i++) {
      sum += folge[i];
      max = Math.max(max, sum);
    }
    return max;
  }

  /**
   * Berechne die maximale Teilsumme in der Zahlenfolge zwischen einer gegeben
   * linken und rechten Grenze. Die Eingabeparameter müssen diese Werte aufweisen:
   * 0 <= links <= rechts < folge.length.
   *
   * @param folge  Die Zahlenfolge, in der die maximale Zahlensumme gerechnet
   *               werden soll.
   * @param links  Die Index-Nummer der linken Grenze.
   * @param rechts Die Index-Nummer der rechten Grenze.
   *
   * @return Die maximale Teilsumme.
   */
  private static int berechne(int[] folge, int links, int rechts) {
    if (links == rechts) // nur ein Element
      return Math.max(0, folge[links]);
    else {
      final int mitte = (rechts + links) / 2;
      final int maxLinks = berechne(folge, links, mitte);
      final int maxRechts = berechne(folge, mitte + 1, rechts);
      final int maxGrenzeRechts = berechneRandRechts(folge, links, mitte);
      // linke Hälfte
      final int maxGrenzeLinks = berechneRandLinks(folge, mitte + 1, rechts);
      // rechte Hälfte
      return Math.max(maxRechts, Math.max(maxLinks, maxGrenzeRechts + maxGrenzeLinks));
    }
  }

  /**
   * Berechne die maximale Teilsumme einer Zahlenfolge rekursiv mit
   * logarithmischer Zeitkomplexität.
   *
   * @param folge Die Zahlenfolge, in der die maximale Zahlensumme gerechnet
   *              werden soll.
   *
   * @return Die maximale Teilsumme.
   */
  public static int berechne(int[] folge) {
    return berechne(folge, 0, folge.length - 1);
  }

  public static void main(String[] args) {
    int[] folge = { 5, -6, 4, 2, -5, 7, -2, -7, 3, 5 };
    int ergebnis = berechne(folge);
    System.out.println(ergebnis);
  }
}
