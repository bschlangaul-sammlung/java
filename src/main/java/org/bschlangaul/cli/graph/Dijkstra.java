package org.bschlangaul.cli.graph;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.algorithmen.KuerzesterPfadDijkstra;
import org.bschlangaul.graph.einfaches_format.GraphenFormat;

import org.bschlangaul.cli.KommandoZeile;
import org.bschlangaul.cli.Ausgabe;

@Command(name = "dijkstra", aliases = { "d" }, description = "Den Dijkstra-Algorithmus ausführen.")
public class Dijkstra implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Parameters(index = "1", description = "Der Startknoten.")
  private String startKnoten;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();

    KuerzesterPfadDijkstra dijkstra = new KuerzesterPfadDijkstra(einfachesGraphenFormat);
    dijkstra.sucheKürzestenPfadMatrix(startKnoten);
    new GraphenFinder(datei).gibTexAus();

    boolean alsTex = KommandoZeile.gibAusgabe() == Ausgabe.tex;

    if (alsTex) {
      GraphenFormat graphenFormat = new GraphenFormat(einfachesGraphenFormat);
      graphenFormat.gibAlsTexUmgebung();
    }

    dijkstra.berichte.zwischenschrittTabelle(alsTex);
    dijkstra.berichte.ergebnisTabelle(alsTex);
    return 0;
  }
}
