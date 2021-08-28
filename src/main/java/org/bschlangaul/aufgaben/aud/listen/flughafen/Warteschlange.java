package org.bschlangaul.aufgaben.aud.listen.flughafen;

public class Warteschlange {
  Ticket erstes = null;

  public void fügeNeuenFlugHinzu(Flug flug) {
    Ticket zähler = erstes;
    if (erstes == null) {
      erstes = new Ticket(flug);
    } else {
      while (zähler.nächstes != null) {
        zähler = zähler.nächstes;
      }
      zähler.nächstes = new Ticket(flug);
    }
  }

  public void fügeNotfallHinzu(Flug flug) {
    if (erstes.flug.gibFlugnummer() == flug.gibFlugnummer()) {
      return;
    }
    Ticket zähler = erstes;
    Ticket zähler2 = erstes.nächstes;
    Ticket notfall = null;
    while (zähler2 != null) {
      if (zähler2.flug.gibFlugnummer() == flug.gibFlugnummer()) {
        notfall = zähler2;
        zähler.nächstes = zähler2.nächstes;
        break;
      } else {
        zähler = zähler2;
        zähler2 = zähler2.nächstes;
      }
    }
    if (notfall == null) {
      notfall = new Ticket(flug);
    }
    notfall.nächstes = erstes;
    erstes = notfall;
  }
}
