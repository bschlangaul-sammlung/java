package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.algorithmen.MinimalerSpannbaumKruskal;

@Command(name = "kruskal", aliases = {
    "k" }, description = "Den Algorithmus von Kruskal ausführen.")
class UnterBefehlGraphKruskal implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();
    MinimalerSpannbaumKruskal k = new MinimalerSpannbaumKruskal(einfachesGraphenFormat);
    k.führeAus();
    return 0;
  }
}
