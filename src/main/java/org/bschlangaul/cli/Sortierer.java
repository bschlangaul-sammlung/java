package org.bschlangaul.cli;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.sortier.BubbleIterativ;
import org.bschlangaul.sortier.Heap;
import org.bschlangaul.sortier.InsertionIterativ;
import org.bschlangaul.sortier.Merge;
import org.bschlangaul.sortier.QuickSaake;
import org.bschlangaul.sortier.SelectionRechtsIterativ;

import org.bschlangaul.sortier.Sortieralgorithmus;

@Command(name = "sortiere", aliases = {
    "so" }, mixinStandardHelpOptions = true, description = "Sortiere mit verschiedenen Algorithmen.")
class Sortierer implements Callable<Integer> {

  static class Algorithmus {
    @Option(names = { "-b", "--bubble" }, description = "Bubblesort.")
    boolean bubble;

    @Option(names = { "-H", "--heap" }, description = "Heapsort.")
    boolean heap;

    @Option(names = { "-i", "--insertion" }, description = "Insertionsort.")
    boolean insertion;

    @Option(names = { "-m", "--merge" }, description = "Mergsort.")
    boolean merge;

    @Option(names = { "-q", "--quick" }, description = "Quicksort.")
    boolean quick;

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
  List<Integer> werte;

  @Override
  public Integer call() {
    Sortieralgorithmus sortierer = null;

    if (algorithmus == null) {
      sortierer = new BubbleIterativ();
    } else if (algorithmus.heap) {
      sortierer = new Heap();
    } else if (algorithmus.insertion) {
      sortierer = new InsertionIterativ();
    } else if (algorithmus.merge) {
      sortierer = new Merge();
    } else if (algorithmus.quick) {
      QuickSaake quick = (QuickSaake) new QuickSaake();
      if (pivotLage == null || pivotLage.mitte) {
        quick.setztePivotMitte();
      } else if (pivotLage.rechts) {
        quick.setztePivotRechts();
      } else if (pivotLage.links) {
        quick.setztePivotLinks();
      }
      sortierer = quick;
    } else if (algorithmus.selection) {
      sortierer = new SelectionRechtsIterativ();
    } else {
      sortierer = new BubbleIterativ();
    }

    int[] zahlen = werte.stream().mapToInt(i -> i).toArray();

    sortierer.setzeZahlen(zahlen);
    sortierer.aktiviereKonsolenAusgabe();
    System.out.println("Sortieralgorithmus: " + sortierer.getClass().getName());
    sortierer.berichte.feld("Eingabe");
    sortierer.sortiere();
    sortierer.berichte.feld("Ausgabe");
    return 0;
  }
}