package org.bschlangaul.aufgaben.sosy.examen_66116_2019_09;

/**
 * Entspricht der „ConcreteState“-Unterklasse in der Terminologie der Gang of
 * Four.
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
