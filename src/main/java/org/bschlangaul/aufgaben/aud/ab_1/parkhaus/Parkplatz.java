package org.bschlangaul.aufgaben.aud.ab_1.parkhaus;

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

  public boolean groessePruefen(Fahrzeug f) {
    if (f.gibLaenge() < this.laenge && f.gibBreite() < this.breite && f.gibHoehe() < this.hoehe) {
      return true;
    }
    return false;
  }

}
