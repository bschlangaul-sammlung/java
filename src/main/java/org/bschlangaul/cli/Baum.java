package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "baum", aliases = {
    "b" }, mixinStandardHelpOptions = true, description = "Führe baumspezifische Aufgaben aus.")
class Baum implements Callable<Integer> {

  @Option(names = { "-a", "--avl", "--avl-baum" }, description = "Als AVL-Baum ausgeben.")
  boolean isAvl;

  @Parameters(arity = "1..*", description = "Zahlen, die in den Baum eingefügt werden sollen.")
  List<Integer> werte;

  @Override
  public Integer call() {
    for (int i = 0; i < werte.size(); i++) {
      System.out.println(werte.get(i));
    }
    System.out.println(isAvl);
    return 0;
  }
}
