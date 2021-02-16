package org.bschlangaul.aufgaben.theo_inf.regulaere_ausdruecke;

public class KrankenversicherungsNummer {

  public static void matches(String nummer) {
    System.out.print("Die Versicherungsnummer " + nummer + " ");
    if (nummer.matches("[A-Z]{2}-[1-9]\\d*(P|(G(0[1-9]|1[0-2])))-[1-9][0-9]*")) {
      System.out.print("ist eine");
    } else {
      System.out.print("ist keine");
    }
    System.out.println(" valide Nummer.");
  }

  public static void main(String[] args) {
    matches("MI-32P-9");
    matches("MM-1234G07-12");
    matches("MM-1234G17-12");
  }
}
