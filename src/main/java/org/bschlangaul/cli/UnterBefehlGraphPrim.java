package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.GraphenFinder;
import org.bschlangaul.graph.algorithmen.MinimalerSpannbaumPrim;

@Command(name = "prim", aliases = {
    "p" }, description = "Den Algorithmus von Prim ausführen.")
class UnterBefehlGraphPrim implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    String einfachesGraphenFormat = new GraphenFinder(datei).gibGraphenFormatText();
    MinimalerSpannbaumPrim p = new MinimalerSpannbaumPrim(einfachesGraphenFormat);
    p.führeAus();
    return 0;
  }
}
