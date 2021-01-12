package org.bschlangaul.aufgaben.tech_info.erzeuger_verbraucher;

public class Kiste extends Bild {

  private static final long serialVersionUID = 918522056303342529L;

  public Kiste(int x, int y) {
    super(x, y, Bild.gibPfad("paket.jpg"));
  }
}
