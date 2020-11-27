package org.bschlangaul.entwurfsmuster.beobachter.allgemein;

public class KonkreterGegenstand extends Gegenstand {
  private int zustand;

  public void setzteZustand(int zustand) {
    this.zustand = zustand;
    // Wenn das Gegenstand die Aktualisierung selbst durchführen soll,
    // alternativ kann die Methode auch vom Client aufgerufen werden.
    benachrichtige(zustand);
  }

  public int gibZustand() {
    return zustand;
  }
}
