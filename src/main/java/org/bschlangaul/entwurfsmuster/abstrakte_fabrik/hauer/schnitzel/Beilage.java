package org.bschlangaul.entwurfsmuster.abstrakte_fabrik.hauer.schnitzel;

public abstract class Beilage implements Gericht {
  protected Gericht gericht;

  public Beilage(Gericht gericht) {
    this.gericht = gericht;
  }
}
