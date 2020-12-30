package org.bschlangaul.graph.tex;

import org.bschlangaul.graph.GraphAdjazenzMatrix;

/**
 * Eine Adjazenzmatrix in TeX setzen.
 *
 * Um die erzeugte TeX-Ausgabe kompilieren zu können, wird das Paket
 * <a href="https://ctan.org/pkg/blkarray">blkarray</a> benötigt.
 *
 * <pre>
 * {@code
 * \[
 * \begin{blockarray}{ccccccc}
 * & A & B & C & D & E & F \\
 * \begin{block}{c(cccccc)}
 * A & 0  & 20 & 0  & 0  & 0  & 0  \\
 * B & 0  & 0  & 50 & 10 & 0  & 60 \\
 * C & 0  & 0  & 0  & 20 & 0  & 10 \\
 * D & 30 & 0  & 0  & 0  & 20 & 0  \\
 * E & 0  & 0  & 10 & 0  & 0  & 30 \\
 * F & 0  & 0  & 0  & 0  & 0  & 0  \\
 * \end{block}
 * \end{blockarray}
 * \]
 * }
 * </pre>
 */
public class TexAdjazenzMatrix {
  private GraphAdjazenzMatrix matrix;

  private int spaltenBreite;

  /**
   * z. B. c l r
   */
  private String spaltenFormatierung = "c";

  public TexAdjazenzMatrix(GraphAdjazenzMatrix matrix) {
    this.matrix = matrix;
    spaltenBreite = matrix.gibSpaltenBreite();
  }

  private String formatiereZelle(String inhalt) {
    return " " + String.format("%" + spaltenBreite + "s", inhalt) + " &";
  }

  /**
   * Erzeugt eine leere Zelle, die nicht am Ende der Zeile steht.
   *
   * @return Die Zeichen für eine leere Zelle.
   */
  private String formatiereZelle() {
    return formatiereZelle("");
  }

  private String formatiereZeile(String inhalt) {
    return inhalt.replaceFirst("&$", "\\\\\\\\\n");
  }

  /**
   * Formatiere den Matrix-Kopf. Die erste Zelle bleibt frei, dann werden die
   * Knotennamen der Reihe nach gesetzt.
   *
   * <pre>
   * {@code
   * & A & B & C & D & E & F \\
   * }
   * </pre>
   */
  private String gibMatrixKopf() {
    String ausgabe = formatiereZelle();
    for (String knotenName : matrix.gibAlleKnotenNamen()) {
      ausgabe = ausgabe + formatiereZelle(knotenName);
    }
    return ausgabe;
  }

  /**
   * Formatiere den Matrix-Kopf. Die erste Zelle bleibt frei, dann werden die
   * Knotennamen der Reihe nach gesetzt.
   *
   * <pre>
   * {@code
   * A & 0  & 20 & 0  & 0  & 0  & 0  \\
   * B & 0  & 0  & 50 & 10 & 0  & 60 \\
   * C & 0  & 0  & 0  & 20 & 0  & 10 \\
   * D & 30 & 0  & 0  & 0  & 20 & 0  \\
   * E & 0  & 0  & 10 & 0  & 0  & 30 \\
   * F & 0  & 0  & 0  & 0  & 0  & 0  \\
   * }
   * </pre>
   */
  private String gibMatrixKörper() {
    String ausgabe = "";
    for (int i = 0; i < matrix.gibKnotenAnzahl(); i++) {
      String zeile = formatiereZelle(matrix.gibKnotenName(i));
      for (int j = 0; j < matrix.gibKnotenAnzahl(); j++) {
        zeile += formatiereZelle(String.valueOf(matrix.matrix[i][j]));
      }
      ausgabe += formatiereZeile(zeile);
    }
    return ausgabe;
  }

  /**
   * Eine Adjazenzmatrix in TeX setzen.
   *
   * Um die erzeugte TeX-Ausgabe kompilieren zu können, wird das Paket
   * <a href="https://ctan.org/pkg/blkarray">blkarray</a> benötigt.
   *
   * <pre>
   * {@code
   * \begin{block}{c(cccccc)}
   * \end{block}
   * }
   * </pre>
   */
  private String gibInnereUmgebung(String inhalt) {
    String spaltenDefintion = spaltenFormatierung.repeat(matrix.gibKnotenAnzahl() - 1);
    // c(cccccc)
    spaltenDefintion = String.format("%s(%s)", spaltenFormatierung, spaltenDefintion);
    return String.format("\\begin{block}{%s}\n%s\\end{block}", spaltenDefintion, inhalt);
  }

  /**
   * Erzeuge den TeX-Code für die äußerste Umgebung.
   *
   * <pre>
   * {@code
   * \[
   * \begin{blockarray}{ccccccc}
   * \end{blockarray}
   * \]
   * }
   * </pre>
   */
  private String gibÄußereUmgebung(String inhalt) {
    String spaltenDefintion = spaltenFormatierung.repeat(matrix.gibKnotenAnzahl());
    return "\\[\n" + "\\begin{blockarray}{" + spaltenDefintion + "}\n" + inhalt + "\n" + "\\end{blockarray}\n"
        + "\\]\n";
  }

  public String gibTexAusgabe() {
    return gibÄußereUmgebung(formatiereZeile(gibMatrixKopf()) + gibInnereUmgebung(gibMatrixKörper()));
  }

  public static void main(String[] args) {
    TexAdjazenzMatrix tex = new TexAdjazenzMatrix(new GraphAdjazenzMatrix("a--b: 1; a--c: 2;a--d: 3"));
    System.out.println(tex.gibTexAusgabe());
  }

}
