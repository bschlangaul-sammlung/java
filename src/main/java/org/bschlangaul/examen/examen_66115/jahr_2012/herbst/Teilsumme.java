package org.bschlangaul.examen.examen_66115.jahr_2012.herbst;

/**
 * Teilsumme.java Klasse mit Algorithmen für die Berechnung des größten
 * gemeinsamen Teilers zweier Ganzzahlen Algorithmen und Datenstrukturen,
 * Auflage 4, Kapitel 2.1
 *
 * nach Prof. Grude, Prof. Solymosi, (c) 2000-2008: APSIS GmbH 22. April 2008
 * http://public.beuth-hochschule.de/oo-plug/A&D/prog/kap21/Teilsumme.java
 */
public class Teilsumme {

  private static int rechtesRandMax(final int[] folge, int links, int rechts) { //
    // requires 0 <= links <= rechts < folge.length
    // berechnet rechtes Randmaximum in folge zwischen links und rechts
    int bisherMax = 0, bisherSum = 0;
    for (int i = rechts; i >= links; i--) {
      bisherSum += folge[i];
      bisherMax = Math.max(bisherMax, bisherSum);
    }

    return bisherMax;
  }

  private static int linkesRandMax(final int[] folge, int links, int rechts) {
    // requires 0 <= links <= rechts < folge.length
    // berechnet linkes Randmaximum in folge zwischen links und rechts
    int bisherMax = 0, bisherSum = 0;
    for (int i = links; i <= rechts; i++) {
      bisherSum += folge[i];
      bisherMax = Math.max(bisherMax, bisherSum);
    }

    return bisherMax;
  }

  private static int maxTeilsummeRekursiv(final int[] folge, int links, int rechts) {
    // requires 0 <= links <= rechts < folge.length
    // berechnet maximale Teilsumme in folge zwischen links und rechts
    if (links == rechts) // nur ein Element
      return Math.max(0, folge[links]);
    else {
      final int mitte = (rechts + links) / 2;
      final int maxLinks = maxTeilsummeRekursiv(folge, links, mitte);
      final int maxRechts = maxTeilsummeRekursiv(folge, mitte + 1, rechts);
      final int rechtesMax = rechtesRandMax(folge, links, mitte);
      // linke Hälfte
      final int linkesMax = linkesRandMax(folge, mitte + 1, rechts);
      // rechte Hälfte
      return Math.max(maxRechts, Math.max(maxLinks, rechtesMax + linkesMax));
    }
  }

  /**
   * Berechnet die maximale Teilsumme von folge rekursiv mit logarithmischer
   * Zeitkomplexität
   *
   * @param folge
   * @return maximale Teilsumme in folge
   */
  public static int maxTeilsummeRekursiv(final int[] folge) {
    // berechnet maximale Teilsumme von folge
    return maxTeilsummeRekursiv(folge, 0, folge.length - 1);
  }

  public static void main(String[] args) {
    final int[] testfolge = { +5, -8, +3, +3, -5, +7, -2, -7, +3, +5 };
    // final int[] testfolge = {+5, -8, +3, +3, -5, +2, +7, -2, -7, +3, -1, +5};

    int[] folge;
    if (args.length > 0) {
      folge = new int[args.length];
      for (int i = 0; i < args.length; i++)
        folge[i] = Integer.parseInt(args[0]);
    } else
      folge = testfolge;

    int erg = maxTeilsummeRekursiv(folge);
    System.out.println("maxTeilsummRekursiv = " + erg);
  }
}
