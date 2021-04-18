package org.bschlangaul.helfer;

import com.jakewharton.fliptables.FlipTable;

public class Tabelle {
  public static String gibAus(String[] kopfZeile, String[][] zeilen, boolean alsTex) {
    if (alsTex)
      return Tex.tabelle(kopfZeile, zeilen);
    else
      return FlipTable.of(kopfZeile, zeilen);
  }
}
