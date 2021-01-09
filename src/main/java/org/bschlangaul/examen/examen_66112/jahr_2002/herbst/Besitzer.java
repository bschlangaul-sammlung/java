package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

public class Besitzer {
  @SuppressWarnings("unused")
  private String name;
  private int kundennr;
  @SuppressWarnings("unused")
  private Konto hatKonto;

  public Besitzer(String name, int kundennr) {
    this.name = name;
    this.kundennr = kundennr;
  }

  public int getKundennr() {
    return kundennr;
  }
}
