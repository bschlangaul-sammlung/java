package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.hauer.schnitzel;

class Garnelen implements Gericht {
  public void druckeBeschreibung() {
    System.out.print("Garnelen");
  }

  public double getPreis() {
    return 13.50;
  }
}
