package org.bschlangaul.aufgaben.aud.ab_1.parkhaus;

public interface Parkhaus {
  public Parkplatz[] freieParkplaetze();

  public Parkplatz ersterFreierPlatz(Fahrzeug f);

  public void reservieren(Parkplatz p, Fahrzeug f);
}
