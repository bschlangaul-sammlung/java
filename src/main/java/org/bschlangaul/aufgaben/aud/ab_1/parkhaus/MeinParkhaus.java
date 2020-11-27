package org.bschlangaul.aufgaben.aud.ab_1.parkhaus;

public abstract class MeinParkhaus implements Parkhaus {

  public abstract Parkplatz[] freieParkplaetze();

  public Parkplatz ersterFreierPlatz(Fahrzeug f) {
    Parkplatz freierPlatz = null;
    Parkplatz[] freiePlaetze = freieParkplaetze();
    for (int i = 0; i < freiePlaetze.length; i++) {
      Parkplatz p = freiePlaetze[i];
      if (p.groessePruefen(f)) {
        freierPlatz = p;
      }
    }
    return freierPlatz;
  }

  public abstract void reservieren(Parkplatz p, Fahrzeug f);
}
