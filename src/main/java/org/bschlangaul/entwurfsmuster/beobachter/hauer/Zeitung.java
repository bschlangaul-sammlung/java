package org.bschlangaul.entwurfsmuster.beobachter.hauer;

public class Zeitung {

  // Ein examplarisches Feld.
  private final String titel;

  public Zeitung(String titel) {
    this.titel = titel;
  }

  public String getTitel() {
    return titel;
  }
}
