package org.bschlangaul.aufgaben.aud.baum.kompositum;

class Abschluss extends Baumelement {

  public void setzteLinks(Baumelement links) {
    System.out.println("Ein Abschluss hat kein linkes Element!");
  }

  public void setzeRechts(Baumelement rechts) {
    System.out.println("Ein Abschluss hat kein rechts Element!");
  }

  public Baumelement gibLinks() {
    System.out.println("Linkes Element nicht bekannt!");
    return this;
  }

  public Baumelement gibRechts() {
    System.out.println("Linkes Element nicht bekannt!");
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
