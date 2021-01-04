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
  boolean isAvl;

  @Parameters(arity = "1..*", description = "Zahlen, die in den Baum eingefügt werden sollen.")
  List<Integer> werte;

  @Override
  public Integer call() {

    BinaerBaum baum;

    if (isAvl) {
      baum = new AVLBaum();
    } else {
      baum = new BinaererSuchBaum();
    }

    if (KommandoZeile.gibRedseligkeit() > 0)
      baum.reporter = new TerminalBaumReporter();

    BaumReporter reporter;

    if (KommandoZeile.ausgabe == Ausgabe.tex)
      reporter = new TexBaumReporter();
    else
      reporter = new TerminalBaumReporter();

    baum.reporter = reporter;
    BaumReporter.redseligkeit = KommandoZeile.gibRedseligkeit();

    for (int i = 0; i < werte.size(); i++) {
      baum.fügeEin(werte.get(i));
    }

    return 0;
  }
}
