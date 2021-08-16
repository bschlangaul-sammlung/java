package org.bschlangaul.aufgaben.aud.baum.kompositum;

abstract class Baumelement {
  public abstract void setzteLinks(Baumelement nl);

  public abstract void setzeRechts(Baumelement nr);

  public abstract Baumelement gibLinks();

  public abstract Baumelement gibRechts();

  public abstract Datenelement gibInhalt();

  public abstract int gibAnzahl();

  public abstract void gibAus();
}
