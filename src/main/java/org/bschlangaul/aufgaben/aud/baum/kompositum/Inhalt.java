package org.bschlangaul.aufgaben.aud.baum.kompositum;

class Inhalt extends Datenelement {
  private String inhalt;

  public Inhalt(String inhalt) {
    this.inhalt = inhalt;
  }

  public String gibInhalt() {
    return inhalt;
  }

  public void gibAus() {
    System.out.print(inhalt);
  }
}
