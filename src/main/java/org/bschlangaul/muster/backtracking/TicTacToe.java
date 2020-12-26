package org.bschlangaul.muster.backtracking;

public class TicTacToe {

  static boolean zeileGewonnen(final char[][] feld, final int z, final char spieler) {
    return true;
  }

  static boolean spalteGewonnen(final char[][] feld, final int z, final char spieler) {
    return true;

  }

  static boolean diagonaleGewonnen(final char[][] feld, final char spieler) {
    return true;
  }

  public static boolean freiesFeld(final char[][] feld) {
    return true;
  }

  public static char wechsel(final char spieler) {
    return 'x';
  }

  /**
   * Saake Seite 212
   *
   * @param feld
   * @param spieler
   *
   * @return
   */
  static boolean gewonnen(final char[][] feld, final char spieler) {
    for (int z = 0; z < 3; z++) {
      if (zeileGewonnen(feld, z, spieler) || spalteGewonnen(feld, z, spieler))
        return true;
    }
    return diagonaleGewonnen(feld, spieler);
  }

  /**
   * Saake Seite 229-230
   *
   * @param feld
   * @param spieler
   * @param pos
   * @param tiefe
   *
   * @return
   */
  int waehleZug(final char[][] feld, final char spieler, final int[] pos, final int tiefe) {
    // Spieler o = Computer: schlechtester Wert als Startwert
    int besterWert = (spieler == 'o' ? -100 : 100);
    int bz = -1, bs = -1;

    // Ende des Spiels erreicht? Was ist passiert?
    if (gewonnen(feld, 'o'))
      return 100; // gewonnen
    else if (gewonnen(feld, 'x'))
      return -100; // verloren
    else if (!freiesFeld(feld))
      return 0; // kein freies Feld -> Unentschieden
    // Erweiterung der Konfiguration durch alle möglichen Züge
    for (int z = 0; z < 3; z++) {
      for (int s = 0; s < 3; s++) {
        if (feld[z][s] == ' ') {
          // Feld ist frei -> Zug ausführen
          feld[z][s] = spieler;
          // nächsten Zug des Gegners untersuchen
          // (dafür Spieler wechseln)
          final int wert = waehleZug(feld, wechsel(spieler), pos, tiefe + 1) * (10 - tiefe); // Züge bis Spielende
          // Zug zurücknehmen
          feld[z][s] = ' ';
          // Ist dieser Spielzug der bisher beste?
          if ((spieler == 'o' && besterWert < wert) || (spieler == 'x' && besterWert > wert)) {
            // wenn ja, dann merken!
            bz = z;
            bs = s;
            besterWert = wert;
          }
        }
      }
    }
    // beste Position zurückgeben
    pos[0] = bz;
    pos[1] = bs;
    return besterWert;
  }

  /**
   * Saake Seite 210-211
   *
   * @param args Kommandozeilenargumente.
   */
  public static void main(final String[] args) {
    final char spieler = 'x';
    boolean fertig = false;
    final char spielfeld[][] = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

    final int z = 0;
    final int s = 0;

    do {
      if (!freiesFeld(spielfeld)) {

        System.out.println("Unentschieden!");
        fertig = true;
      } else {
        // Zug z, s eingeben
        if (spielfeld[z][s] == ' ') {
          // Zug ist gültig
          spielfeld[z][s] = spieler;
          // auf Sieg testen
        } else
          System.out.println("Zug ungültig!");
      }
    } while (!fertig);
  }
}
