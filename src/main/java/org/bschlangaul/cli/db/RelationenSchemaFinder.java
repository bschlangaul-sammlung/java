package org.bschlangaul.cli.db;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.db.RelationenSchema;
import org.bschlangaul.helfer.TextAusschnittFinder;

@Command(name = "relationen-schema", aliases = {
    "r" }, description = "Suche in der TeX-Datei nach rModell-Umgebungen und gib SQL-Befehle dazu aus.")
public class RelationenSchemaFinder implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    List<String> ausschnitte = TextAusschnittFinder.sucheAusschnitteInTextDatei(datei, "");

    for (String ausschnitt : ausschnitte) {
      RelationenSchema.gibAusFürProjektSprachen(ausschnitt);
    }
    return 0;
  }
}
