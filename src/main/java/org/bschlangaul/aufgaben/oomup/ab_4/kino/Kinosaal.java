package org.bschlangaul.aufgaben.oomup.ab_4.kino;

/**
 * Beschreiben Sie hier die Klasse Kinosaal.
 *
 * @author (Hermine Bschlangaul)
 * @version (2020-02-05)
 */
public class Kinosaal {
  private int sitzplatzAnzahl = 180;
  private Sitzplatz[] sitzplaetze;
  public int nummer;

  public Kinosaal(int nummer) {
    this.nummer = nummer;
    sitzplaetze = new Sitzplatz[sitzplatzAnzahl];
    for (int i = 0; i < sitzplatzAnzahl; i++) {
      sitzplaetze[i] = new Sitzplatz(i + 1);
    }
  }

  public Sitzplatz gibSitzplatzBeiNummer(int sitzplatzNummer) {
    return sitzplaetze[sitzplatzNummer - 1];
  }

  /**
   * @param sitzplatzNr Die Sitzplatznummer.
   * @param buchungsNr Die Buchungsnummer.
   *
   * @return Wahr bei erfolgreicher Buchung, ansonsten falsch.
   */
  public boolean sitzplatzReservieren(int sitzplatzNr, int buchungsNr) {
    if (sitzplatzNr > 180 || sitzplatzNr < 1) {
      System.out
          .println("Leider besitzt der Kinosaal " + nummer + " keinen Sitzplatz mit der Nummer " + sitzplatzNr + ".");
      return false;
    }
    Sitzplatz sitzplatz = sitzplaetze[sitzplatzNr - 1];
    return sitzplatz.reservieren(buchungsNr);
  }
}
