package org.bschlangaul.aufgaben.oomup.ab_4.kino;

public class Sitzplatz {
  private boolean reserviert;
  private int buchungsNr;
  public int nummer;

  public Sitzplatz(int nummer) {
    buchungsNr = 0;
    reserviert = false;
    this.nummer = nummer;
  }

  public boolean reservieren(int buchungsnummer) {
    if (reserviert || buchungsNr > 0) {
      return false;
    }
    buchungsNr = buchungsnummer;
    return true;
  }

  public void besetzen() {
    buchungsNr = 0;
  }
}
