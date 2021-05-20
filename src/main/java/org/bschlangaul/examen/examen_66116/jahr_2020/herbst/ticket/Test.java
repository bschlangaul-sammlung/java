package org.bschlangaul.examen.examen_66116.jahr_2020.herbst.ticket;

public class Test {
  public static void main(String[] args) {
    TicketHandel.gibInstanz().ticketKaufen(Kategorie.ERWACHSEN);
    TicketHandel.gibInstanz().ticketKaufen(Kategorie.KIND);
    System.out.println("Anzahl verkaufter Tickets: " + TicketHandel.gibInstanz().gibVerkaufteTickets());
  }
}
