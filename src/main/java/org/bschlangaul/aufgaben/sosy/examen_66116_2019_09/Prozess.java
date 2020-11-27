package org.bschlangaul.aufgaben.sosy.examen_66116_2019_09;

/**
 * Entspricht der „State“-Klasse in der Terminologie der Gang of
 * Four.
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
  }

}
