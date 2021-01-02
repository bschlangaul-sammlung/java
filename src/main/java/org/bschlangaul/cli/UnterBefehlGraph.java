package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "graph", aliases = {
    "g" }, mixinStandardHelpOptions = true, description = "Führe graphspezifische Aufgaben aus.", subcommands = {
        UnterBefehltGraphTex.class, UnterBefehlGraphDijkstra.class })
class UnterBefehlGraph implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    return 0;
  }
}
