package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.ticket;

public class TicketHandel {
  private static TicketHandel system;
  private int verkaufteTickets;

  private TicketHandel() {
  }

  public TicketHandel gibInstanz() {
    if (system == null) {
      system = new TicketHandel();
    }
    return system;
  }

  public Ticket ticketKaufen(Kategorie kategorie) {
    if (kategorie == Kategorie.ERWACHSENEN) {
      return new ErwachsenenTicket();
    } else {
      return new KinderTicket();
    }
  }

  public int gibVerkaufteTickets() {
    return verkaufteTickets;
  }

}
