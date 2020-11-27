package org.bschlangaul.entwurfsmuster.stellvertreter.allgemein;

public class Klient {
  public static void main(String[] args) {
    Subjekt stellvertreter = new Stellvertreter();
    // Keine Ausgabe

    stellvertreter.agiere();
    // Lade aufwändige Konfiguration ...
    // agiere ...

    stellvertreter.agiere();
    // agiere ...
  }
}
