package org.bschlangaul.examen.examen_66116.jahr_2014.fruehjahr.parkhaus;

public interface Parkhaus {
  public Parkplatz[] freieParkplaetze();

  public Parkplatz ersterFreierPlatz(Fahrzeug f);

  public void reservieren(Parkplatz p, Fahrzeug f);
}
