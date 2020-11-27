package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.hauer.schnitzel;

class Pommes extends Beilage {
  public Pommes(Gericht gericht) {
    super(gericht);
  }

  public void druckeBeschreibung() {
    gericht.druckeBeschreibung();
    System.out.print(", Pommes");
  }

  public double getPreis() {
    return gericht.getPreis() + 2.50;
  }
}
