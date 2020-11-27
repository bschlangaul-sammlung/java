package org.bschlangaul.aufgaben.oomup.ab_4.kino;

public class Reservierung {

  public Sitzplatz sitzplatz;
  public Kinosaal kinosaal;
  public int nummer;

  public Reservierung(Kinosaal kinosaal, Sitzplatz sitzplatz, int nummer) {
    this.kinosaal = kinosaal;
    this.sitzplatz = sitzplatz;
    this.nummer = nummer;
  }

}
