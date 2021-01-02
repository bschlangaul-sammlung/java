package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import tech.vanyo.tree_printer.TreePrinter;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.Baum;

import org.bschlangaul.baum.BinaerBaum;
import org.bschlangaul.baum.Knoten;

@Command(name = "baum", aliases = {
    "b" }, mixinStandardHelpOptions = true, description = "Führe baumspezifische Aufgaben aus.")
class UnterBefehlBaum implements Callable<Integer> {

  @Option(names = { "-a", "--avl", "--avl-baum" }, description = "Als AVL-Baum ausgeben.")
  boolean isAvl;

  @Parameters(arity = "1..*", description = "Zahlen, die in den Baum eingefügt werden sollen.")
  List<Integer> werte;

  @Override
  public Integer call() {

    Baum baum;

    if (isAvl) {
      baum = new AVLBaum();
    } else {
      baum = new BinaerBaum();
    }

    for (int i = 0; i < werte.size(); i++) {
      baum.fügeEin(werte.get(i));
    }
    Knoten wurzel = baum.gibKopf();

    TreePrinter<Knoten> printer = new TreePrinter<>(knoten -> ("" + knoten.gibSchlüssel()), n -> n.gibLinks(), n -> n.gibRechts());

    printer.setHspace(2);
    printer.setSquareBranches(false);
    System.out.println();

    printer.printTree(wurzel);
    System.out.println();

    return 0;
  }
}
