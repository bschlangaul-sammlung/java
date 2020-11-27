package org.bschlangaul.entwurfsmuster.beobachter.allgemein;

public class KonkreterBeobachterA implements Beobachter {
  public void aktualisiere(int zustand) {
    System.out.println("Konkreter Beobachter A wurde aktualisiert mit " + zustand);
    // ggf. Modifikationen mit setState().
  }
}
