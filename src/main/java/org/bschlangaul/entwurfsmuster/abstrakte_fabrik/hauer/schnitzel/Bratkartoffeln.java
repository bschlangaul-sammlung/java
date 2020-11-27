package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.hauer.schnitzel;

class Bratkartoffeln extends Beilage {
  public Bratkartoffeln(Gericht gericht) {
    super(gericht);
  }

  public void druckeBeschreibung() {
    gericht.druckeBeschreibung();
    System.out.print(", Bratkartoffeln");
  }

  public double getPreis() {
    return gericht.getPreis() + 1.50;
  }
}
