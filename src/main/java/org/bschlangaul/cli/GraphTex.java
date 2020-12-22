package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.io.File;
import java.nio.file.Files;
import org.bschlangaul.graph.TexGraphenFormat;

@Command(name = "tex-graphen-format", aliases = {
    "t" }, description = "Lese TeX-Datei ein, die Graphen im TeX-Graphenformat enthält.")
class GraphTex implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    String inhalt = Files.readString(datei.toPath());
    new TexGraphenFormat(inhalt);
    return 0;
  }
}
