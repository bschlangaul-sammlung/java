package org.bschlangaul.cli.db;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Callable;

import org.bschlangaul.db.normalisation.KommandozeilenAusgabe;

@Command(name = "normalisation", aliases = {
    "n" }, mixinStandardHelpOptions = true, description = "Führe Aufgaben zum Themenbereich Datenbanken aus.")
class Normalisation implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei-Pfad mit \\FA{a->b} als Inhalt oder als String in der Form (ohne Leerzeichen): a-->b;b-->c;", paramLabel = "<FAs>")
  private String abhängigkeiten;

  @Parameters(index = "1", arity = "0..1", description = "Attribute in der Form: a,b,c")
  private String attribute;

  @Override
  public Integer call() {
    KommandozeilenAusgabe ausgabe = null;

    File texDatei = new File(abhängigkeiten);
    if (texDatei.exists()) {
      try {
        ausgabe = KommandozeilenAusgabe.sucheAbhängigkeitenInText(Files.readString(texDatei.toPath()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (attribute != null) {
      ausgabe = new KommandozeilenAusgabe(attribute, abhängigkeiten);
    } else {
      ausgabe = new KommandozeilenAusgabe(abhängigkeiten);
    }

    if (ausgabe != null) {
      ausgabe.gibAllesAus();
    }

    return 0;
  }
}
