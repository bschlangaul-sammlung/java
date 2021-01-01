package org.bschlangaul.cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.bschlangaul.db.RelationenSchema;

class ProjektSprache {

  String name;
  String inhalt;

  public ProjektSprache(String name, String inhalt) {
    this.name = name.trim();
    this.inhalt = inhalt.trim();
  }
}

@Command(name = "projekt-sprachen", aliases = {
    "s" }, description = "Nach Projektsprachen in einer TeX-Datei suchen und diese dann ausführen.")
public class SubKommandoProjektSprachenFinder implements Callable<Integer> {

  String umgebungsName = "liProjektSprache";

  private ProjektSprache[] sucheNachSprachen(File datei) {
    ArrayList<ProjektSprache> ausgabe = new ArrayList<ProjektSprache>();

    try {
      String inhalt = Files.readString(datei.toPath());
      Pattern pattern = Pattern.compile(
          "\\\\begin\\{" + umgebungsName + "\\}\\{(?<name>.*?)\\}(?<inhalt>.*?)\\\\end\\{" + umgebungsName + "\\}",
          Pattern.DOTALL);
      Matcher ergebnis = pattern.matcher(inhalt);
      while (ergebnis.find()) {
        ausgabe.add(new ProjektSprache(ergebnis.group("name"), ergebnis.group("inhalt")));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    ProjektSprache[] sprachen = new ProjektSprache[ausgabe.size()];

    ausgabe.toArray(sprachen);
    return sprachen;
  }

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    sucheNachSprachen(datei);
    for (ProjektSprache sprache : sucheNachSprachen(datei)) {
      switch (sprache.name) {
        case "RelationenSchema":
          RelationenSchema schema = new RelationenSchema(sprache.inhalt);

          Helfer.gibÜberschriftAus("SQL-CREATE-Befehl");
          System.out.println(schema.baueSqlCreate());

          Helfer.gibÜberschriftAus("SQL-INSERT-Befehl");
          System.out.println(schema.baueSqlInsert());

          Helfer.gibÜberschriftAus("Übungsdatenbank");
          System.out.println(schema.baueÜbungsdatenbank());

          Helfer.gibÜberschriftAus("TeX");
          System.out.println(schema.baueTeX());
          break;

        default:
          break;
      }

    }
    return 0;
  }
}
