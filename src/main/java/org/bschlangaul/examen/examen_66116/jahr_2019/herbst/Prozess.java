package org.bschlangaul.examen.examen_66116.jahr_2019.herbst;

/**
 * Entspricht der „Kontext“-Klasse in der Terminologie der „Gang of
 * Four“.
 */
public class Prozess {

  private ProzessZustand aktuellerZustand;

  public Prozess() {
    aktuellerZustand = new ZustandBereit(this);
  }

  public void setzeZustand(ProzessZustand zustand) {
    aktuellerZustand = zustand;
  }

  public void starten() {
    aktuellerZustand.starten();
  }

  public void suspendieren() {
    aktuellerZustand.suspendieren();
  }

  public void fortsetzen() {
    aktuellerZustand.fortsetzen();
  }

  public void beenden() {
    aktuellerZustand.beenden();
  }

  public void abbrechen() {
    aktuellerZustand.abbrechen();
  }

  public static void main(String[] args) {
    Prozess prozess = new Prozess();
    prozess.starten();
    prozess.suspendieren();
    prozess.fortsetzen();
    prozess.beenden();
    prozess.starten();

    // Ausgabe:
    // Der Prozess ist im Zustand „bereit“
    // Der Prozess wird gestartet.
    // Der Prozess ist im Zustand „aktiv“
    // Der Prozess wird suspendiert.
    // Der Prozess ist im Zustand „suspendiert“
    // Der Prozess wird fortgesetzt.
    // Der Prozess ist im Zustand „aktiv“
    // Der Prozess wird beendet.
    // Der Prozess ist im Zustand „beendet“
    // Im Zustand „beendet“ kann die Transition „starten“ nicht ausführt werden!
  }
}
