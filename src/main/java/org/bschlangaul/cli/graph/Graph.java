package org.bschlangaul.cli.graph;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "graph", aliases = {
    "g" }, mixinStandardHelpOptions = true, description = "Führe graphspezifische Aufgaben aus.", subcommands = {
        Tex.class, Dijkstra.class, Prim.class, Kruskal.class, Breitensuche.class, TiefensucheStapel.class,
        TiefensucheRekursion.class })

public class Graph implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    return 0;
  }
}
