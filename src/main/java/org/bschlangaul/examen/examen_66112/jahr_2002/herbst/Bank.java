package org.bschlangaul.examen.examen_66112.jahr_2002.herbst;

import java.util.ArrayList;

public class Bank {
  @SuppressWarnings("unused")
  private String name;
  private ArrayList<Konto> konten;

  public Bank(String name) {
    this.name = name;
    konten = new ArrayList<Konto>();
  }

  public void rechneAb() {
    for (int i = 0; i <= konten.size(); i++) {
      konten.get(i).rechneAb();
    }
  }

  public void legeAn(String name, int kundenNummer, int kontoNummer, float kontoStand, float zinssatz) {
    Besitzer besitzer = schonVorhanden(name, kundenNummer);
    konten.add(new Sparkonto(kontoNummer, kontoStand, besitzer, zinssatz));
  }

  public Besitzer schonVorhanden(String name, int kundenNummer) {
    if (!konten.isEmpty()) {
      for (int i = 0; i < konten.size(); i++) {
        if (konten.get(i).besitzer.gibKundenNummer() == kundenNummer) {
          return konten.get(i).gibBesitzer();
        }
      }
    }
    return new Besitzer(name, kundenNummer);
  }
}
