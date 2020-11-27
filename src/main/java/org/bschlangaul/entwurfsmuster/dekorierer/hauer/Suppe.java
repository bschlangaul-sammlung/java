package org.bschlangaul.entwurfsmuster.dekorierer.hauer;

class Suppe extends Beilage {
  public Suppe(Gericht gericht) {
    super(gericht);
  }

  public void druckeBeschreibung() {
    gericht.druckeBeschreibung();
    System.out.print(", Suppe");
  }

  public double getPreis() {
    return gericht.getPreis() + 1.50;
  }
}
