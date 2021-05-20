package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.ticket;

public class TicketDruckerei {
  public Ticket erstelleTicket(Kategorie kategorie) {
    if (kategorie == Kategorie.ERWACHSENEN) {
      return new ErwachsenenTicket();
    } else {
      return new KinderTicket();
    }
  }
}
