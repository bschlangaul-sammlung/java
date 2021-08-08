package org.bschlangaul.helfer.report;

import com.jakewharton.fliptables.FlipTable;

import org.bschlangaul.cli.KonsoleHelfer;

public class KonsolenReporter extends Reporter {

  @Override
  public String erzeugeÜberschrift(String überschrift) {
    return KonsoleHelfer.erzeugeÜberschrift(überschrift);
  }

  public String erzeugeTabelle(String[] kopfZeile, String[][] zeilen) {
    return FlipTable.of(kopfZeile, zeilen);
  }

}
