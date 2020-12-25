package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.TexDateiUntersucher;

@Command(name = "einfaches-graphen-format", aliases = {
    "e" }, description = "Lese TeX-Datei ein, die Graphen im einfachen Graphenformat enthält.")
class GraphEinfach implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    String inhalt = Files.readString(datei.toPath());
    //TexDateiUntersucher.sucheNachEinfachem(inhalt);
    return 0;
  }
}
