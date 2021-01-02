package org.bschlangaul.baum.tex;

import org.bschlangaul.baum.Knoten;
import org.bschlangaul.helfer.Tex;
import org.bschlangaul.baum.Baum;

public class TexTikzBaumErzeuger {

  private static String generiereTikzRekursiv(Knoten knoten, int einrückung) {
    if (knoten != null && knoten.gibSchlüssel() != null) {
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

  private static String umrahmeMitTikzUmgebung(String tikzMarkup) {
    return Tex.umgebungOption("tikzpicture", String.format("\\Tree\n%s", tikzMarkup), "binaerer baum");
  }

  public static String generiere(Baum baum) {
    return umrahmeMitTikzUmgebung(generiereTikzRekursiv(baum.gibKopf(), 0));
  }

}
