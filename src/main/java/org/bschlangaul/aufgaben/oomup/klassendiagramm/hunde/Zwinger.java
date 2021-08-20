package org.bschlangaul.aufgaben.oomup.klassendiagramm.hunde;

public class Zwinger {
  // Attribute
  private Hund[] zwinger;

  // Konstruktor
  public Zwinger() {
    zwinger = new Hund[10];
  }

  // Methoden
  public void belegen() {
    zwinger[0] = new Chihuahua("Tim", 2, 1.8);
    zwinger[1] = new Schaeferhund("Alex", 4, 40.0, true);
    zwinger[2] = new Bernhardiner("Eva", 5, 82.0, false);
    zwinger[3] = new Schaeferhund("Lilli", 3, 34.0, false);
  }

  public void fuettern() {
    for (int i = 0; i < zwinger.length; i++) {
      if (zwinger[i] != null) {
        zwinger[i].fressen();
      }
    }
  }

  public void fuetterzeit() {
    for (int i = 0; i < zwinger.length; i++) {
      if (zwinger[i] != null) {
        zwinger[i].bellen();
      }
    }
  }

  public void gassiGehen() {
    for (int i = 0; i < zwinger.length; i++) {
      if (zwinger[i] != null) {
        zwinger[i].gassiGehen();
      }
    }
  }
}
