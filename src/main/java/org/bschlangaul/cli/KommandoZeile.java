package org.bschlangaul.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ScopeType;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Model.UsageMessageSpec.SECTION_KEY_COMMAND_LIST;

enum Ausgabe {
  konsole, tex
}

@Command(name = "didaktik", mixinStandardHelpOptions = true, version = "didaktik 0.1.0", description = "Kommandozeilen-Interface für die Java-Didaktik-Beispiele.", subcommands = {
    UnterBefehlBaum.class, UnterBefehlGraph.class, UnterBefehlProjektSprachenFinder.class })
class KommandoZeile implements Callable<Integer> {
  @Spec
  CommandSpec spec;

  @Option(names = { "-v", "--redselig" }, scope = ScopeType.INHERIT)
  static boolean[] redselig;

  @Option(names = { "-a", "--ausgabe" }, description = "Mögliche Werte: ${COMPLETION-CANDIDATES}")
  static Ausgabe ausgabe;

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    new CommandLine(spec).usage(System.out);
    return 0;
  }

  static int gibRedseligkeit() {
    if (redselig == null) return 0;
    return redselig.length;
  }

  public static void main(String... args) {
    CommandLine cmd = new CommandLine(new KommandoZeile());
    cmd.getHelpSectionMap().put(SECTION_KEY_COMMAND_LIST, new HilfeUnterBefehltsListe());

    int exitCode = cmd.execute(args);
    System.exit(exitCode);
  }
}
