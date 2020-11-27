package org.bschlangaul.entwurfsmuster.beobachter.hauer;

import java.util.ArrayList;
import java.util.List;

public abstract class Verlag {

  private List<Abonnent> abonnentenList = new ArrayList<Abonnent>();

  public void aboHinzufuegen(Abonnent abonnent) {
    abonnentenList.add(abonnent);
  }

  public void aboEntfernen(Abonnent abonnent) {
    abonnentenList.remove(abonnent);
  }

  protected void verteileZeitung(Zeitung zeitung) {
    for (Abonnent abonnent : abonnentenList) {
      abonnent.erhalteZeitung(zeitung);
    }
  }
}
