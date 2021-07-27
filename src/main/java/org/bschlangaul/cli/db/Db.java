package org.bschlangaul.cli.db;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "db", aliases = {
    "d" }, mixinStandardHelpOptions = true, description = "Führe Aufgaben zum Themenbereich Datenbanken aus.", subcommands = {
        Normalisation.class, RelationenSchemaKonvertierer.class })
public class Db implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    return 0;
  }
}
