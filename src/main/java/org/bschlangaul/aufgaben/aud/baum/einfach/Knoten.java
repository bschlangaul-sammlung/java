package org.bschlangaul.aufgaben.aud.baum.einfach;

public class Knoten {

  private Knoten links;
  private Knoten rechts;
  private int wert;

  public Knoten(int wert) {
    this.wert = wert;
  }

  public void setzeWert(int w) {
    wert = w;
  }

  public int gibWert() {
    return wert;
  }

  public void setzeLinks(Knoten l) {
    links = l;
  }

  public void setzeRechts(Knoten r) {
    rechts = r;
  }

  public Knoten gibLinks() {
    return links;
  }

  public Knoten gibRechts() {
    return rechts;
  }

  public void fügeEin(int wert) {
    if (wert <= this.gibWert()) {
      if (this.gibLinks() != null) {
        this.gibLinks().fügeEin(wert);
      } else {
        this.setzeLinks(new Knoten(wert));
      }
    } else {
      if (this.gibRechts() != null) {
        this.gibRechts().fügeEin(wert);
      } else {
        this.setzeRechts(new Knoten(wert));
      }
    }
  }

}
