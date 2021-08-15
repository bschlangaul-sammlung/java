package org.bschlangaul.aufgaben.aud.listen.musikliste;

public class MusikListe {
  private Knoten erster;
  private int anzahl;

  public MusikListe() {
    erster = null;
    anzahl = 0;
  }

  public void setzeErsten(Knoten knoten) {
    erster = knoten;
    aktualisiereAnzahl();
  }

  public int aktualisiereAnzahl() {
    if (erster == null) {
      anzahl = 0;
    } else {
      int zähler = 1;
      Knoten knoten = erster;
      while (!(knoten.gibNächsten() == null)) {
        knoten = knoten.gibNächsten();
        zähler = zähler + 1;
      }
      anzahl = zähler;
    }
    return anzahl;
  }

  public String gibMusikstückListe() {
    String ausgabe = " ";
    if (anzahl >= 1) {
      Knoten knoten = erster;
      ausgabe = knoten.gibMusikstück().gibTitel();
      for (int i = 1; i <= anzahl - 1; i++) {
        knoten = knoten.gibNächsten();
        ausgabe = ausgabe + " | " + knoten.gibMusikstück().gibTitel();
      }
    }
    return ausgabe;
  }

  public Knoten entnimmOben() {
    if (erster == null) {
      return erster;
    }
    Knoten alterKnoten = erster;
    erster = erster.gibNächsten();
    aktualisiereAnzahl();
    return alterKnoten;
  }

  public Knoten gibKnoten(int position) {
    if ((position < 1) || (position > anzahl)) {
      System.out.println(" FEHLER ! ");
      return null;
    }
    Knoten knoten = erster;
    for (int i = 1; i <= position - 1; i++) {
      knoten = knoten.gibNächsten();
    }
    return knoten;
  }

  public int zähleEinträge() {
    if (erster == null) {
      return 0;
    }
    return erster.zähleEinträge();
  }
}
