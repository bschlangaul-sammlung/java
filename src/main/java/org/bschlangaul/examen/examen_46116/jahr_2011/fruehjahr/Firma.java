package org.bschlangaul.examen.examen_46116.jahr_2011.fruehjahr;

import java.util.ArrayList;
import java.util.List;

public class Firma {

  List<Abteilung> abteilungen;

  public Firma() {
    abteilungen = new ArrayList<Abteilung>();
  }

  public void addAbteilung(String name) {
    for (Abteilung abteilung : abteilungen) {
      if (abteilung.name.equals(name)) {
        System.out.println("Eine Abteilung mit diesem Namen gibt es bereits schon.");
        return;
      }
      abteilungen.add(new Abteilung(name));
    }
  }

  public void erzeugeIDs() {
    int idZähler = 1;
    for (Abteilung abteilung : abteilungen) {
      for (Angestellter angestellter : abteilung.angestellte) {
        angestellter.id = idZähler;
        idZähler++;
      }
    }
  }
}
