package org.bschlangaul.aufgaben.aud.baum.kompositum;

class Datenknoten extends Baumelement {
  private Baumelement links, rechts;
  private Datenelement inhalt;

  public Datenknoten(Baumelement links, Baumelement rechts, Datenelement inhalt) {
    this.links = links;
    this.rechts = rechts;
    this.inhalt = inhalt;
  }

  public void setzteLinks(Baumelement links) {
    this.links = links;
  }

  public void setzeRechts(Baumelement rechts) {
    this.rechts = rechts;
  }

  public void inhaltSetzen(Datenelement inhalt) {
    this.inhalt = inhalt;
  }

  public Baumelement gibLinks() {
    return links;
  }

  public Baumelement gibRechts() {
    return rechts;
  }

  public Datenelement gibInhalt() {
    return inhalt;
  }

  public int gibAnzahl() {
    return 1 + links.gibAnzahl() + rechts.gibAnzahl();
  }

  public void gibAus() {
    System.out.print(" [");
    links.gibAus();
    inhalt.gibAus();
    rechts.gibAus();
    System.out.print("] ");
  }
}
