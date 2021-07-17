package org.bschlangaul.examen.examen_66116.jahr_2014.fruehjahr.parkhaus;

public abstract class MeinParkhaus implements Parkhaus {

  public abstract Parkplatz[] gibFreiePlaetze();

  public Parkplatz gibErstenFreienPlatz(Fahrzeug fahrzeug) {
    Parkplatz freierPlatz = null;
    Parkplatz[] freiePlaetze = gibFreiePlaetze();
    for (int i = 0; i < freiePlaetze.length; i++) {
      Parkplatz parkplatz = freiePlaetze[i];
      if (parkplatz.pruefeGroesse(fahrzeug)) {
        freierPlatz = parkplatz;
      }
    }
    return freierPlatz;
  }

  public abstract void reserviere(Parkplatz parkplatz, Fahrzeug fahrzeug);
}
