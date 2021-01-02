package org.bschlangaul.baum.tex;

import org.bschlangaul.baum.Knoten;
import org.bschlangaul.helfer.Tex;
import org.bschlangaul.baum.Baum;

public class TexBaumTikz {

  private static String generiereTikzRekursiv(Knoten knoten, int einrückung) {
    if (knoten != null) {
      String leerzeichen = " ".repeat(einrückung);
      String links = generiereTikzRekursiv(knoten.gibLinks(), einrückung + 2);
      String rechts = generiereTikzRekursiv(knoten.gibRechts(), einrückung + 2);
      String leererKnoten = leerzeichen + "  " + "\\edge[blank]; \\node[blank]{};";
      String kinder;

      if (!links.equals("") && !rechts.equals("")) {
        kinder = String.format("\n%s\n%s\n%s", links, rechts, leerzeichen);
      } else if (!links.equals("") && rechts.equals("")) {
        kinder = String.format("\n%s\n%s\n%s", links, leererKnoten, leerzeichen);
      } else if (links.equals("") && !rechts.equals("")) {
        kinder = String.format("\n%s\n%s\n%s", leererKnoten, rechts, leerzeichen);
      } else {
        kinder = "";
      }

      return String.format("%s[.%s %s]", leerzeichen, knoten.gibSchlüssel(), kinder);
    }
    return "";
  }

  public static String generiere(Baum baum) {
    String tikzMarkup = generiereTikzRekursiv(baum.gibKopf(), 0);
    return Tex.umgebungOption("tikzpicture", String.format("\\Tree\n%s", tikzMarkup), "binaerer baum");
  }

}
