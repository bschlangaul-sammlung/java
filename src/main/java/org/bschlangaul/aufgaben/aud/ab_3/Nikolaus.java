package org.bschlangaul.aufgaben.aud.ab_3;

public class Nikolaus {

  static final int maxPunktAnz = 5;
  static final int maxKantenAnz = 8;
  static boolean[][] kanteZulaessig;
  static boolean[][] kanteGezeichnet;
  static int[] loesungsWeg;
  static int aktKantenAnzahl = 0;
  static int loesungsAnzahl = 0;

  static void initArrays() {
    kanteZulaessig = new boolean[maxKantenAnz + 1][maxKantenAnz + 1];
    kanteGezeichnet = new boolean[maxKantenAnz + 1][maxKantenAnz + 1];
    loesungsWeg = new int[maxKantenAnz + 2]; // mit Startpunkt
    // Erst mal alles auf false ;
    for (int i = 1; i <= maxPunktAnz; i++) {
      for (int k = 1; k <= maxPunktAnz; k++) {
        kanteZulaessig[i][k] = false;
        kanteGezeichnet[i][k] = false;
      }
    }
    /*
     * Zulaessige Kanten fuer das " Haus des Nikolaus " eintragen . Der Nummerierung
     * liegt das Bild in main zu Grunde . Eine Anpassung 2an andere Graphen ist
     * leicht moeglich .
     */
    kanteZulaessig[1][2] = true; // von 1 nach 2 zulaessig
    kanteZulaessig[1][3] = true;
    kanteZulaessig[1][5] = true;
    kanteZulaessig[2][1] = true;
    kanteZulaessig[2][3] = true;
    kanteZulaessig[2][5] = true;
    kanteZulaessig[3][1] = true;
    kanteZulaessig[3][2] = true;
    kanteZulaessig[3][4] = true;
    kanteZulaessig[3][5] = true;
    kanteZulaessig[4][3] = true;
    kanteZulaessig[4][5] = true;
    kanteZulaessig[5][1] = true;
    kanteZulaessig[5][2] = true;
    kanteZulaessig[5][3] = true;
    kanteZulaessig[5][4] = true;
    for (int i = 0; i <= maxKantenAnz; i++) {
      loesungsWeg[i] = 0;
    }
  }

  static void zeichneKante(final int von, final int nach) {
    kanteGezeichnet[von][nach] = true;
    kanteGezeichnet[nach][von] = true;
    aktKantenAnzahl++;
    // Anzahl bereits gezeichneter Kanten erhoehen
    loesungsWeg[aktKantenAnzahl] = nach; // neuen Wegpunkt in Loesung aufnehmen
  }

  static void loescheKante(final int von, final int nach) {
    kanteGezeichnet[von][nach] = false;
    kanteGezeichnet[nach][von] = false;
    aktKantenAnzahl--;
  }

  static boolean fertig() {
    return (aktKantenAnzahl == maxKantenAnz);
  }

  static void loesungAusgeben() {
    for (int i = 0; i <= maxKantenAnz; i++) {
      System.out.print(loesungsWeg[i]);
      System.out.print(" ");
      loesungsAnzahl++;
      if (loesungsAnzahl % 5 == 0) {
        System.out.println();
      }
    }
  }

  static void versucheKanteZuZeichnen(final int start) {
    for (int ziel = 1; ziel <= maxPunktAnz; ziel++) {
      if (kanteZulaessig[start][ziel] && !kanteGezeichnet[start][ziel]) {
        zeichneKante(start, ziel);
        if (!fertig()) {
          versucheKanteZuZeichnen(ziel);
        } else {
          loesungAusgeben();
        }
        loescheKante(start, ziel);
      }
    }
  }

  public static void main(final String[] arg) {
    initArrays();
    System.out.println(
        " Das Programm bestimmt alle Loesungen des Problems, das Haus des Nikolaus in einem Zug zu zeichnen . ");
    System.out.println("      4      ");
    System.out.println("     . .     ");
    System.out.println("    .   .    ");
    System.out.println("   5-----3   ");
    System.out.println("   |.   .|   ");
    System.out.println("   | . . |   ");
    System.out.println("   | . . |   ");
    System.out.println("   |.   .|   ");
    System.out.println("   1-----2   ");
    for (int punktNr = 1; punktNr <= maxPunktAnz; punktNr++) {
      loesungsWeg[0] = punktNr;
      versucheKanteZuZeichnen(punktNr);
    }
    System.out.println();
    System.out.println(" Es ergaben sich " + loesungsAnzahl + " Loesungen.");
  }
}
