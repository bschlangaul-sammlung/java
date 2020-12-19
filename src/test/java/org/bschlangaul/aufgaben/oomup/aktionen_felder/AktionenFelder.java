package org.bschlangaul.aufgaben.oomup.aktionen_felder;

public class AktionenFelder {
  public double[] feld = { 1.7, 5.2, 8.4, 2.2 };

  public double berechneMittelwert() {
    double summe = 0;
    for (int i = 0; i < feld.length; i++) {
      summe = summe + feld[i];
    }
    return summe / feld.length;
  }

  public double berechneMaximalwert() {
    double max = feld[0];
    for (int i = 0; i < feld.length; i++) {
      double aktuelleZahl = feld[i];
      if (aktuelleZahl > max) {
        max = aktuelleZahl;
      }
    }
    return max;
  }

  public double berechneMinimalwert() {
    double min = feld[0];
    for (int i = 0; i < feld.length; i++) {
      double aktuelleZahl = feld[i];
      if (aktuelleZahl < min) {
        min = aktuelleZahl;
      }
    }
    return min;
  }

  public void sortiere() {
    boolean aenderung = false;
    do {
      aenderung = false;
      for (int i = 0; i < feld.length - 1; i++) {
        if (feld[i] > feld[i + 1]) {
          feld[i] = feld[i + 1];
          feld[i + 1] = feld[i];
          aenderung = true;
        }
      }
    } while (aenderung);
  }
}
