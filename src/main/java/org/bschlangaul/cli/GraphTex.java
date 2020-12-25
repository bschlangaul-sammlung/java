package org.bschlangaul.cli;

import org.bschlangaul.helfer.Tex;
import org.bschlangaul.graph.TexDateiUntersucher;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.io.File;
import java.nio.file.Files;
import org.bschlangaul.graph.TexGraphenFormat;

@Command(name = "tex-graphen-format", aliases = {
    "t" }, description = "Lese TeX-Datei ein, die Graphen im TeX-Graphenformat (\\graph knoten {} kanten {}) enthält und wandle ihn in das einfache Graphenformat um.")
class GraphTex implements Callable<Integer> {

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  @Override
  public Integer call() throws Exception {
    String inhalt = Files.readString(datei.toPath());
    TexGraphenFormat t = new TexGraphenFormat(inhalt);
    String einfachesGraphenFormat = t.gibEinfachesGraphenFormat();
    System.out.println(Tex.umgebung(TexDateiUntersucher.umgebungsName, einfachesGraphenFormat));
    return 0;
  }
}
