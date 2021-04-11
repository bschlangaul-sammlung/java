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
   * Erzeuge das TikZ-Markup für eine B-Baum Seite (Knoten).
   *
   * <pre>
   * {@code
   *   child {60 \nodepart{two} 80 \nodepart{three} 90
   *     child {node {15}}
   *     child {node {31}}
   *   }
   * }
   * </pre>
   *
   * @param seite Die B-Baum-Seite (Knoten), für die das Markup erzeugt werden
   *              soll.
   * @param ebene Auf welcher Ebene sich die Seite im B-Baum befindet, beginnend
   *              bei 0.
   *
   * @return Das TikZ-Markup zum zeichnen eines B-Baums in TeX.
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

    if (ebene == 0)
      return String.format("\\node {%s} [->]%s", schlüsselListe, kinderListe);
    else
      return String.format("%s  node {%s}%s\n%s", leerzeichen, schlüsselListe, kinderListe, leerzeichen);
  }

  public static void druckeBaum(BBaum bbaum) {
    BBaum.BBaumSeite wurzel = bbaum.gibWurzel();

    String ausgabe = "\\begin{tikzpicture}[\n" + "  li bbaum,\n"
        + "  level 1/.style={level distance=10mm,sibling distance=32mm},\n"
        + "  level 2/.style={level distance=10mm,sibling distance=20mm},\n" + "]\n" + erzeugeSeite(wurzel, 0) + ";\n"
        + "\\end{tikzpicture}";
    System.out.println(ausgabe);
  }

  public static void main(String[] args) {
    BBaum bbaum = new BBaum(2);
    bbaum.fügeEin(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
    druckeBaum(bbaum);
  }

}
