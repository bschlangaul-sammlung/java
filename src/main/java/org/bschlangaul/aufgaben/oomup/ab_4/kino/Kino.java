package org.bschlangaul.aufgaben.oomup.ab_4.kino;

public class Kino {
  private int buchungsNrZaehler;
  private Kinosaal[] kinosaele;
  private Reservierung[] reservierungen;

  public Kino() {
    kinosaele = new Kinosaal[5];
    reservierungen = new Reservierung[5 * 180];
    for (int i = 0; i < 5; i++) {
      kinosaele[i] = new Kinosaal(i + 1);
    }
    buchungsNrZaehler = 0;
  }

  public void sitzplatzReservieren(int saalNr, int sitzplatzNr) {
    Kinosaal kinosaal = kinosaele[saalNr - 1];
    boolean ergebnis = kinosaal.sitzplatzReservieren(sitzplatzNr, buchungsNrZaehler + 1);
    if (ergebnis) {
      buchungsNrZaehler++;
      System.out.println("Ihre Reservierungsnummer lautet: " + buchungsNrZaehler);
      reservierungen[buchungsNrZaehler - 1] = new Reservierung(kinosaal, kinosaal.gibSitzplatzBeiNummer(sitzplatzNr),
          buchungsNrZaehler);
    } else {
      System.out.println(
          "Der gewünschte Sitzplatz mit der Nummer " + sitzplatzNr + " im Kino " + saalNr + " ist bereits vergeben.");
    }
  }

  public void ticketAbholen(int buchungsNr) {
    if (reservierungen[buchungsNr - 1] instanceof Reservierung) {
      Reservierung reservierung = reservierungen[buchungsNr - 1];
      reservierung.sitzplatz.besetzen();
      System.out.println("Genießen Sie Ihren Film im Kino " + reservierung.kinosaal.nummer + " auf dem Sitzplatz "
          + reservierung.sitzplatz.nummer + ".");
      reservierungen[buchungsNr - 1] = null;
    } else {
      System.out.println("Uns liegt keine Reservierung mit der Nummer " + buchungsNr + " vor.");
    }
  }
}
