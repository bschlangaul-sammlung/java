package org.bschlangaul.aufgaben.aud.listen.flughafen;

public class Flug {
  private int flugnummer;
  private String startFlughafen;
  private String zielFlughafen;

  public Flug(int flugnummer, String startFlughafen, String zielFlughafen) {
    this.flugnummer = flugnummer;
    this.startFlughafen = startFlughafen;
    this.zielFlughafen = zielFlughafen;
  }

  public int gibFlugnummer() {
    return flugnummer;
  }

  public String gibStartFlughafen() {
    return startFlughafen;
  }

  public String gibZielFlughafen() {
    return zielFlughafen;
  }
}
