package org.bschlangaul.cli;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.sortier.BubbleIterativ;
import org.bschlangaul.sortier.InsertionIterativ;

import org.bschlangaul.sortier.Sortieralgorithmus;

@Command(name = "sortiere", aliases = {
    "so" }, mixinStandardHelpOptions = true, description = "Sortiere mit verschiedenen Algorithmen.")
class Sortierer implements Callable<Integer> {

  @ArgGroup(exclusive = true, multiplicity = "1")
  Algorithmus algorithmus;

  static class Algorithmus {
    @Option(names = { "-b", "--bubble" }, description = "Bubblesort.")
    boolean bubble;

    @Option(names = { "-i", "--insertion" }, description = "Insertionsort.")
    boolean insertion;
  }

  @Parameters(arity = "1..*", description = "Eine Zahlenfolge, die sortiert werden soll.")
  List<Integer> werte;

  @Override
  public Integer call() {
    Sortieralgorithmus sortierer = null;

    if (algorithmus.bubble) {
      sortierer = new BubbleIterativ();
    } else if (algorithmus.insertion) {
      sortierer = new InsertionIterativ();
    } else {
      sortierer = new BubbleIterativ();
    }

    int[] zahlen = werte.stream().mapToInt(i->i).toArray();

    sortierer.setzeZahlen(zahlen);
    sortierer.sortiere();
    return 0;
  }
}
