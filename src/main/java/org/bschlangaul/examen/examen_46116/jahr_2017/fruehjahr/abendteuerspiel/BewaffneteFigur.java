package org.bschlangaul.examen.examen_46116.jahr_2017.fruehjahr.abendteuerspiel;

abstract class BewaffneteFigur implements Spielfigur {
  protected Spielfigur figur;

  public BewaffneteFigur(Spielfigur figur) {
    this.figur = figur;
  }

  public void attackieren() {
    figur.attackieren();
  }
}
