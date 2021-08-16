package org.bschlangaul.examen.examen_66112.jahr_2003.herbst;

public class Knoten {
  private int zahl;
  private Knoten links = null;
  private Knoten rechts = null;

  public Knoten() {
  }

  public Knoten(int zahl) {
    this.zahl = zahl;
  }

  public void setzeZahl(int zahl) {
    this.zahl = zahl;
  }

  public int gibZahl() {
    return zahl;
  }

  public void setzeLinks(Knoten k) {
    links = k;
  }

  public Knoten gibLinks() {
    return links;
  }

  public void setzeRechts(Knoten k) {
    rechts = k;
  }

  public Knoten gibRechts() {
    return rechts;
  }
}
