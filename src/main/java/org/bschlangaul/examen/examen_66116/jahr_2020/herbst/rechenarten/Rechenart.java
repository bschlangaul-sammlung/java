package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

abstract class Rechenart extends Term {
  Term a;
  Term b;
  String operatorZeichen;

  public Rechenart (Term a, Term b, String operatorZeichen) {
    this.a = a;
    this.b = b;
    this.operatorZeichen = operatorZeichen;
  }

  public void ausgeben () {
    a.ausgeben();
    System.out.print(" " + operatorZeichen + " ");
    b.ausgeben();
  }

  abstract public double auswerten();
}
