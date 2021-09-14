package org.bschlangaul.examen.examen_46116.jahr_2018.fruehjahr.verein;

public class Spieler {
  private String name;
  private int tore;
  @SuppressWarnings("unused")
  private Verein verein;

  public Spieler(String name, Verein verein) {
    this.name = name;
    this.verein = verein;
    tore = 0;
  }

  public String getName() {
    return name;
  }

  public int getTore() {
    return tore;
  }

  public void schiesseTor() {
    tore++;
  }

  public void wechsleVerein(Verein neuerVerein) {
    verein = neuerVerein;
  }
}
