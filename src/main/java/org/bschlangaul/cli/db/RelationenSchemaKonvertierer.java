package org.bschlangaul.cli.db;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.db.RelationenSchema;
import org.bschlangaul.helfer.TextAusschnitt;

@Command(name = "relationen-schema", aliases = {
    "r" }, description = "Suche in der TeX-Datei nach liRelationenSchemaFormat-Umgebungen und "
        + "formatiere daraus TeX- und SQL-Markup.")
public class RelationenSchemaKonvertierer implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    List<String> ausschnitte = TextAusschnitt.sucheAusschnitteInTextDatei(datei,
        TextAusschnitt.gibTexUmgebungRegex("liRelationenSchemaFormat"));
    for (String ausschnitt : ausschnitte) {
      System.out.println(ausschnitt);
      RelationenSchema.gibAusFürProjektSprachen(ausschnitt);
    }
    return 0;
  }
}
