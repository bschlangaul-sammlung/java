package org.bschlangaul.graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bschlangaul.helfer.Farbe;
import org.bschlangaul.graph.einfaches_format.GraphenFormat;
import org.bschlangaul.graph.tex.TexAdjazenzListe;
import org.bschlangaul.graph.tex.TexAdjazenzMatrix;
import org.bschlangaul.graph.tex.TexGraphenFormat;
import org.bschlangaul.graph.tex.TexTikz;

/**
 * Suche in einer Text oder TeX-Datei nach Graph-Spezifikation (entweder im
 * einfachen Graphenformat oder im TeX-Graphenformat.)
 */
public class GraphenFinder {

  private GraphenFormat[] graphen;

  public GraphenFinder(File texDatei) {
    String[] einfachesGraphenFormat;

    String[] texGraphenFormat;

    String inhalt;
    try {
      inhalt = Files.readString(texDatei.toPath());
      einfachesGraphenFormat = sucheNachEinfachem(inhalt);
      texGraphenFormat = sucheNachTex(inhalt);

      graphen = new GraphenFormat[einfachesGraphenFormat.length + texGraphenFormat.length];

      int i = 0;

      for (String format : einfachesGraphenFormat) {
        graphen[i] = new GraphenFormat(format);
        i++;
      }

      for (String format : texGraphenFormat) {
        graphen[i] = new GraphenFormat(new TexGraphenFormat(format).gibGraphenFormat());
        i++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gib die erste gefundene Graphdefinition aus.
   *
   * @return Die erste gefundene Graphendefintion im einfachen Graphenformat.
   */
  public GraphenFormat gibGraphenFormat() {
    return graphen[0];
  }

  public String gibGraphenFormatText() {
    return gibGraphenFormat().toString();
  }

  private void gibÜberschriftAus(String überschrift) {
    System.out.println(String.format("\n%s\n", Farbe.rot(überschrift)));
  }

  public static String umgebungsName = "liGraphenFormat";

  private String[] sucheNachEinfachem(String inhalt) {
    Pattern pattern = Pattern.compile(
        "\\\\begin\\{" + umgebungsName + "\\}(?<format>.*?)\\\\end\\{" + umgebungsName + "\\}", Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(inhalt);
    ArrayList<String> ausgabe = new ArrayList<String>();
    while (ergebnis.find()) {
      ausgabe.add(ergebnis.group("format"));
    }
    return ausgabe.toArray(new String[0]);
  }

  private String[] sucheNachTex(String inhalt) {
    Pattern pattern = Pattern.compile(TexGraphenFormat.globalerRegex, Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(inhalt);
    ArrayList<String> ausgabe = new ArrayList<String>();
    while (ergebnis.find()) {
      ausgabe.add(ergebnis.group(0));
    }
    return ausgabe.toArray(new String[0]);
  }

  public void gibTexAus() {
    for (int i = 0; i < graphen.length; i++) {
      gibPerGraphTexAus(graphen[i]);
    }
  }

  private void gibPerGraphTexAus(GraphenFormat graph) {
    gibÜberschriftAus("Einfaches Graphen-Format zum Einbetten");
    System.out.println(graph.gibAlsTexUmgebung());

    gibÜberschriftAus("Als TikZ-Umgebung");
    System.out.println(new TexTikz(graph).gibTikzUmgebung());

    gibÜberschriftAus("Adjazenz-Matrix");
    GraphAdjazenzMatrix matrix = new GraphAdjazenzMatrix(graph.toString());
    System.out.println(new TexAdjazenzMatrix(matrix).gibTexAusgabe());

    gibÜberschriftAus("Adjazenz-Liste");
    GraphAdjazenzListe liste = new GraphAdjazenzListe(graph.toString());
    System.out.println(new TexAdjazenzListe(liste).gibTexAusgabe());
  }
}
