package org.bschlangaul.examen.examen_66116.jahr_2014.fruehjahr.parkhaus;

public class Parkplatz {
  public double laenge;
  public double breite;
  public double hoehe;
  public boolean frei;
  public boolean reserviert;

  public Parkplatz(double laenge, double breite, double hoehe) {
    this.laenge = laenge;
    this.breite = breite;
    this.hoehe = hoehe;
    frei = true;
    reserviert = false;
  }

  public boolean pruefeGroesse(Fahrzeug fahrzeug) {
    if (fahrzeug.gibLaenge() < this.laenge && fahrzeug.gibBreite() < this.breite && fahrzeug.gibHoehe() < this.hoehe) {
      return true;
    }
    return false;
  }
}
