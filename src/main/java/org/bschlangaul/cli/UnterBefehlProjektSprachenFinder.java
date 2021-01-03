package org.bschlangaul.cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import org.bschlangaul.baum.BaumFormat;
import org.bschlangaul.db.RelationenSchema;
import org.bschlangaul.helfer.DateiSchreiber;
import org.bschlangaul.helfer.Tex;

class ProjektSprache {

  String name;
  String inhalt;

  public ProjektSprache(String name, String inhalt) {
    this.name = name.trim();
    this.inhalt = inhalt.trim();
  }
}

@Command(name = "projekt-sprachen", aliases = {
    "s" }, description = "Nach Projektsprachen in einer TeX-Datei suchen und diese dann ausführen.")
public class UnterBefehlProjektSprachenFinder implements Callable<Integer> {

  String umgebungsName = "liProjektSprache";

  @Parameters(index = "0", description = "Eine TeX-Datei.")
  private File datei;

  private String gibTexMakroRegex(String name, String inhalt) {
    return String.format("\\\\%s\\{%s\\}", name, inhalt);
  }

  private String gibTexUmgebungRegex(String name, String inhalt) {
    return gibTexMakroRegex("begin", name) + inhalt + gibTexMakroRegex("end", name);
  }

  private String kompiliereSprache(String name, String inhalt) {
    switch (name) {
      case "RelationenSchema":
        return RelationenSchema.gibAusFürEinbettung(inhalt);

      case "Baum":
        return BaumFormat.gibAusFürEinbettung(inhalt);

      default:
        break;
    }
    return null;
  }

  private String gibErsetzung(String sprachenName, String sprachenInhalt) {
    sprachenInhalt = sprachenInhalt.trim();
    sprachenName = sprachenName.trim();
    String kompilat = kompiliereSprache(sprachenName, sprachenInhalt);
    return Tex.umgebungArgument(umgebungsName, sprachenInhalt, sprachenName) + "\n"
        + Tex.umgebung("liEinbettung", kompilat);
  }

  /**
   * Suche nach in TeX-Dateien eingebundenen Projektsprachen.
   *
   * @param datei Eine TeX-Datei.
   *
   * @return Ein Feld mit ProjektSprachen-Objekten.
   */
  private ProjektSprache[] sucheNachSprachen(File datei) {
    ArrayList<ProjektSprache> ausgabe = new ArrayList<ProjektSprache>();

    StringBuilder sb = new StringBuilder();

    try {
      String inhalt = Files.readString(datei.toPath());
      String regexProjektSprache = gibTexUmgebungRegex(umgebungsName, "\\{(?<name>.*?)\\}" + "(?<inhalt>.*?)");
      String regexEinbettung = "([\s\n\r]*?" + gibTexUmgebungRegex("liEinbettung", ".*?") + ")?";
      Pattern pattern = Pattern.compile(regexProjektSprache + regexEinbettung, Pattern.DOTALL);
      Matcher ergebnis = pattern.matcher(inhalt);
      while (ergebnis.find()) {
        ausgabe.add(new ProjektSprache(ergebnis.group("name"), ergebnis.group("inhalt")));
        ergebnis.appendReplacement(sb,
        Matcher.quoteReplacement(gibErsetzung(ergebnis.group("name"), ergebnis.group("inhalt"))));
      }
      ergebnis.appendTail(sb);
      System.out.println(sb.toString());
      DateiSchreiber.schreibe(datei.toString(), sb.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

    ProjektSprache[] sprachen = new ProjektSprache[ausgabe.size()];

    ausgabe.toArray(sprachen);
    return sprachen;
  }

  @Override
  public Integer call() throws Exception {
    for (ProjektSprache sprache : sucheNachSprachen(datei)) {
      switch (sprache.name) {
        case "RelationenSchema":
          // RelationenSchema.gibAusFürProjektSprachen(sprache.inhalt);
          break;

        case "Baum":
          // BaumFormat.gibAusFürProjektSprachen(sprache.inhalt);

          break;

        default:
          break;
      }

    }
    return 0;
  }
}
