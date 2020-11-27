package org.bschlangaul.aufgaben.oomup.pu_3.schulklassen;

public class Schueler {
  private String vorname;
  private String nachname;
  @SuppressWarnings("unused")
  private String strasse;
  @SuppressWarnings("unused")
  private String hausnummer;
  @SuppressWarnings("unused")
  private int plz;
  @SuppressWarnings("unused")
  private String wohnort;

  public Schueler(String vorname, String nachname, String strasse, String hausnummer, int plz, String wohnort) {
    this.vorname = vorname;
    this.nachname = nachname;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.plz = plz;
    this.wohnort = wohnort;
  }

  public String gibVorname() {
    return vorname;
  }

  public String gibNachname() {
    return nachname;
  }

}
