package org.bschlangaul.aufgaben.aud.ab_4.musikliste;

public class Knoten {
  private Knoten naechster;
  private Knoten vorheriger;
  private MusikStueck lied;

  public Knoten(MusikStueck lied) {
    naechster = null;
    this.lied = lied;
    vorheriger = null;
  }

  public Knoten gibNächsten() {
    return naechster;
  }

  public void setzeNächsten(Knoten naechsterKnoten) {
    naechster = naechsterKnoten;
  }

  public MusikStueck gibMusikstück() {
    return lied;
  }

  public Knoten gibVorherigen() {
    return vorheriger;
  }

  public void setzenVorherigen(Knoten vorherigerKnoten) {
    vorheriger = vorherigerKnoten;
  }

  public int zähleEinträge() {
    if (this.gibNächsten() == null) {
      return 1;
    } else {
      return this.gibNächsten().zähleEinträge() + 1;
    }
  }
}
