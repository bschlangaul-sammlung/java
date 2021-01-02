package org.bschlangaul.cli;

import org.bschlangaul.graph.GraphenFinder;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.io.File;

@Command(name = "tex", aliases = {
    "t" }, description = "Lese Text-Datei ein und versuche einen Graph zu erkennen, formatiere den Graphen dann für TeX.")
class UnterBefehltGraphTex implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    new GraphenFinder(datei).gibTexAus();
    return 0;
  }
}
