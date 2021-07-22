package org.bschlangaul.cli.graph;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.algorithmen.TiefenSucheStapel;

@Command(name = "dfs", aliases = {
    "f" }, description = "Die Tiefensuche (depth-first search = DFS) mit Hilfe eines Stapels ausführen.")
public class TiefensucheStapel implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Parameters(index = "1", description = "Der Startknoten.")
  private String startKnoten;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();
    TiefenSucheStapel t = new TiefenSucheStapel(einfachesGraphenFormat);
    t.führeAus(startKnoten);
    return 0;
  }
}
