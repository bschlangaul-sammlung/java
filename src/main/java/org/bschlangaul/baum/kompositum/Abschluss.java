package org.bschlangaul.baum.kompositum;

class Abschluss extends Baumelement {

  public void mutterSetzen(Baumelement nl) {
    System.out.println("Ein Abschluss hat keine Mutter!");
  }

  public void vaterSetzen(Baumelement nr) {
    System.out.println("Ein Abschluss hat keinen Vater!");
  }

  public Baumelement mutterGeben() {
    System.out.println("Mutter nicht bekannt!");
    return this;
  }

  public Baumelement vaterGeben() {
    System.out.println("Vater nicht bekannt!");
    return this;
  }

  public Datenelement inhaltGeben() {
    return null;
  }

  public int anzahlDatenknotenGeben() {
    return 0;
  }

  public void baumdatenAusgeben() {
  }
}
