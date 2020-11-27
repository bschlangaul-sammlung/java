package org.bschlangaul.entwurfsmuster.dekorierer.hauer;

class Nudeln extends Beilage {
  public Nudeln(Gericht gericht) {
    super(gericht);
  }

  public void druckeBeschreibung() {
    gericht.druckeBeschreibung();
    System.out.print(", Nudeln");
  }

  public double getPreis() {
    return gericht.getPreis() + 4.50;
  }
}
