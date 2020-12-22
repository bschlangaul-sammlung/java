package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "baum", aliases = {
    "b" }, mixinStandardHelpOptions = true, description = "Führe baumspezifische Aufgaben aus.", subcommands = {
        GraphTex.class, GraphEinfach.class })
class Baum implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    return 0;
  }
}
