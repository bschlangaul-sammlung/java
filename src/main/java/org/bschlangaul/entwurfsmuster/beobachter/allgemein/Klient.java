package org.bschlangaul.entwurfsmuster.beobachter.allgemein;

public class Klient {
  public static void main(String[] args) {
    KonkreterGegenstand konkreterGegenstand = new KonkreterGegenstand();
    konkreterGegenstand.registriere(new KonkreterBeobachterA());
    konkreterGegenstand.registriere(new KonkreterBeobachterB());
    konkreterGegenstand.setzteZustand(77);
    // Konkreter Beobachter A wurde aktualisiert mit 77
    // Konkreter Beobachter B wurde aktualisiert mit 77
  }
}
