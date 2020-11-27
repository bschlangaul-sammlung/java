package org.bschlangaul.helfer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.bschlangaul.baum.BinaerBaum;
import org.bschlangaul.baum.Knoten;
import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.Baum;
import org.bschlangaul.liste.saake.WarteschlangeFehler;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Erzeugt eine visuelle Darstellung eines Binärbaums für das TeX-Programm TikZ
 * bzw. tikz-qtree.
 */
@SuppressWarnings("rawtypes")
public class BaumTikzKonvertierer {

  ArrayList<String> aktionen;

  String baumArt;

  Baum baum;

  Writer ziel;

  public BaumTikzKonvertierer(String baumArt) {
    aktionen = new ArrayList<String>();
    this.baumArt = baumArt;
    if (baumArt == "avl") {
      baum = new AVLBaum();
    } else {
      baum = new BinaerBaum();
    }
    öffneTeXDokument();
  }

  public BaumTikzKonvertierer() {
    this("binaer");
  }

  private void öffneTeXDokument() {
    DateTimeFormatter datum = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    try {
      ziel = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(System.getProperty("user.home")
          + "/git-repositories/github/hbschlang/lehramt-informatik/tmp-binaerer-baum__"
          + datum.format(LocalDateTime.now()) + ".tex"), "UTF-8"));
      ziel.write("\\documentclass{lehramt-informatik}\n" + "\\InformatikPakete{baum}\n\n" + "\\begin{document}\n");

      if (baumArt == "avl") {
        ziel.write("\\section{AVL-Baum}\n");
      } else {
        ziel.write("\\section{Binärer Suchbaum}\n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void schließeTeXDokument() {
    try {
      ziel.write("\\end{document}\n");
      ziel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String generiereTikzRekursiv(Knoten knoten, int einrückung) {
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

  private String umrahmeMitTikzUmgebung(String baum) {
    String ausgabe = "\n\\subsection{" + String.join(", ", aktionen) + "}\n\n"
        + String.format("\\begin{tikzpicture}[binaerer baum]\n\\Tree\n%s\n\\end{tikzpicture}\n", baum);
    aktionen.clear();
    return ausgabe;
  }

  /**
   * Einstieg
   *
   * @param knoten
   */
  public void generiereTikz() {
    try {
      String inhalt = umrahmeMitTikzUmgebung(generiereTikzRekursiv(baum.gibKopf(), 0));
      System.out.println(inhalt);
      ziel.write(inhalt);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void generiereTraversierung() {
    try {
      ziel.write("\n\n\\subsection{Traversierung}\n\n");
      for (int i = 0; i < 4; i++) {
        ArrayList<Comparable> schlüssel = baum.traversiere(i);
        ziel.write("\\subsubsection{" + BinaerBaum.traversierungsNamen[i] + "}\n");
        int zähler = 0;
        for (Comparable s : schlüssel) {
          zähler++;
          if (zähler < schlüssel.size()) {
            ziel.write(s + ", ");
          } else {
            ziel.write(s.toString());
          }
        }
        ziel.write("\n\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Füge mehrere Werte auf einmal ein.
   *
   * @param werte Mehrere Werte.
   *
   * @return Wahr, wenn das Einfügen erfolgreich war, d. h. alle Werte eingefügt
   *         werden konnten. Konnte ein Wert nicht eingefügt werden, wird falsch
   *         zurück gegeben.
   */
  public boolean fügeEin(Comparable... werte) {
    String aktion = "Füge ein: ";
    for (int i = 0; i < werte.length; i++) {
      aktion = aktion + werte[i] + ", ";
    }
    aktionen.add(aktion);
    boolean ergebnis = true;
    boolean tmp;
    for (Comparable wert : werte) {
      tmp = baum.fügeEin(wert);
      if (!tmp) {
        ergebnis = false;
      }
    }
    return ergebnis;
  }

  public static void main(String[] args) throws WarteschlangeFehler {
    // BinaerBaumNachTikz baum = new BinaerBaumNachTikz();
    // baum.fügeEin(4, 6, 19, 2, 3, 15, 1, 8, 5, 7);
    // baum.generiereTikz();
    // baum.fügeEin(9, 10, 11);
    // baum.generiereTikz();
    // baum.schließeTeXDokument();

    BaumTikzKonvertierer baum = new BaumTikzKonvertierer();
    baum.fügeEin(7, 3, 6, 9, 2, 8, 5, 1);
    baum.generiereTikz();
    baum.generiereTraversierung();
    baum.schließeTeXDokument();

    // BaumTikzKonvertierer baum = new BaumTikzKonvertierer("avl");
    // baum.fügeEin(1, 2, 3, 4, 5);
    // baum.generiereTikz();
    // baum.schließeTeXDokument();
  }

}
