package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.algorithmen.KuerzesterPfadDijkstra;

@Command(name = "dijkstra", aliases = {
    "d" }, description = "Den Dijkstra-Algorithmus ausführen.")
class UnterBefehlGraphDijkstra implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Parameters(index = "1", description = "Der Startknoten.")
  private String startKnoten;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();

    KuerzesterPfadDijkstra d = new KuerzesterPfadDijkstra(einfachesGraphenFormat);
    d.sucheKürzestenPfadMatrix(startKnoten);
    boolean alsTex = KommandoZeile.gibAusgabe() == Ausgabe.tex;
    d.reporter.gibZwischenschrittTabelle(alsTex);
    d.reporter.gibErgebnisTabelle(alsTex);
    return 0;
  }
}
