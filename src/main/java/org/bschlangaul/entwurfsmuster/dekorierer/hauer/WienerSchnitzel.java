package org.bschlangaul.entwurfsmuster.dekorierer.hauer;

class WienerSchnitzel implements Gericht {
  public void druckeBeschreibung() {
    System.out.print("WienerSchnitzel");
  }

  public double getPreis() {
    return 10.50;
  }
}
