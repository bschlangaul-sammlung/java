package org.bschlangaul.examen.examen_66116.jahr_2014.fruehjahr.parkhaus;

public interface Parkhaus {
  public Parkplatz[] gibFreiePlaetze();

  public Parkplatz gibErstenFreienPlatz(Fahrzeug fahrzeug);

  public void reserviere(Parkplatz parkhaus, Fahrzeug fahrzeug);
}
