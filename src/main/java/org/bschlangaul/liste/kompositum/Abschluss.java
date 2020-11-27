package org.bschlangaul.liste.kompositum;

public class Abschluss extends ListenElement {
  public ListenElement gibNächstes() {
    return this;
  }

  public DatenElement gibDaten() {
    return null;
  }

  public DatenKnoten fügeHintenEin(DatenElement daten) {
    return new DatenKnoten(this, daten);
  }

  public DatenElement gibLetzteDaten(DatenElement aktuellerInhalt) {
    return aktuellerInhalt;
  }

  public int gibSummeAlter() {
    return 0;
  }

  public int gibAnzahlDatenKnoten() {
    return 0;
  }

  public void gibDatenAus() {
  }
}
