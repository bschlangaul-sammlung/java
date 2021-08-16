package org.bschlangaul.aufgaben.aud.baum.kompositum;

class Abschluss extends Baumelement {

  public void setzteLinks(Baumelement nl) {
    System.out.println("Ein Abschluss hat keine Mutter!");
  }

  public void setzeRechts(Baumelement nr) {
    System.out.println("Ein Abschluss hat keinen Vater!");
  }

  public Baumelement gibLinks() {
    System.out.println("Mutter nicht bekannt!");
    return this;
  }

  public Baumelement gibRechts() {
    System.out.println("Vater nicht bekannt!");
    return this;
  }

  public Datenelement gibInhalt() {
    return null;
  }

  public int gibAnzahl() {
    return 0;
  }

  public void gibAus() {
  }
}
