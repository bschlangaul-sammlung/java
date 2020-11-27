package org.bschlangaul.baum.kompositum;

class Person extends Datenelement {
  private String geburtsdatum;
  private String sterbedatum;
  private String vorname;
  private String name;
  private String beruf;

  public Person(String nn) {
    name = nn;
  }

  public Person(String nn, String vn, String gebdat, String stedat, String ber) {
    name = nn;
    vorname = vn;
    geburtsdatum = gebdat;
    sterbedatum = stedat;
    beruf = ber;
  }

  public void datenAusgeben() {
    System.out.print(name + " " + vorname + " (" + geburtsdatum + "-" + sterbedatum + ") " + beruf);
  }
}
