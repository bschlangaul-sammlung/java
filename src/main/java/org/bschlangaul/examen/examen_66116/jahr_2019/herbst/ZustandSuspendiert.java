package org.bschlangaul.examen.examen_66116.jahr_2019.herbst;

/**
 * Entspricht der „ConcreteState“-Unterklasse in der Terminologie der Gang of
 * Four.
 */
public class ZustandSuspendiert extends ProzessZustand {

  public ZustandSuspendiert(Prozess prozess) {
    super("suspendiert", prozess);
  }

  public void fortsetzen() {
    System.out.println("Der Prozess wird fortgesetzt.");
    prozess.setzeZustand(new ZustandAktiv(prozess));
  }

  public void abbrechen() {
    System.out.println("Der Prozess wird abgebrochen.");
    prozess.setzeZustand(new ZustandAbgebrochen(prozess));
  }
}
