package org.bschlangaul.aufgaben.oomup.klassendiagramm.hunde;

public abstract class Hund {
  protected String name;
  protected int alter;
  protected double gewicht;

  public Hund(String n, int a, double g) {
    name = n;
    alter = a;
    gewicht = g;
  }

  public abstract void bellen();

  public void altern() {
    alter = alter + 1;
  }

  public void fressen(double futter) {
    gewicht = gewicht + futter;
  }

  public abstract void fressen();

  public abstract void gassiGehen();

  public String getName() {
    return name;
  }

  public int getAlter() {
    return alter;
  }

  public double getGewicht() {
    return gewicht;
  }

  public void setName(String n) {
    name = n;
  }

  public void setAlter(int a) {
    if (a >= 0) {
      alter = a;
    }
  }

  public void setGewicht(double g) {
    if (g > 0) {
      gewicht = g;
    }
  }
}
