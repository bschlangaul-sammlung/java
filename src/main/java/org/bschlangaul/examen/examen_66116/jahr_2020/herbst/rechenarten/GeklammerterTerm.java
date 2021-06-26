package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

public class GeklammerterTerm extends Term {
  Term term;

  public GeklammerterTerm(Term term) {
    this.term = term;
  }

  public double auswerten() {
    return term.auswerten();
  }

  public void ausgeben() {
    System.out.print("(");
    term.ausgeben();
    System.out.print(")");
  }
}
