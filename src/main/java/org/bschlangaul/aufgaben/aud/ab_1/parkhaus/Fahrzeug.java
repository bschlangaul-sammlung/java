package org.bschlangaul.aufgaben.aud.ab_1.parkhaus;

import java.util.Date;

public class Fahrzeug {
  @SuppressWarnings("unused")
  private String kennzeichen;
  private double laenge;
  private double breite;
  private double hoehe;
  @SuppressWarnings("unused")
  private Date erstzulassung;

  public Fahrzeug(String kennzeichen, double laenge, double breite, double hoehe, Date erstzulassung) {
    this.kennzeichen = kennzeichen;
    this.laenge = laenge;
    this.breite = breite;
    this.hoehe = hoehe;
    this.erstzulassung = erstzulassung;
  }

  public double gibLaenge() {
    return laenge;
  }

  public double gibBreite() {
    return breite;
  }

  public double gibHoehe() {
    return hoehe;
  }

}
