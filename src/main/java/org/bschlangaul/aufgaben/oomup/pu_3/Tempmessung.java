package org.bschlangaul.aufgaben.oomup.pu_3;

public class Tempmessung {
  /**
   * Deklaration des Feldes temperatur vom Typ double
   */
  private double[] temperatur;

  /**
   * Erzeugung des Feldes mit 365 Plätzen für eine Zahl vom Typ double
   */
  public Tempmessung() {
    temperatur = new double[365];
  }

  /**
   * Das Feld wird mit 365 zufälligen Kommazahlen belegt. Der Index des Feldes
   * startet bei 0 und endet bei 364. Es wird eine zufällige Zahl zwischen -20
   * Grad und kleiner 45 Grad erzeugt und abgespeichert.
   */
  public void fuellenMitZufallszahlen() {
    for (int i = 0; i < 365; i++) {
      temperatur[i] = 65 * Math.random() - 20;
    }
  }

  /**
   * Teilaufgabe b: Gibt den Index der Feld-Abteilung mit der höchsten Temperatur
   * aus. In dieser Variable wird der Index des aktuell heissesten Tages
   * gespeichert. Jede Abteilung im Feld wird durchlaufen. Wenn die Temperatur an
   * der aktuellen Feldstelle höher ist, als die bisher gefundene maximale
   * Temperatur. Speichere die aktuell höchste Temperatur in maxTemp ab. Merke dir
   * den Index, um später diesen als heissesten Tag ausgeben zu können.
   *
   * @return Der heisseste Tag als Zahl (0 ist der erste Tag des Jahres.)
   */
  public int gibHeissesterTag() {
    int maxTag = 0;
    double maxTemp = temperatur[0];

    for (int i = 0; i < 365; i++) {
      if (temperatur[i] > maxTemp) {
        maxTemp = temperatur[i];
        maxTag = i;
      }
    }

    return maxTag;
  }

  /**
   * analog zu gibHeissesterTag()
   *
   * @return Der kälteste Tag als Zahl (0 ist der erste Tag des Jahres.)
   */
  public int gibKaeltesterTag() {
    int minTag = 0;
    double minTemp = temperatur[0];

    for (int i = 0; i < 365; i++) {
      if (temperatur[i] < minTemp) {
        minTemp = temperatur[i];
        minTag = i;
      }
    }

    return minTag;
  }

  /**
   * Hier werden alle Temperaturen, die im Feld abgespeichert sind, addiert. Die
   * erhaltene Summe wird durch die Anzahl der Tage geteilt und somit die
   * Durchschnittstemperatur zurückgegeben.
   *
   * @return Die Durschnittstemperatur.
   */
  public double berechneDurchschnitt() {
    double summe = 0;

    for (int i = 0; i < 365; i++) {
      summe = summe + temperatur[i];
    }
    return summe / 365;
  }
}
