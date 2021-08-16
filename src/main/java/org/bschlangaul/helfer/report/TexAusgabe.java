package org.bschlangaul.helfer.report;

import org.bschlangaul.helfer.Tex;

public class TexAusgabe implements Ausgabe {

  public String überschrift(String überschrift) {
    // return KonsoleHelfer.erzeugeÜberschrift(überschrift);
    return String.format("\n%s{%s}", Tex.makro("begin", "liDiagramm"), überschrift);
  }

  public String tabelle(String[] kopfZeile, String[][] zeilen) {
    return Tex.tabelle(kopfZeile, zeilen);
  }

}
