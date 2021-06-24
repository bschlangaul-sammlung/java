package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

public class Addition extends Rechenart {
  public Addition(Term a, Term b ) {
    super(a, b, "+");
  }

  public double auswerten() {
    return a.auswerten() * b.auswerten();
  }
}
