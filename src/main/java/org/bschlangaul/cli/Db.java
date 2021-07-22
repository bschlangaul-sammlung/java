package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import io.bretty.solver.normalization.Ausgabe;

import java.util.concurrent.Callable;



@Command(name = "db", aliases = {
    "d" }, mixinStandardHelpOptions = true, description = "Führe Aufgaben zum Themenbereich Datenbanken aus.")
class Db implements Callable<Integer> {

  @Parameters(description = "Attribute in der Form: a,b,c")
  private String attrs;

  @Parameters(description = "Funktionale Abhängigkeiten in der Form (ohne Leerzeichen): a-->b;b-->c;")
  private String funcDeps;

  @Override
  public Integer call() {
    Ausgabe.findeSchlüsselKandidaten(attrs, funcDeps);
    Ausgabe.bestimmeKanonischeÜberdeckung(funcDeps);
    Ausgabe.istIn3NF(attrs, funcDeps);
    Ausgabe.istInBCNF(attrs, funcDeps);
    return 0;
  }
}
