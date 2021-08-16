package org.bschlangaul.aufgaben.aud.baum.kompositum;

class Binaerbaum {
  private Baumelement wurzel;

  public Binaerbaum() {
    wurzel = new Abschluss();
  }

  public void setzeWurzel(Baumelement wurzel) {
    this.wurzel = wurzel;
  }

  public Baumelement gibWurzel() {
    return wurzel;
  }

  public int gibAnzahl() {
    return wurzel.gibAnzahl();
  }

  public void gibAus() {
    wurzel.gibAus();
  }
}
