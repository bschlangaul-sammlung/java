package org.bschlangaul.graph.report;

import org.bschlangaul.helfer.report.Reporter;

/**
 * Momentan noch ohne Funktion. Kann durch Graphenspezifisches Code aufgefüllt
 * werden.
 */
public class GraphReporter extends Reporter {
  /**
   * Gib Ausgleichsleerzeichen, die vorne oder hinten an den Knotennamen angehängt
   * werden können, sodass die Textausgabe in der Konsole schöne ausgerichtet ist.
   *
   * @param name Der Name des Knoten.
   *
   * @return 0 oder mehr Leerzeichen.
   */
  public static String gibLeerzeichen(String name, int maxKnotenTextbreite) {
    int anzahl = maxKnotenTextbreite - name.length();
    if (anzahl > 0) {
      return " ".repeat(anzahl);
    }
    return "";
  }
}
