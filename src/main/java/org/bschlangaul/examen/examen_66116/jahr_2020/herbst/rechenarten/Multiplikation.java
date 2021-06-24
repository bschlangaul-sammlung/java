package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

public class Multiplikation extends Rechenart {
  public Multiplikation(Term a, Term b ) {
    super(a, b, "*");
  }

  public double auswerten() {
    return a.auswerten() * b.auswerten();
  }
}
