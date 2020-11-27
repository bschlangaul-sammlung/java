package org.bschlangaul.liste.kompositum;

public class DatenKnoten extends ListenElement {
  private ListenElement nächstes;
  private DatenElement daten;

  public DatenKnoten(ListenElement n, DatenElement i) {
    nächstes = n;
    daten = i;
  }

  public void setzeNächstes(ListenElement n) {
    nächstes = n;
  }

  public void setzeDaten(DatenElement i) {
    daten = i;
  }

  public ListenElement gibNächstes() {
    return nächstes;
  }

  public DatenElement gibDaten() {
    return daten;
  }

  ///////////// rekursive Listenmethoden ////////////////////
  public DatenKnoten fügeHintenEin(DatenElement daten) {
    nächstes = nächstes.fügeHintenEin(daten);
    return this;
  }

  public DatenElement gibLetzteDaten(DatenElement aktuelleDaten) {
    return nächstes.gibLetzteDaten(aktuelleDaten);
  }

  public int gibSummeAlter() {
    return daten.gibAlter() + nächstes.gibSummeAlter();
  }

  public int gibAnzahlDatenKnoten() {
    return 1 + nächstes.gibAnzahlDatenKnoten();
  }

  public void gibDatenAus() {
    daten.gibDatenAus();
    nächstes.gibDatenAus();
  }
}
