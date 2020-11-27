package org.bschlangaul.aufgaben.sosy.examen_66116_2019_09;

/**
 * Entspricht der „ConcreteState“-Unterklasse in der Terminologie der Gang of
 * Four.
 */
public class ZustandAbgebrochen extends ProzessZustand {

  public ZustandAbgebrochen(Prozess prozess) {
    super("abgebrochen", prozess);
  }

}
