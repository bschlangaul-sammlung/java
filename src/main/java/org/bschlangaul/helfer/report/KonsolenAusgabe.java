package org.bschlangaul.helfer.report;

import com.jakewharton.fliptables.FlipTable;
import org.bschlangaul.helfer.Farbe;

import org.bschlangaul.cli.KonsoleHelfer;

public class KonsolenAusgabe implements Ausgabe {

  public String überschrift(String überschrift) {
    return KonsoleHelfer.erzeugeÜberschrift(überschrift);
  }

  public String tabelle(String[] kopfZeile, String[][] zeilen) {
    return FlipTable.of(kopfZeile, zeilen);
  }

  public String rot(Object objekt) {
    return Farbe.rot(objekt);
  }

  public String grün(Object objekt) {
    return Farbe.grün(objekt);
  }

  public String gelb(Object objekt) {
    return Farbe.gelb(objekt);
  }

}
