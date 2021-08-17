package org.bschlangaul.cli;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.sortier.Sortierer;

@Command(name = "sortiere", aliases = {
    "so" }, mixinStandardHelpOptions = true, description = "Sortiere mit verschiedenen Algorithmen.")
class KommandoSortierer implements Callable<Integer> {

  static class Algorithmus {
    @Option(names = { "-b", "--bubble" }, description = "Bubblesort.")
    boolean bubble;

    @Option(names = { "-H", "--heap" }, description = "Heapsort.")
    boolean heap;

    @Option(names = { "-i", "--insertion" }, description = "Insertionsort.")
    boolean insertion;

    @Option(names = { "-m", "--merge" }, description = "Mergsort.")
    boolean merge;

    @Option(names = { "-q", "--quick-saake" }, description = "Quicksort nach Saake.")
    boolean quickSaake;

    @Option(names = { "-Q", "--quick-horare" }, description = "Quicksort original nach Horare.")
    boolean quickHorare;

    @Option(names = { "-s", "--selection" }, description = "Selectionsort.")
    boolean selection;
  }

  @ArgGroup(exclusive = true, multiplicity = "0..1")
  Algorithmus algorithmus;

  static class PivotLage {
    @Option(names = { "--links" }, description = "Pivot-Element links (Nur bei Quicksort relevant).")
    boolean links;

    @Option(names = { "--mitte" }, description = "Pivot-Element in der Mitte (Nur bei Quicksort relevant).")
    boolean mitte;

    @Option(names = { "--rechts" }, description = "Pivot-Element rechts (Nur bei Quicksort relevant).")
    boolean rechts;
  }

  @ArgGroup(exclusive = true, multiplicity = "0..1")
  PivotLage pivotLage;

  @Parameters(arity = "1..*", description = "Eine Zahlenfolge, die sortiert werden soll.")
  List<String> werte;

  @Override
  public Integer call() {

    Sortierer sortierer = new Sortierer();

    if (algorithmus == null) {
      sortierer.algorithmus("BubbleIterativ");
    } else if (algorithmus.heap) {
      sortierer.algorithmus("Heap");
    } else if (algorithmus.insertion) {
      sortierer.algorithmus("InsertionIterativ");
    } else if (algorithmus.merge) {
      sortierer.algorithmus("Merge");
    } else if (algorithmus.quickSaake) {
      sortierer.algorithmus("QuickSaake");
    } else if (algorithmus.quickHorare) {
      sortierer.algorithmus("QuickHorare");
    } else if (algorithmus.selection) {
      sortierer.algorithmus("SelectionRechtsIterativ");
    } else {
      sortierer.algorithmus("BubbleIterativ");
    }

    if (pivotLage != null) {
      if (pivotLage.mitte) {
        sortierer.pivot("mitte");
      } else if (pivotLage.rechts) {
        sortierer.pivot("rechts");
      } else if (pivotLage.links) {
        sortierer.pivot("links");
      }
    }

    sortierer.zahlen(werte).konsole().sortiere();
    return 0;
  }
}
