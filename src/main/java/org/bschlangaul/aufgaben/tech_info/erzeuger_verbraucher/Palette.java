package org.bschlangaul.aufgaben.tech_info.erzeuger_verbraucher;

public class Palette extends Bild {

  private static final long serialVersionUID = 535897982045911214L;
  public Kiste kiste;

  public Palette(int x, int y) {
    super(x, y, Bild.gibPfad("palette.png"));
    this.kiste = null;
  }

  public void nimmKiste(Kiste k) {
    this.kiste = k;
  }

  public void gibKisteAn(Stapler s) {
    s.nimmKiste(this.kiste);
    this.kiste = null;
  }
}
