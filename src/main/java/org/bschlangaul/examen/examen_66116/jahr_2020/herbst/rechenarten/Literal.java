package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

public class Literal extends Term {
  int wert;

  public Literal(int wert) {
    this.wert = wert;
  }

  public double auswerten() {
    return wert;
  }

  public void ausgeben() {
    System.out.print(wert);
  }
}
