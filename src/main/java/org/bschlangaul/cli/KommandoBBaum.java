package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.baum.BBaum;
import org.bschlangaul.baum.visualisierung.TexBBaumReporter;

@Command(name = "bbaum", aliases = {
    "bb" }, mixinStandardHelpOptions = true, description = "Führe Aufgaben zum Thema BBaum aus.")
class KommandoBBaum implements Callable<Integer> {

  @Parameters(arity = "1..*", description = "Einfügen: 1 2 3")
  List<String> werte;

  @Override
  public Integer call() {
    BBaum bbaum = new BBaum(2);
    for (int i = 0; i < werte.size(); i++) {
      String wert = werte.get(i);
      int zahl = Integer.parseInt(wert);
      bbaum.fügeEin(zahl);
    }

    TexBBaumReporter.druckeBaum(bbaum);
    return 0;
  }
}
