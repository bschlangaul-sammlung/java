package org.bschlangaul.aufgaben.aud.listen.musikliste;

public class MusikListe {
  private Knoten erster;
  private int anzahl;

  public MusikListe() {
    erster = null;
    anzahl = 0;
  }

  public void setzeErsten(Knoten e) {
    erster = e;
    aktualisiereAnzahl();
  }

  public int aktualisiereAnzahl() {
    if (erster == null) {
      anzahl = 0;
    } else {
      int n = 1;
      Knoten k = erster;
      while (!(k.gibNächsten() == null)) {
        k = k.gibNächsten();
        n = n + 1;
      }
      anzahl = n;
    }
    return anzahl;
  }

  public String gibMusikstückListe() {
    String ausgabe = " ";
    if (anzahl >= 1) {
      Knoten k = erster;
      ausgabe = k.gibMusikstück().gibTitel();
      for (int i = 1; i <= anzahl - 1; i++) {
        k = k.gibNächsten();
        ausgabe = ausgabe + " | " + k.gibMusikstück().gibTitel();
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
    } else {
      Knoten k = erster;
      for (int i = 1; i <= position - 1; i++) {
        k = k.gibNächsten();
      }
      return k;
    }
  }

  public int zähleEinträge() {
    if (erster == null) {
      return 0;
    } else {
      return erster.zähleEinträge();
    }
  }
}
