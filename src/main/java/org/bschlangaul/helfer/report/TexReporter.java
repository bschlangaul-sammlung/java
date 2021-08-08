package org.bschlangaul.helfer.report;

import org.bschlangaul.helfer.Tex;

public class TexReporter extends Reporter {

  @Override
  public String erzeugeÜberschrift(String überschrift) {
    // return KonsoleHelfer.erzeugeÜberschrift(überschrift);
    return String.format("\n%s{%s}", Tex.makro("begin", "liDiagramm"), überschrift);
  }


  public String erzeugeTabelle(String[] kopfZeile, String[][] zeilen) {
    return Tex.tabelle(kopfZeile, zeilen);
  }


}
