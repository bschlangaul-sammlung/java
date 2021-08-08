package org.bschlangaul.helfer.report;

import com.jakewharton.fliptables.FlipTable;

import org.bschlangaul.cli.KonsoleHelfer;

class KonsolenAusgabe implements Ausgabe {

  public String überschrift(String überschrift) {
    return KonsoleHelfer.erzeugeÜberschrift(überschrift);
  }

  public String tabelle(String[] kopfZeile, String[][] zeilen) {
    return FlipTable.of(kopfZeile, zeilen);
  }

}
