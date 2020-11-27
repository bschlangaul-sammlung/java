package org.bschlangaul.aufgaben.oomup.examen_66112_2002_09;

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
