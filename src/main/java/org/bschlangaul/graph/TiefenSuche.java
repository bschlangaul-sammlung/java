package org.bschlangaul.graph;

/**
 * nach Schulbuch: Informatik 1 Oberstufe Oldenbourg Verlag
 */
public class TiefenSuche extends GraphAdjazenzMatrix {
  private boolean[] besucht;

  public TiefenSuche(int maximaleKnoten) {
    super(maximaleKnoten);
    besucht = new boolean[maximaleKnoten];
  }

  public void besucheKnoten(int knotenNummer) {
    besucht[knotenNummer] = true;

    System.out.println(knoten[knotenNummer].gibName() + ";");

    for (int abzweigung = 0; abzweigung <= anzahlKnoten - 1; abzweigung++) {
      if (matrix[knotenNummer][abzweigung] > 0 && !besucht[abzweigung]) {
        besucheKnoten(abzweigung);
      }
    }
    System.out.println(knoten[knotenNummer].gibName() + "  fertig");
  }

  public void führeTiefenSucheAus(String startKnoten) {
    int startnummer;
    startnummer = gibKnotenNummer(startKnoten);

    if (startnummer != -1) {
      for (int i = 0; i <= anzahlKnoten - 1; i++) {
        besucht[i] = false;
      }
      besucheKnoten(startnummer);
    }
  }

  public static void main(String[] args) {
    TiefenSuche tiefensuche = new TiefenSuche(6);

    tiefensuche.setzeKnoten("0");
    tiefensuche.setzeKnoten("1");
    tiefensuche.setzeKnoten("2");
    tiefensuche.setzeKnoten("3");
    tiefensuche.setzeKnoten("4");
    tiefensuche.setzeKnoten("5");

    tiefensuche.setzeUngerichteteKante("0", "1", 1);
    tiefensuche.setzeUngerichteteKante("0", "3", 1);
    tiefensuche.setzeUngerichteteKante("0", "4", 1);
    tiefensuche.setzeUngerichteteKante("2", "3", 1);
    tiefensuche.setzeUngerichteteKante("2", "4", 1);
    tiefensuche.setzeUngerichteteKante("2", "5", 1);
    tiefensuche.setzeUngerichteteKante("3", "4", 1);

    tiefensuche.gibMatrixAus();

    tiefensuche.führeTiefenSucheAus("5");
  }
}
