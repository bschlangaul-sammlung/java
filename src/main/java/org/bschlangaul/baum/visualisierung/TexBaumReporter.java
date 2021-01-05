package org.bschlangaul.baum.visualisierung;

import java.util.ArrayList;

import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.AVLBaumKnoten;
import org.bschlangaul.baum.BinaerBaum;
import org.bschlangaul.baum.BaumKnoten;
import org.bschlangaul.helfer.Tex;

public class TexBaumReporter extends BaumReporter {

  private String formatiereKnoten(BinaerBaum baum, BaumKnoten knoten) {
    if (knoten instanceof AVLBaumKnoten && baum instanceof AVLBaum) {
      AVLBaum avlBaum = (AVLBaum) baum;
      AVLBaumKnoten avlKnoten = (AVLBaumKnoten) knoten;
      return String.format("\\node[label=%s]{%s};", avlBaum.gibBalance(avlKnoten), knoten.gibSchlüssel());
    } else {
      return knoten.gibSchlüssel().toString();
    }
  }

  private String generiereBaumRekursiv(BinaerBaum baum, BaumKnoten knoten, int einrückung) {
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

  @Override
  public String erzeugeBaum(BinaerBaum baum) {
    String tikzMarkup = generiereBaumRekursiv(baum, baum.gibKopf(), 0);
    // return Tex.umgebungOption("tikzpicture", String.format("\\Tree\n%s",
    // tikzMarkup), "li binaer baum");
    return Tex.umgebungOption("tikzpicture", String.format("\\Tree\n%s", tikzMarkup), "li binaer baum") + "\n"
        + Tex.makro("end", "liDiagramm");
  }

  @Override
  public String erzeugeÜberschrift(String überschrift) {
    // return KonsoleHelfer.erzeugeÜberschrift(überschrift);
    return String.format("\n%s{%s}", Tex.makro("begin", "liDiagramm"), überschrift);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public String erzeugeTraversierung(BinaerBaum baum) {
    String ausgabe = "";
    ausgabe += "\n\n\\subsection{Traversierung}\n\n";
    for (int i = 0; i < 4; i++) {
      ArrayList<Comparable> schlüssel = baum.traversiere(i);
      ausgabe += "\\subsubsection{" + BinaerBaum.traversierungsNamen[i] + "}\n";
      int zähler = 0;
      for (Comparable s : schlüssel) {
        zähler++;
        if (zähler < schlüssel.size()) {
          ausgabe += s + ", ";
        } else {
          ausgabe += s.toString();
        }
      }
      ausgabe += "\n\n";
    }
    return ausgabe;
  }

}
