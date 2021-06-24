package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

public class Symbol extends Term {
  String name;
  public Symbol(String name) {
    this.name = name;
  }

  public double auswerten() {
    return Klient.symbole.get(name);
  }

  public void ausgeben() {
    System.out.print(name);
  }
}
