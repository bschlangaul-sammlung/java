package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

@SuppressWarnings("unused")
public class Besitzer {
  private String name;
  private int kundenNummer;
  private Konto hatKonto;

  public Besitzer(String name, int kundenNummer) {
    this.name = name;
    this.kundenNummer = kundenNummer;
  }

  public int gibKundenNummer() {
    return kundenNummer;
  }
}
