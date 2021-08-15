package org.bschlangaul.aufgaben.aud.listen.musikliste;

public class Knoten {
  private Knoten nächster;
  private Knoten vorheriger;
  private MusikStueck lied;

  public Knoten(MusikStueck lied) {
    nächster = null;
    this.lied = lied;
    vorheriger = null;
  }

  public Knoten gibNächsten() {
    return nächster;
  }

  public void setzeNächsten(Knoten nächsterKnoten) {
    nächster = nächsterKnoten;
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
