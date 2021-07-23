package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import io.bretty.solver.normalization.CliOutput;

import java.util.concurrent.Callable;

@Command(name = "db", aliases = {
    "d" }, mixinStandardHelpOptions = true, description = "Führe Aufgaben zum Themenbereich Datenbanken aus.")
class Db implements Callable<Integer> {

  @Parameters(index = "0", description = "Funktionale Abhängigkeiten in der Form (ohne Leerzeichen): a-->b;b-->c;", paramLabel = "<FAs>")
  private String funktionaleAbhaengigkeiten;

  @Parameters(index = "1", arity = "0..1", description = "Attribute in der Form: a,b,c")
  private String attribute;

  @Override
  public Integer call() {
    CliOutput output;
    if (attribute != null) {
      output = new CliOutput(attribute, funktionaleAbhaengigkeiten);
    } else {
      output = new CliOutput(funktionaleAbhaengigkeiten);
    }

    output.findKeys();
    output.findMinimalCover();
    output.isIn3NF();
    output.isInBCNF();
    return 0;
  }
}
