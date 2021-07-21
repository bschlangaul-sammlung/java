package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.algorithmen.TiefenSucheRekursion;

@Command(name = "dfr", aliases = {
    "r" }, description = "Die Tiefensuche (depth-first search = DFS) rekursiv ausführen.")
class UnterBefehlGraphTiefensucheRekursion implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Parameters(index = "1", description = "Der Startknoten.")
  private String startKnoten;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();
    TiefenSucheRekursion t = new TiefenSucheRekursion(einfachesGraphenFormat);
    t.führeAus(startKnoten);
    return 0;
  }
}
