package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.baum.BBaum;
import org.bschlangaul.baum.report.TexBBaumAusgabe;

@Command(name = "bbaum", aliases = {
    "bb" }, mixinStandardHelpOptions = true, description = "Führe Aufgaben zum Thema BBaum aus.")
class KommandoBBaum implements Callable<Integer> {

  @Option(names = { "-o",
      "--ordnung" }, defaultValue = "2", description = "Die Ordnung des B-Baums. Ist die Ordnung beispielsweise 2, dann muss jede Seite mindestens 2 Knoten und maximal 4 Knoten aufweisen.")
  static int ordnung;

  @Parameters(arity = "1..*", description = "Einfügen: 1 2 3")
  List<String> werte;

  @Override
  public Integer call() {
    BBaum bbaum = new BBaum(ordnung);
    for (int i = 0; i < werte.size(); i++) {
      String wert = werte.get(i);
      int zahl = Integer.parseInt(wert);
      bbaum.fügeEin(zahl);
    }

    TexBBaumAusgabe.druckeBaum(bbaum);
    return 0;
  }
}
