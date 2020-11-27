package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.hauer.schnitzel;

class Salat extends Beilage {
  public Salat(Gericht gericht) {
    super(gericht);
  }

  public void druckeBeschreibung() {
    gericht.druckeBeschreibung();
    System.out.print(", Salat");
  }

  public double getPreis() {
    return gericht.getPreis() + 2.25;
  }
}
