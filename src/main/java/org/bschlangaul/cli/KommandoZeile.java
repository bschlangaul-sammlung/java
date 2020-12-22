package org.bschlangaul.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "didaktik", mixinStandardHelpOptions = true, version = "didaktik 0.1.0", description = "Kommandozeilen-Interface für die Java-Didaktik-Beispiele.", subcommands = {
    Baum.class, Graph.class })
class KommandoZeile implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    return 0;
  }

  public static void main(String... args) {
    int exitCode = new CommandLine(new KommandoZeile()).execute(args);
    System.exit(exitCode);
  }
}
