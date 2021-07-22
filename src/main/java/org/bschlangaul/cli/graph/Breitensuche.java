package org.bschlangaul.cli.graph;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.algorithmen.BreitenSucheWarteschlange;

@Command(name = "bfs", aliases = {
    "b" }, description = "Die Breitensuche (breadth-first search = BFS) mit Hilfe einer Warteschlange ausführen.")
public class Breitensuche implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Parameters(index = "1", description = "Der Startknoten.")
  private String startKnoten;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();
    BreitenSucheWarteschlange b = new BreitenSucheWarteschlange(einfachesGraphenFormat);
    b.führeAus(startKnoten);
    return 0;
  }
}
