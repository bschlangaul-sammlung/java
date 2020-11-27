package org.bschlangaul.entwurfsmuster.beobachter.allgemein;

public class KonkreterBeobachterB implements Beobachter {
  public void aktualisiere(int zustand) {
    System.out.println("Konkreter Beobachter B wurde aktualisiert mit " + zustand);
    // ggf. Modifikationen mit setState().
  }
}
