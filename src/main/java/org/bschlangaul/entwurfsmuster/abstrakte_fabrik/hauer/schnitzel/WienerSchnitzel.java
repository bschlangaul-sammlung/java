package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.hauer.schnitzel;

class WienerSchnitzel implements Gericht {
  public void druckeBeschreibung() {
    System.out.print("WienerSchnitzel");
  }

  public double getPreis() {
    return 10.50;
  }
}
