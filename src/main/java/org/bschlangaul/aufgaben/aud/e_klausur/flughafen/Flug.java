package org.bschlangaul.aufgaben.aud.e_klausur.flughafen;

public class Flug {
  private int flugnummer;
  private String startFlughafen;
  private String zielFlughafen;

  public Flug(int flugnummer, String startFlughafen, String zielFlughafen) {
    this.flugnummer = flugnummer;
    this.startFlughafen = startFlughafen;
    this.zielFlughafen = zielFlughafen;
  }

  public int getFlugnummer() {
    return flugnummer;
  }

  public String getStartFlughafen() {
    return startFlughafen;
  }

  public String getZielFlughafen() {
    return zielFlughafen;
  }
}
