package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.BBaum;

/**
 * <pre>
 * {@code
 * \begin{tikzpicture}[
 *   scale=0.8,
 *   transform shape,
 *   bbaum,
 *   level 1/.style={level distance=10mm,sibling distance=32mm},
 *   level 2/.style={level distance=10mm,sibling distance=20mm},
 * ]
 *  {10 \nodepart{two} 33} [->]
 *   child {node {8}
 *     child {node {5}}
 *     child {node {9}}
 *   }
 *   child {node {20}
 *     child {node {15}}
 *     child {node {31}}
 *   }
 *   child {node {50}
 *     child {node {45}}
 *     child {node {60 \nodepart{two} 80}}
 *   }
 * ;
 * \end{tikzpicture}
 * }
 * </pre>
 */

public class BBaumReporter {

  static String[] englischeZahlen = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };

  /**
   * <pre>
   * {@code
   *   child {60 \nodepart{two} 80 \nodepart{three} 90
   *     child {node {15}}
   *     child {node {31}}
   *   }
   * }
   * </pre>
   */
  private static String erzeugeSeite(BBaum.BBaumSeite seite, int ebene) {
    String leerzeichen = " ".repeat(ebene * 2);

    String schlüsselListe = "";
    for (int i = 0; i < seite.gibAnzahlSchlüssel(); i++) {
      schlüsselListe += seite.gibSchlüssel(i);
      if (i < seite.gibAnzahlSchlüssel() - 1) {
        schlüsselListe += String.format(" \\nodepart{%s} ", englischeZahlen[i + 1]);
      }
    }

    String kinderListe = "";

    for (int i = 0; i < seite.gibAnzahlKinder(); i++) {
      kinderListe += String.format("\n%s  child {\n%s}", leerzeichen,
          erzeugeSeite(seite.gibKindDurchIndex(i), ebene + 1));
    }

    if (!kinderListe.equals(""))
      kinderListe = "\n" + kinderListe;

    if (ebene == 0)
      return String.format("\\node {%s}[->]\n%s", schlüsselListe, kinderListe);
    else
      return String.format("%s  node {%s}%s\n%s", leerzeichen, schlüsselListe, kinderListe, leerzeichen);
  }

  public static void druckeBaum(BBaum bbaum) {
    BBaum.BBaumSeite wurzel = bbaum.gibWurzel();

    String ausgabe = "\\begin{tikzpicture}[\n" + "  bbaum,\n"
        + "  level 1/.style={level distance=10mm,sibling distance=32mm},\n"
        + "  level 2/.style={level distance=10mm,sibling distance=20mm},\n" + "]\n"  + erzeugeSeite(wurzel, 0)
        + ";\n" + "\\end{tikzpicture}";
    System.out.println(ausgabe);
  }

  public static void main(String[] args) {
    BBaum bbaum = new BBaum(2);
    bbaum.fügeEin(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);

    druckeBaum(bbaum);

  }


}
