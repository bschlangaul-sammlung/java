package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;

import org.bschlangaul.graph.TexDateiUntersucher;

@Command(name = "automatisch", aliases = {
    "a" }, description = "Lese TeX-Datei ein und versuche einen Graph zu erkennen, formatiere den Graphen dann für TeX.")
class GraphAutomatisch implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    String inhalt = Files.readString(datei.toPath());
    new TexDateiUntersucher(inhalt);

    // TexGraphenFormat t = new TexGraphenFormat(inhalt);
    // String einfachesGraphenFormat = t.gibEinfachesGraphenFormat();
    // System.out.println(Tex.umgebung(TexDateiUntersucher.umgebungsName, einfachesGraphenFormat));
    return 0;
  }
}
