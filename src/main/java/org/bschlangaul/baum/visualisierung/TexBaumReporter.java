package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.AVLBaumKnoten;
import org.bschlangaul.baum.Baum;
import org.bschlangaul.baum.BaumKnoten;
import org.bschlangaul.cli.KonsoleHelfer;
import org.bschlangaul.helfer.Tex;

public class TexBaumReporter extends BaumReporter {

  private String formatiereKnoten(Baum baum, BaumKnoten knoten) {
    if (knoten instanceof AVLBaumKnoten && baum instanceof AVLBaum) {
      AVLBaum avlBaum = (AVLBaum) baum;
      AVLBaumKnoten avlKnoten = (AVLBaumKnoten) knoten;
      return String.format("\\node[label=%s]{%s};", avlBaum.gibBalance(avlKnoten), knoten.gibSchlüssel());
    } else {
      return knoten.gibSchlüssel().toString();
    }
  }

  private String generiereBaumRekursiv(Baum baum, BaumKnoten knoten, int einrückung) {
    if (knoten != null) {
      String leerzeichen = " ".repeat(einrückung);
      String links = generiereBaumRekursiv(baum, knoten.gibLinks(), einrückung + 2);
      String rechts = generiereBaumRekursiv(baum, knoten.gibRechts(), einrückung + 2);
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

  private String generiereBaum(Baum baum) {
    String tikzMarkup = generiereBaumRekursiv(baum, baum.gibKopf(), 0);
    return Tex.umgebungOption("tikzpicture", String.format("\\Tree\n%s", tikzMarkup), "li binaer baum");
  }

  @Override
  public void visualisiereBaum(Baum baum) {
    System.out.println(generiereBaum(baum));
  }

  @Override
  public void visualisiereÜberschrift(String überschrift) {
    KonsoleHelfer.gibÜberschriftAus(Tex.makro("section", überschrift));
  }

}
