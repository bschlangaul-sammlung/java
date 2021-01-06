package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.BinaerBaum;

import org.bschlangaul.baum.BinaererSuchBaum;
import org.bschlangaul.baum.visualisierung.BaumReporter;
import org.bschlangaul.baum.visualisierung.TerminalBaumReporter;
import org.bschlangaul.baum.visualisierung.TexBaumReporter;

@Command(name = "baum", aliases = {
    "b" }, mixinStandardHelpOptions = true, description = "Führe baumspezifische Aufgaben aus.")
class UnterBefehlBaum implements Callable<Integer> {

  @Option(names = { "-a", "--avl", "--avl-baum" }, description = "Als AVL-Baum ausgeben.")
  boolean istAvl;

  @Option(names = { "-m", "--min", "--min-halde" }, description = "Als Min-Halde (Heap) ausgeben.")
  boolean istHalde;

  @Option(names = { "-T", "--traversierung" }, description = "Zeige Traversierungsmethoden-Tabelle.")
  boolean zeigeTraversierung;

  @Parameters(arity = "1..*", description = "Einfügen: (setze) 1 2 3; Löschen: lösche 1 2 3.")
  List<String> werte;

  @Override
  public Integer call() {

    BinaerBaum baum;

    if (istAvl) {
      baum = new AVLBaum();
    } else {
      baum = new BinaererSuchBaum();
    }

    if (KommandoZeile.gibRedseligkeit() > 0)
      baum.reporter = new TerminalBaumReporter();

    BaumReporter reporter;

    if (KommandoZeile.gibAusgabe() == Ausgabe.tex)
      reporter = new TexBaumReporter();
    else
      reporter = new TerminalBaumReporter();

    baum.reporter = reporter;
    BaumReporter.redseligkeit = KommandoZeile.gibRedseligkeit();

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
        if (!lösche)
          baum.fügeEin(zahl);
        else
          baum.entferne(zahl);
      }
    }

    if (zeigeTraversierung)
      baum.reporter.berichteTraversierung(baum);

    return 0;
  }
}
