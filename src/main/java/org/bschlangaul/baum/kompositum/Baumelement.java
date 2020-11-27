package org.bschlangaul.baum.kompositum;

abstract class Baumelement {
  public abstract void mutterSetzen(Baumelement nl);

  public abstract void vaterSetzen(Baumelement nr);

  public abstract Baumelement mutterGeben();

  public abstract Baumelement vaterGeben();

  public abstract Datenelement inhaltGeben();

  public abstract int anzahlDatenknotenGeben();

  public abstract void baumdatenAusgeben();
}
