package org.bschlangaul.aufgaben.aud.pu_5.aufgabe_1;

public class Knoten {

  private Knoten links;
  private Knoten rechts;
  private int wert;

  public Knoten(int wert) {
    this.wert = wert;
    // links und rechts werden dann mit setter-Methoden gefüllt.
  }

  public void setWert(int w) {
    wert = w;
  }

  public int getWert() {
    return wert;
  }

  public void setLinks(Knoten l) {
    links = l;
  }

  public void setRechts(Knoten r) {
    rechts = r;
  }

  public Knoten getLinks() {
    return links;
  }

  public Knoten getRechts() {
    return rechts;
  }

  public void elementEinfuegen(int wert) {
    if (wert <= this.getWert()) {
      if (this.getLinks() != null) {
        this.getLinks().elementEinfuegen(wert);
      } else {
        this.setLinks(new Knoten(wert));
      }
    } else {
      if (this.getRechts() != null) {
        this.getRechts().elementEinfuegen(wert);
      } else {
        this.setRechts(new Knoten(wert));
      }
    }
  }

}
