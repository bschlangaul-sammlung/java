package org.bschlangaul.graph;

/**
 * Klasse für einen ungerichteten, gewichteten Graphen.
 *
 * Als Datenstruktur wird eine Adjazenzmatrix verwendet.
 *
 * nach Schulbuch: Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class GraphAdjazenzMatrix extends Graph {

  /**
   * Das zweidimensionale Feld der Adjazenzmatrix.
   */
  public double[][] matrix;

  /**
   * Konstruktor für Objekte der Klasse GraphAdjazenzMatrix.
   *
   * Die maximale Anzahl der Knoten wird dabei festgelegt.
   *
   * @param maximaleKnotenAnzahl Anzahl der maximal möglichen Knoten
   */
  public GraphAdjazenzMatrix(int maximaleKnotenAnzahl) {
    initialisiere(maximaleKnotenAnzahl);
  }

  /**
   * Mit diesem Konstruktur kann eine Adjazenzmatrix durch das einfache
   * Graphenformat erzeugt werden.
   *
   * @param graphenFormat Ein String im einfachen Graphenformat.
   */
  public GraphAdjazenzMatrix(String graphenFormat) {
    super(graphenFormat);
  }

  @Override
  protected void initialisiere(int maximaleAnzahlKnoten) {
    matrix = new double[maximaleAnzahlKnoten][maximaleAnzahlKnoten];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = -Double.MAX_VALUE;
      }
    }
  }

  /**
   * Einfügen eines neuen Knoten in den Graphen.
   *
   * Wenn die maximale Anzahl an Knoten erreicht wird oder der Knoten bereits
   * eingefügt ist, dann erfolgt kein Einfügen. Ob der Knoten bereits eingefügt
   * ist, findet man mit der Methode gibKnotenNummer(name) heraus.
   *
   * @param knotenName Name des neuen Knotens, der dem Graphen hinzugefügt wird.
   */
  public int setzeKnoten(String knotenName) {
    int knotenNummer = gibKnotenNummer(knotenName);
    if (knotenNummer > -1)
      return knotenNummer;
    knotenNummer = super.setzeKnoten(knotenName);
    return knotenNummer;
  }

  /**
   * Berechne das kleinste Einzel-Kantengewicht aller Kanten.
   *
   * Diese Methode ist nützlich für die negativen Zahlen. Dieser Wert ist z. B.
   * nützlich, wenn die Adjazenz-Matrix in der Konsole ausgegeben werden soll. Mit
   * Hilfe dieses Wertes kann die Breite der Tabelle bestimmt werden.
   *
   * @return Das Gewicht der Kante mit dem Minimalgewicht.
   */
  public double gibMinimalesGewicht() {
    double min = 0;
    for (double[] reihe : matrix) {
      for (double gewicht : reihe) {
        if (gewicht < min && gewicht != -Double.MAX_VALUE)
          min = gewicht;
      }
    }
    return min;
  }

  /**
   * Berechne das größte Einzel-Kantengewicht aller Kanten.
   *
   * Dieser Wert ist z. B. nützlich, wenn die Adjazenz-Matrix in der Konsole
   * ausgegeben werden soll. Mit Hilfe dieses Wertes kann die Breite der Tabelle
   * bestimmt werden.
   *
   * @return Das Gewicht der Kante mit dem Maximalgewicht.
   */
  public double gibMaximalesGewicht() {
    double max = 0;
    for (double[] reihe : matrix) {
      for (double gewicht : reihe) {
        if (gewicht > max)
          max = gewicht;
      }
    }
    return max;
  }

  /**
   * Füge eine Kante ein. Diese Methode ist in der allgemeinsten Form. Es gibt
   * weitere Methoden, die auf diese Methode aufbauen, die weniger Argumente
   * benötigen.
   *
   * Eine Kante ist durch einen Anfangsknoten und einen Endknoten festgelegt und
   * hat eine Gewichtung.
   *
   * @param von       Name des Anfangsknotens
   * @param nach      Name des Endknotens
   * @param gewicht   Gewichtung der Kante als Ganzzahl
   * @param gerichtet Ob die Kante gerichtet oder ungerichtet ist. Ist die Kante
   *                  ungerichtet werden zwei Einträge in die Matrix gesetzt.
   */
  public void setzeKante(String von, String nach, double gewicht, boolean gerichtet) {
    int vonNummer, nachNummer;
    vonNummer = gibKnotenNummer(von);
    nachNummer = gibKnotenNummer(nach);
    if (vonNummer != -1 && nachNummer != -1) {
      matrix[vonNummer][nachNummer] = gewicht;
      if (!gerichtet)
        matrix[nachNummer][vonNummer] = gewicht;
    }
  }

  /**
   * Gibt die Adjazenzmatrix des Graphen in der Konsole formatiert aus.
   *
   * Matrixelemente, die mit -1 belegt sind, werden als Leerzeichen ausgegeben.
   * Als Spaltenbreite wurde hier 4 Zeichen gewählt.
   */
  public void gibMatrixAus() {
    int breite = 4;
    // Kopfzeile
    System.out.print("    ");
    for (int i = 0; i < gibKnotenAnzahl(); i++)
      System.out.print(gibKnoten(i).gibNameFormatiert(breite));
    System.out.println();

    for (int i = 0; i < gibKnotenAnzahl(); i++) {
      System.out.print(gibKnoten(i).gibNameFormatiert(breite));
      for (int j = 0; j < gibKnotenAnzahl(); j++)
        if (matrix[i][j] == -Double.MAX_VALUE) {
          System.out.print("-   ");
        } else if (matrix[i][j] != -1)
          System.out.print((matrix[i][j] + "   ").substring(0, breite));
        else
          System.out.print("    ");
      System.out.println();
    }
  }

  /**
   * Gib die Gewichtung einer Kante. Die Kante ist durch einen Anfangsknoten und
   * einen Endknoten festgelegt. Ist die Kante unbekannt, wird -1 ausgegeben.
   *
   * @param von  Name des Anfangsknotens
   * @param nach Name des Endknotens
   *
   * @return Gewichtung der Kante
   */
  double gibKanteGewicht(String von, String nach) {
    int vonNummer, nachNummer;

    vonNummer = gibKnotenNummer(von);
    nachNummer = gibKnotenNummer(nach);
    if ((vonNummer != -1) && (nachNummer != -1))
      return matrix[vonNummer][nachNummer];
    else
      return -1;
  }

  public static void main(String[] args) {
    GraphAdjazenzMatrix matrix = new GraphAdjazenzMatrix(20);

    matrix.setzeKnoten("A");
    matrix.setzeKnoten("B");
    matrix.setzeKnoten("C");
    matrix.setzeKnoten("D");
    matrix.setzeKnoten("E");
    matrix.setzeKnoten("F");
    matrix.setzeKnoten("G");
    matrix.setzeKnoten("H");
    matrix.setzeKnoten("J");
    matrix.setzeKnoten("K");

    matrix.setzeUngerichteteKante("A", "B", 1);
    matrix.setzeUngerichteteKante("A", "C", 1);

    matrix.setzeUngerichteteKante("B", "A", 1);
    matrix.setzeUngerichteteKante("B", "D", 1);
    matrix.setzeUngerichteteKante("B", "E", 1);

    matrix.setzeUngerichteteKante("C", "A", 1);
    matrix.setzeUngerichteteKante("C", "F", 1);
    matrix.setzeUngerichteteKante("C", "G", 1);

    matrix.setzeUngerichteteKante("D", "B", 1);
    matrix.setzeUngerichteteKante("D", "H", 1);

    matrix.setzeUngerichteteKante("E", "B", 1);
    matrix.setzeUngerichteteKante("E", "F", 1);

    matrix.setzeUngerichteteKante("F", "C", 1);
    matrix.setzeUngerichteteKante("F", "E", 1);
    matrix.setzeUngerichteteKante("F", "G", 1);
    matrix.setzeUngerichteteKante("F", "J", 1);

    matrix.setzeUngerichteteKante("G", "C", 1);
    matrix.setzeUngerichteteKante("G", "F", 1);

    matrix.setzeUngerichteteKante("H", "D", 1);

    matrix.setzeUngerichteteKante("J", "F", 1);

    matrix.setzeUngerichteteKante("K", "F", 1);

    matrix.gibMatrixAus();
  }
}
