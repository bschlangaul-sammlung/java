package org.bschlangaul.aufgaben.aud.pu_5.aufgabe_2;

public class Knoten {
  private int zahl;
  private Knoten linkerTB = null;
  private Knoten rechterTB = null;

  // Konstruktoren
  public Knoten() {
  }

  public Knoten(int zahl) {
    this.zahl = zahl;
  }

  // setter und getter
  public void setZahl(int zahl) {
    this.zahl = zahl;
  }

  public int getZahl() {
    return zahl;
  }

  public void setLinkerTB(Knoten k) {
    linkerTB = k;
  }

  public Knoten getLinkerTB() {
    return linkerTB;
  }

  public void setRechterTB(Knoten k) {
    rechterTB = k;
  }

  public Knoten getRechterTB() {
    return rechterTB;
  }
  // ...
}
