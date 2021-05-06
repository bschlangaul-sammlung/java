package org.bschlangaul.examen.examen_66116.jahr_2019.herbst;

/**
 * Entspricht der „KonkreterZustand“-Unterklasse in der Terminologie der “Gang of
 * Four”.
 */
public class ZustandAbgebrochen extends ProzessZustand {

  public ZustandAbgebrochen(Prozess prozess) {
    super("abgebrochen", prozess);
  }
}
