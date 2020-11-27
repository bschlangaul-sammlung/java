package org.bschlangaul.aufgaben.oomup.pu_3.schulklassen;

public class Schulklasse {
  @SuppressWarnings("unused")
  private String bezeichnung;
  private Schueler[] sl;
  private int anzahlS;

  public Schulklasse(String b) {
    sl = new Schueler[32];
    bezeichnung = b;
    anzahlS = 0;
  }

  public void schuelerAufnehmen(Schueler s) {
    if (anzahlS <= 31) {
      sl[anzahlS] = s;
      anzahlS++;
    }
  }

  public void klasseAusgeben() {
    for (int i = 0; i < anzahlS; i++) {
      System.out.println("Vorname: " + sl[i].gibVorname() + " Nachname: " + sl[i].gibNachname());
    }

  }
}
