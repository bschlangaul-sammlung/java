package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.rechenarten;

import java.util.HashMap;

public class Klient {
  public static HashMap<String, Integer> symbole = new HashMap<String, Integer>();
  public static void main(String[] args) {
    symbole.put("x", 2);
    Term addition = new Addition(new Literal(4), new GeklammerterTerm(new Multiplikation(new Literal(8), new Symbol("x"))));
    addition.ausgeben();

    System.out.println(addition.auswerten());
  }
}
