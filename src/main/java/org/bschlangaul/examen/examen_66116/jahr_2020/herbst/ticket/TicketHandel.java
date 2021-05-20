package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.ticket;

public class TicketHandel {
  private static TicketHandel system;
  private int verkaufteTickets = 0;
  private TicketDruckerei druckerei;

  private TicketHandel() {
    druckerei = new TicketDruckerei();
  }

  public static TicketHandel gibInstanz() {
    if (system == null) {
      system = new TicketHandel();
    }
    return system;
  }

  public Ticket ticketKaufen(Kategorie kategorie) {
    verkaufteTickets++;
    return druckerei.erstelleTicket(kategorie);
  }

  public int gibVerkaufteTickets() {
    return verkaufteTickets;
  }

}
