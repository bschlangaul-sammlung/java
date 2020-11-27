package org.bschlangaul.aufgaben.sosy.examen_66116_2019_09;

/**
 * Entspricht der „ConcreteState“-Unterklasse in der Terminologie der Gang of
 * Four.
 */
public class ZustandAktiv extends ProzessZustand {

  public ZustandAktiv(Prozess prozess) {
    super("aktiv", prozess);
  }

  public void suspendieren() {
    System.out.println("Der Prozess wird suspendiert.");
    prozess.setzeZustand(new ZustandSuspendiert(prozess));
  }

  public void beenden() {
    System.out.println("Der Prozess wird beendet.");
    prozess.setzeZustand(new ZustandBeendet(prozess));
  }

}
