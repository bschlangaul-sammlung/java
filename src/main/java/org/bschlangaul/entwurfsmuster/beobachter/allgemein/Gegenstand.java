package org.bschlangaul.entwurfsmuster.beobachter.allgemein;

import java.util.ArrayList;
import java.util.List;

public abstract class Gegenstand {
  private final List<Beobachter> beobachterListe = new ArrayList<Beobachter>();

  public void registriere(Beobachter beobachter) {
    beobachterListe.add(beobachter);
  }

  public void meldeAb(Beobachter beobachter) {
    beobachterListe.remove(beobachter);
  }

  protected void benachrichtige(int zustand) {
    for (Beobachter beobachter : beobachterListe) {
      beobachter.aktualisiere(zustand);
    }
  }
}
