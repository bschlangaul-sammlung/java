package org.bschlangaul.examen.examen_46116.jahr_2011.fruehjahr;

import java.util.ArrayList;
import java.util.List;

public class Abteilung {
  public String name;

  public List<Angestellter> angestellte;

  public Abteilung(String name) {
    this.name = name;
    angestellte = new ArrayList<Angestellter>();
  }
}
