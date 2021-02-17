package org.bschlangaul.aufgaben.theo_inf.regulaere_ausdruecke;

public class Takterkenner {

  private String zustand;

  public Takterkenner() {
    // Startzustand initialisieren
    zustand = "z0";
  }

  /**
   * Erkennung der Viervierteltakte
   *
   * @param Viervierteltakt als String
   *
   */
  public boolean istViervierteltakt(String eingabe) {
    boolean ergebnis = false;
    zustand = "z0";
    for (int i = 0; i < eingabe.length(); i++) {
      zustandWechseln(eingabe.charAt(i));
    }
    if (zustand == "z2") {
      ergebnis = true;
    }
    return ergebnis;
  }

  /**
   * Erkennung der Viervierteltakte
   *
   * Abbild des erkennenden Automaten über Mehrfachauswahl Fangzustand zur
   * Erkennung eines falschen Taktes über den Zustand fehler, den es im Automaten
   * nicht gibt. Dieser sollte als Erkennung eines zu großen Taktes hinzugefügt
   * werden.
   *
   * @param Notenwert der aktuellen Note des Taktes
   *
   */
  private void zustandWechseln(char zeichen) {
    switch (zustand) {
      case "z0":
        switch (zeichen) {
          case 'p':
            zustand = "z1";
            break;
          case 'v':
            zustand = "z3";
            break;
          case 'h':
            zustand = "z4";
            break;
          case 'g':
            zustand = "z2";
            break;
          default:
            zustand = "fehler";
            break;
        }
        break;
      case "z1":
        switch (zeichen) {
          case 'v':
            zustand = "z2";
            break;
          default:
            zustand = "fehler";
            break;
        }
        break;
      case "z2":
        switch (zeichen) {
          default:
            zustand = "fehler";
            break;
        }
        break;
      case "z3":
        switch (zeichen) {
          case 'p':
            zustand = "z2";
            break;
          case 'h':
            zustand = "z5";
            break;
          case 'v':
            zustand = "z4";
            break;
          default:
            zustand = "fehler";
            break;
        }
        break;
      case "z4":
        switch (zeichen) {
          case 'v':
            zustand = "z5";
            break;
          case 'h':
            zustand = "z2";
            break;
          default:
            zustand = "fehler";
            break;
        }
        break;
      case "z5":
        switch (zeichen) {
          case 'v':
            zustand = "z2";
            break;
          default:
            zustand = "fehler";
            break;
        }
        break;
      default:
        zustand = "fehler";
    }
  }
}
