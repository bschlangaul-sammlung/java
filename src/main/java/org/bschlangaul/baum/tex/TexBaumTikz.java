package org.bschlangaul.baum.tex;

import org.bschlangaul.baum.Knoten;
import org.bschlangaul.cli.KonsoleHelfer;
import org.bschlangaul.helfer.Tex;
import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.AVLKnoten;
import org.bschlangaul.baum.Baum;

public class TexBaumTikz {

  private static String formatiereKnoten(Baum baum, Knoten knoten) {
    if (knoten instanceof AVLKnoten && baum instanceof AVLBaum) {
      AVLBaum avlBaum = (AVLBaum) baum;
      AVLKnoten avlKnoten = (AVLKnoten) knoten;
      return String.format("\\node[label=%s]{%s};", avlBaum.gibBalance(avlKnoten), knoten.gibSchlüssel());
    } else {
      return knoten.gibSchlüssel().toString();
    }
  }

  private static String generiereTikzRekursiv(Baum baum, Knoten knoten, int einrückung) {
    if (knoten != null) {
      String leerzeichen = " ".repeat(einrückung);
      String links = generiereTikzRekursiv(baum, knoten.gibLinks(), einrückung + 2);
      String rechts = generiereTikzRekursiv(baum, knoten.gibRechts(), einrückung + 2);
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

      return String.format("%s[.%s %s]", leerzeichen, formatiereKnoten(baum, knoten), kinder);
    }
    return "";
  }

  public static String generiere(Baum baum) {
    String tikzMarkup = generiereTikzRekursiv(baum, baum.gibKopf(), 0);
    return Tex.umgebungOption("tikzpicture", String.format("\\Tree\n%s", tikzMarkup), "li binaer baum");
  }

  public static String generiere(Baum baum, Knoten knoten) {
    String tikzMarkup = generiereTikzRekursiv(baum, knoten, 0);
    return Tex.umgebungOption("tikzpicture", String.format("\\Tree\n%s", tikzMarkup), "li binaer baum");
  }

  public static void gibAus(Baum baum) {
    System.out.println(generiere(baum));
  }

  public static void gibAus(Baum baum, Knoten knoten) {
    System.out.println(generiere(baum, knoten));
  }


  public static void gibAus(Baum baum, String überschrift) {
    KonsoleHelfer.gibÜberschriftAus(Tex.makro("section", überschrift));
    gibAus(baum);
  }

  public static void gibAus(Baum baum, Knoten knoten, String überschrift) {
    KonsoleHelfer.gibÜberschriftAus(Tex.makro("section", überschrift));
    gibAus(baum, knoten);
  }

}
