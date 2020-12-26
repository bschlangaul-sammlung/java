package org.bschlangaul.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Model.UsageMessageSpec.SECTION_KEY_COMMAND_LIST;

@Command(name = "didaktik", mixinStandardHelpOptions = true, version = "didaktik 0.1.0", description = "Kommandozeilen-Interface für die Java-Didaktik-Beispiele.", subcommands = {
    Baum.class, Graph.class })
class KommandoZeile implements Callable<Integer> {
  @Spec
  CommandSpec spec;

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    new CommandLine(spec).usage(System.out);
    return 0;
  }

  public static void main(String... args) {
    CommandLine cmd = new CommandLine(new KommandoZeile());
    cmd.getHelpSectionMap().put(SECTION_KEY_COMMAND_LIST, new SubkommandosListe());

    int exitCode = cmd.execute(args);
    System.exit(exitCode);
  }
}
