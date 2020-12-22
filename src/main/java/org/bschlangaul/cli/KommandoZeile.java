package org.bschlangaul.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.TexDateiUntersucher;
import org.bschlangaul.graph.TexGraphenFormat;

@Command(name = "einfaches-graphen-format", aliases = {
    "e" }, description = "Lese TeX-Datei ein, die Graphen im einfachen Graphenformat enthält.")
class KommandoEinfachesGraphenFormat implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    String inhalt = Files.readString(datei.toPath());
    TexDateiUntersucher.untersucheInhalt(inhalt);
    return 0;
  }
}

@Command(name = "tex-graphen-format", aliases = {
  "t" }, description = "Lese TeX-Datei ein, die Graphen im TeX-Graphenformat enthält.")
class KommandoTexGraphenFormat implements Callable<Integer> {

@Parameters(index = "0", description = "Eine TeX-Datei.")
private File datei;

@Override
public Integer call() throws Exception {
  String inhalt = Files.readString(datei.toPath());
  new TexGraphenFormat(inhalt);
  return 0;
}
}

@Command(name = "graph", aliases = { "g" }, description = "Führe graphspezifische Aufgaben aus.", subcommands = {
    KommandoTexGraphenFormat.class, KommandoEinfachesGraphenFormat.class })
class Graph implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("Benutze eine Unterkommando!");
    return 0;
  }
}

@Command(name = "didaktik", mixinStandardHelpOptions = true, version = "didaktik 0.1.0", description = "Kommandozeilen-Interface für die Java-Didaktik-Beispiele.", subcommands = {
    Graph.class })
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
