package org.bschlangaul.aufgaben.oomup.ab_5;

public class Hund extends Kleintier {
  private boolean reinrassig;

  public Hund(String name, int alter, float gewicht, boolean reinrassig) {
    super(name, alter, gewicht);
    this.reinrassig = reinrassig;
    narkoseGrundGebuehr = 1.50f;
  }

  public void datenAusgeben() {
    System.out.println("Name: " + name + ", Alter: " + alter + " Jahre");

    if (reinrassig) {
      System.out.println("Der Hund ist reinrassig.");
    } else {
      System.out.println("Der Hund ist nicht reinrassig.");
    }
  }
}
