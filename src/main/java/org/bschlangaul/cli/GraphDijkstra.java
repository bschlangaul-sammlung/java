package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.Dijkstra;

@Command(name = "dijkstra", aliases = {
    "d" }, description = "Den Dijkstra-Algorithmus ausführen.")
class GraphDijkstra implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Parameters(index = "1", description = "Der Startknoten.")
  private String startKnoten;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();

    Dijkstra d = new Dijkstra(einfachesGraphenFormat);
    d.sucheKürzestenPfadMatrix(startKnoten);
    d.reporter.gibErgebnisTabelle();
    d.reporter.gibZwischenschrittTabelleTex();
    return 0;
  }
}
