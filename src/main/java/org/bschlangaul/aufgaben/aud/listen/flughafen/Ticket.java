package org.bschlangaul.aufgaben.aud.listen.flughafen;

public class Ticket {
  public Flug flug;
  public Ticket nächstes;
  public boolean startetInNürnberg = false;
  public boolean landetInNürnberg = false;

  public Ticket(Flug flug) {
    this.flug = flug;
    if (flug.gibStartFlughafen().equals("NUE")) {
      startetInNürnberg = true;
    } else {
      landetInNürnberg = true;
    }
  }
}
