package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.hauer.schnitzel;

class Tofu implements Gericht {
  public void druckeBeschreibung() {
    System.out.print("Tofu");
  }

  public double getPreis() {
    return 8.50;
  }
}
