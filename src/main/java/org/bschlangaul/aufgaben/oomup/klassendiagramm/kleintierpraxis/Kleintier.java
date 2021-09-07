package org.bschlangaul.aufgaben.oomup.klassendiagramm.kleintierpraxis;

public abstract class Kleintier {
  protected String name;
  protected int alter;
  protected float gewicht;
  protected float narkoseGrundGebuehr;
  private float narkotikumPreisProKg;

  public Kleintier(String name, int alter, float gewicht) {
    this.name = name;
    this.alter = alter;
    this.gewicht = gewicht;
    narkotikumPreisProKg = 2.5f;
  }

  public void datenAusgeben() {
    System.out.println("Name: " + name + ", Alter: " + alter);
  }

  public float narkoseKostenBerechnen() {
    float preis = narkoseGrundGebuehr + narkotikumPreisProKg * gewicht;
    System.out.println("Die Narkose kostet " + preis + " €.");
    return preis;
  }
}
