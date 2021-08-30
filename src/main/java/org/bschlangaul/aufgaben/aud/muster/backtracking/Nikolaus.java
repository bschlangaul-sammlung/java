package org.bschlangaul.aufgaben.aud.muster.backtracking;

/**
 * Qualifizierungsmaßnahme Informatik: Algorithmen und Datenstrukturen:
 * Aufgabenblatt 3: Algorithmenmuster.
 *
 * <a href="https://www.studon.fau.de/file2521908_download.html">Angabe: AB_3
 * Greedy_DP_Backtracking.pdf</a>
 * <a href="https://www.studon.fau.de/file2521907_download.html">Lösung: AB_3
 * Greedy_DP_Backtracking_Lsg.pdf</a>
 */
public class Nikolaus {
  static final int maxPunktAnzahl = 5;
  static final int maxKantenAnzahl = 8;
  static boolean[][] kanteZulässig;
  static boolean[][] kanteGezeichnet;
  static int[] lösungsWeg;
  static int aktuelleKantenAnzahl = 0;
  static int lösungsAnzahl = 0;

  /**
   * Zulässige Kanten für das „Haus des Nikolaus“ eintragen. Der Nummerierung
   * liegt das Bild in main zu Grunde. Eine Anpassung an andere Graphen ist leicht
   * möglich.
   */
  static void initialsiereFelder() {
    kanteZulässig = new boolean[maxKantenAnzahl + 1][maxKantenAnzahl + 1];
    kanteGezeichnet = new boolean[maxKantenAnzahl + 1][maxKantenAnzahl + 1];
    lösungsWeg = new int[maxKantenAnzahl + 2]; // mit Startpunkt
    // Erst mal alles auf false ;
    for (int i = 1; i <= maxPunktAnzahl; i++) {
      for (int k = 1; k <= maxPunktAnzahl; k++) {
        kanteZulässig[i][k] = false;
        kanteGezeichnet[i][k] = false;
      }
    }

    kanteZulässig[1][2] = true; // von 1 nach 2 zulässig
    kanteZulässig[2][1] = true;

    kanteZulässig[1][3] = true;
    kanteZulässig[3][1] = true;

    kanteZulässig[1][5] = true;
    kanteZulässig[5][1] = true;

    kanteZulässig[2][3] = true;
    kanteZulässig[3][2] = true;

    kanteZulässig[2][5] = true;
    kanteZulässig[5][2] = true;

    kanteZulässig[3][4] = true;
    kanteZulässig[4][3] = true;

    kanteZulässig[3][5] = true;
    kanteZulässig[5][3] = true;

    kanteZulässig[4][5] = true;
    kanteZulässig[5][4] = true;
    for (int i = 0; i <= maxKantenAnzahl; i++) {
      lösungsWeg[i] = 0;
    }
  }

  static void zeichneKante(final int von, final int nach) {
    kanteGezeichnet[von][nach] = true;
    kanteGezeichnet[nach][von] = true;
    // Anzahl bereits gezeichneter Kanten erhöhen
    aktuelleKantenAnzahl++;
    // neuen Wegpunkt in Lösung aufnehmen
    lösungsWeg[aktuelleKantenAnzahl] = nach;
  }

  static void löscheKante(final int von, final int nach) {
    kanteGezeichnet[von][nach] = false;
    kanteGezeichnet[nach][von] = false;
    aktuelleKantenAnzahl--;
  }

  static boolean fertig() {
    return (aktuelleKantenAnzahl == maxKantenAnzahl);
  }

  static void gibLösungAus() {
    for (int i = 0; i <= maxKantenAnzahl; i++) {
      System.out.print(lösungsWeg[i]);
      System.out.print(" ");
      lösungsAnzahl++;
      if (lösungsAnzahl % 8 == 0) {
        System.out.println();
      }
    }
  }

  static void versucheKanteZuZeichnen(final int start) {
    for (int ziel = 1; ziel <= maxPunktAnzahl; ziel++) {
      if (kanteZulässig[start][ziel] && !kanteGezeichnet[start][ziel]) {
        zeichneKante(start, ziel);
        if (!fertig()) {
          versucheKanteZuZeichnen(ziel);
        } else {
          gibLösungAus();
        }
        löscheKante(start, ziel);
      }
    }
  }

  public static void main(final String[] arg) {
    initialsiereFelder();
    System.out
        .println("Das Programm bestimmt alle Lösungen des Problems, das Haus des Nikolaus in einem Zug zu zeichnen.");
    System.out.println("      4      ");
    System.out.println("     . .     ");
    System.out.println("    .   .    ");
    System.out.println("   5-----3   ");
    System.out.println("   |.   .|   ");
    System.out.println("   | . . |   ");
    System.out.println("   | . . |   ");
    System.out.println("   |.   .|   ");
    System.out.println("   1-----2   ");
    for (int punktNr = 1; punktNr <= maxPunktAnzahl; punktNr++) {
      lösungsWeg[0] = punktNr;
      versucheKanteZuZeichnen(punktNr);
    }
    System.out.println();
    System.out.println("Es ergaben sich " + lösungsAnzahl + " Lösungen.");
  }
}
