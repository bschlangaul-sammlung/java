package org.bschlangaul.aufgaben.oomup.klassendiagramm.kleintierpraxis;

public class Heimtier extends Kleintier {
  @SuppressWarnings("unused")
  private boolean wirdGeschlachtet;

  public Heimtier(String name, int alter, float gewicht, boolean wirdGeschlachtet) {
    super(name, alter, gewicht);
    this.wirdGeschlachtet = wirdGeschlachtet;
    narkoseGrundGebuehr = 2f;
  }
}
