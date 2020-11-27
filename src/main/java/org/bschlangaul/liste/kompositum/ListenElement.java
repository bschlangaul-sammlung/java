package org.bschlangaul.liste.kompositum;

public abstract class ListenElement {
  // Methoden des einzelnen ListenElements
  public abstract ListenElement gibNächstes();

  public abstract DatenElement gibDaten();

  // rekursive Methoden der verketteten Liste
  public abstract DatenElement gibLetzteDaten(DatenElement aktuellerInhalt);

  public abstract DatenKnoten fügeHintenEin(DatenElement daten);

  public abstract int gibAnzahlDatenKnoten();

  public abstract int gibSummeAlter();

  public abstract void gibDatenAus();
}
