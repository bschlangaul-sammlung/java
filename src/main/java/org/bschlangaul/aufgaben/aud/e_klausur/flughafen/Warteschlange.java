package org.bschlangaul.aufgaben.aud.e_klausur.flughafen;

public class Warteschlange {
  Ticket first = null;

  public void addNewFlight(Flug flug) {
    Ticket counter = first;
    if (first == null) {
      first = new Ticket(flug);
    } else {
      while (counter.next != null) {
        counter = counter.next;
      }
      counter.next = new Ticket(flug);
    }
  }

  public void addEmergency(Flug flug) {
    if (first.flug.getFlugnummer() == flug.getFlugnummer()) {
      return;
    }
    Ticket counter = first;
    Ticket counter2 = first.next;
    Ticket emergency = null;
    while (counter2 != null) {
      if (counter2.flug.getFlugnummer() == flug.getFlugnummer()) {
        emergency = counter2;
        counter.next = counter2.next;
        break;
      } else {
        counter = counter2;
        counter2 = counter2.next;
      }
    }
    if (emergency == null) {
      emergency = new Ticket(flug);
    }
    emergency.next = first;
    first = emergency;
  }
}
