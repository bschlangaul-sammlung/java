package org.bschlangaul.aufgaben.aud.e_klausur.flughafen;

public class Ticket {
  public Flug flug;
  public Ticket next;
  public boolean startetInNBG = false;
  public boolean landetInNBG = false;

  public Ticket(Flug flug) {
    this.flug = flug;
    if (flug.getStartFlughafen().equals("NUE")) {
      startetInNBG = true;
    } else {
      landetInNBG = true;
    }
  }
}
