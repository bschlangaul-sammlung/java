package org.bschlangaul.baum.kompositum;

class Datenknoten extends Baumelement {
  private Baumelement mutter, vater;
  private Datenelement inhalt;

  public Datenknoten(Baumelement nl, Baumelement nr, Datenelement i) {
    mutter = nl;
    vater = nr;
    inhalt = i;
  }

  public void mutterSetzen(Baumelement nl) {
    mutter = nl;
  }

  public void vaterSetzen(Baumelement nr) {
    vater = nr;
  }

  public void inhaltSetzen(Datenelement i) {
    inhalt = i;
  }

  public Baumelement mutterGeben() {
    return mutter;
  }

  public Baumelement vaterGeben() {
    return vater;
  }

  public Datenelement inhaltGeben() {
    return inhalt;
  }

  /////////// rekursive Baummethoden/////////////////
  public int anzahlDatenknotenGeben() {
    return 1 + mutter.anzahlDatenknotenGeben() + vater.anzahlDatenknotenGeben();
  }

  // InOrder
  public void baumdatenAusgeben() {
    System.out.print(" [");
    mutter.baumdatenAusgeben();
    inhalt.datenAusgeben();
    vater.baumdatenAusgeben();
    System.out.print("] ");
  }
}
