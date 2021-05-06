package org.bschlangaul.examen.examen_66116.jahr_2019.herbst;

/**
 * Entspricht der „KonkreterZustand“-Unterklasse in der Terminologie der „Gang of
 * Four“.
 */
public class ZustandBereit extends ProzessZustand {

  public ZustandBereit(Prozess prozess) {
    super("bereit", prozess);
  }

  public void starten() {
    System.out.println("Der Prozess wird gestartet.");
    prozess.setzeZustand(new ZustandAktiv(prozess));
  }
}
