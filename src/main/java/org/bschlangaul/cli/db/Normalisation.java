package org.bschlangaul.cli.db;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.db.normalisation.TerminalAusgabe;

@Command(name = "normalisation", aliases = {
    "n" }, mixinStandardHelpOptions = true, description = "Führe Aufgaben zum Themenbereich Datenbanken aus.")
class Normalisation implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei-Pfad mit \\FA{a->b} als Inhalt oder als String in der Form (ohne Leerzeichen): a-->b;b-->c;", paramLabel = "<FAs>")
  private String abhängigkeiten;

  @Parameters(index = "1", arity = "0..1", description = "Attribute in der Form: a,b,c")
  private String attribute;

  @Override
  public Integer call() {
    TerminalAusgabe ausgabe = null;

    File texDatei = new File(abhängigkeiten);
    if (texDatei.exists()) {
      ausgabe = TerminalAusgabe.sucheAbhängigkeiten(abhängigkeiten);
    } else if (attribute != null) {
      ausgabe = new TerminalAusgabe(attribute, abhängigkeiten);
    } else {
      ausgabe = new TerminalAusgabe(abhängigkeiten);
    }

    if (ausgabe != null) {
      ausgabe.gibAllesAus();
    }

    return 0;
  }
}
