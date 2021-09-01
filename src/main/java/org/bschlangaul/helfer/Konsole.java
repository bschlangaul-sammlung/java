package org.bschlangaul.helfer;

/**
 * Zeige Ergebnisse in der Textkonsole (Terminal)
 */
public class Konsole {

  /**
   * Zeigt den Format-String in der Textkonsole.
   *
   * @param format  Ein Text der an String.format übergeben wird.
   * @param eingang Ein Object das für String.format gebraucht wird.
   */
  private static void zeige(String format, Object eingang) {
    System.out.print(String.format(format, eingang));
  }

  public static void zeige2DIntFeld(int[][] feld) {
    int max = 0;
    for (int i = 0; i < feld.length; i++) {
      for (int j = 0; j < feld[i].length; j++) {
        if (feld[i][j] > max)
          max = feld[i][j];
      }
    }
    if (feld.length > max)
      max = feld.length;
    if (feld[0].length > max)
      max = feld[0].length;

    // + 1: Ein Leerzeichen zwischen den Zahlen.
    int anzahlStellen = String.valueOf(max).length() + 1;
    String format = "%" + anzahlStellen + "s";
    zeige(format, "x");

    // Spaltenbeschriftung
    for (int i = 0; i < feld[0].length; i++) {
      zeige(format, i);
    }
    System.out.println();

    for (int i = 0; i < feld.length; i++) {
      // Zeilenbeschriftung
      zeige(format, i);
      for (int j = 0; j < feld[i].length; j++) {
        zeige(format, feld[i][j]);
      }
      System.out.println();
    }
  }

  /**
   * Zeige eine Feld voller Zahlen in der Konsole.
   *
   * @param zahlen Eine Feld gefüllt mit Zahlen, das in der Konsole gezeigt werden
   *               soll.
   */
  public static void zeigeZahlenFeld(int[] zahlen) {
    String output = "";
    for (int i = 0; i < zahlen.length; i++) {
      output = output + zahlen[i] + " ";
    }
    System.out.println(output);
  }
}
