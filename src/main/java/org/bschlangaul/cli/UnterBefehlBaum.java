package org.bschlangaul.cli;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.BinaerBaum;

import org.bschlangaul.baum.BinaererSuchBaum;
import org.bschlangaul.baum.Halde;
import org.bschlangaul.baum.MaxHalde;
import org.bschlangaul.baum.MinHalde;
import org.bschlangaul.baum.visualisierung.BaumReporter;
import org.bschlangaul.baum.visualisierung.TerminalBaumReporter;
import org.bschlangaul.baum.visualisierung.TexBaumReporter;

@Command(name = "baum", aliases = {
    "b" }, mixinStandardHelpOptions = true, description = "Führe baumspezifische Aufgaben aus.")
class UnterBefehlBaum implements Callable<Integer> {

  @ArgGroup(exclusive = true, multiplicity = "1")
  BaumArt baumArt;

  static class BaumArt {
    @Option(names = { "-s", "--such", "--such-baum" }, description = "Als binärer Suchbaum ausgeben.")
    boolean such;

    @Option(names = { "-a", "--avl", "--avl-baum" }, description = "Als AVL-Baum ausgeben.")
    boolean avl;

    @Option(names = { "-m", "--min", "--min-halde" }, description = "Als Min-Halde (Heap) ausgeben.")
    boolean minHalde;

    @Option(names = { "-M", "--max", "--max-halde" }, description = "Als Max-Halde (Heap) ausgeben.")
    boolean maxHalde;
  }

  @Option(names = { "-T", "--traversierung" }, description = "Zeige Traversierungsmethoden-Tabelle.")
  boolean zeigeTraversierung;

  @Parameters(arity = "1..*", description = "Einfügen: (setze) 1 2 3; Löschen: lösche 1 2 3.")
  List<String> werte;

  private void fügeHinzuOderLösche(Object haldeOderBaum) {
    boolean lösche = false;
    for (int i = 0; i < werte.size(); i++) {
      String wert = werte.get(i);
      if (wert.equals("lösche")) {
        lösche = true;
      } else if (wert.equals("setze")) {
        lösche = false;
      }
      if (!wert.equals("lösche") && !wert.equals("setze")) {
        int zahl = Integer.parseInt(wert);
        if (haldeOderBaum instanceof BinaerBaum) {
          BinaerBaum baum = (BinaerBaum) haldeOderBaum;
          if (!lösche)
            baum.fügeEin(zahl);
          else
            baum.entferne(zahl);
        } else {
          Halde halde = (Halde) haldeOderBaum;
          if (!lösche)
            halde.fügeEin(zahl);
          else
            halde.entferne(zahl);
        }
      }
    }
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Integer call() {

    BaumReporter reporter;
    BaumReporter.redseligkeit = KommandoZeile.gibRedseligkeit();

    if (KommandoZeile.gibAusgabe() == Ausgabe.tex)
      reporter = new TexBaumReporter();
    else
      reporter = new TerminalBaumReporter();

    if (baumArt.minHalde || baumArt.maxHalde) {
      Halde halde;
      if (baumArt.minHalde)
        halde = new MinHalde<>();
      else
        halde = new MaxHalde<>();

      halde.reporter = reporter;

      fügeHinzuOderLösche(halde);
      return 0;
    }

    BinaerBaum baum;

    if (baumArt.avl) {
      baum = new AVLBaum();
    } else {
      baum = new BinaererSuchBaum();
    }

    if (KommandoZeile.gibRedseligkeit() > 0)
      baum.reporter = new TerminalBaumReporter();

    baum.reporter = reporter;

    fügeHinzuOderLösche(baum);

    if (zeigeTraversierung)
      baum.reporter.berichteTraversierung(baum);

    return 0;
  }
}
