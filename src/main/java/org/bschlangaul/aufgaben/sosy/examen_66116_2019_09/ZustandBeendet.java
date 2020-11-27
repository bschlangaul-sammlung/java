package org.bschlangaul.aufgaben.sosy.examen_66116_2019_09;

/**
 * Entspricht der „ConcreteState“-Unterklasse in der Terminologie der Gang of
 * Four.
 */
public class ZustandBeendet extends ProzessZustand {

  public ZustandBeendet(Prozess prozess) {
    super("beendet", prozess);
  }

}
