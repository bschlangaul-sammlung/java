package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

public class Subtraktion extends Rechenart {
  public Subtraktion(Term a, Term b) {
    super(a, b, "-");
  }

  public double auswerten() {
    return a.auswerten() - b.auswerten();
  }
}
