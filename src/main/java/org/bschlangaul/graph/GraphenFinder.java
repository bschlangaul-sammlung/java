package org.bschlangaul.graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.bschlangaul.helfer.Farbe;
import org.bschlangaul.helfer.TextAusschnitt;
import org.bschlangaul.graph.einfaches_format.GraphenFormat;
import org.bschlangaul.graph.tex.TexAdjazenzListe;
import org.bschlangaul.graph.tex.TexAdjazenzMatrix;
import org.bschlangaul.graph.tex.TexTikz;

/**
 * Suche in einer Text oder TeX-Datei nach Graph-Spezifikation (entweder im
 * einfachen Graphenformat oder im TeX-Graphenformat.)
 */
public class GraphenFinder {

  private GraphenFormat[] graphen;

  public GraphenFinder(File texDatei) {
    String[] einfachesGraphenFormat;

    String inhalt;
    try {
      inhalt = Files.readString(texDatei.toPath());
      einfachesGraphenFormat = sucheNachEinfachem(inhalt);

      graphen = new GraphenFormat[einfachesGraphenFormat.length];

      int i = 0;
      for (String format : einfachesGraphenFormat) {
        graphen[i] = new GraphenFormat(format);
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

  /**
   * Gib alle gefundene Graphdefinition aus.
   *
   * @return Alle gefundenen Graphendefintion im einfachen Graphenformat.
   */
  public GraphenFormat[] gibAlleGraphenFormate() {
    return graphen;
  }

  public String gibGraphenFormatText() {
    return gibGraphenFormat().toString();
  }

  private void gibÜberschriftAus(String überschrift) {
    System.out.println(String.format("\n%s\n", überschrift));
  }

  public static String umgebungsName = "bGraphenFormat";

  private String[] sucheNachEinfachem(String inhalt) {
    return TextAusschnitt.sucheUmgebung(inhalt, umgebungsName).toArray(new String[0]);
  }

  public void gibTexAus() {
    for (int i = 0; i < graphen.length; i++) {
      gibPerGraphTexAus(graphen[i]);
    }
  }

  private void gibPerGraphTexAus(GraphenFormat graph) {
    gibÜberschriftAus(Farbe.rot("Einfaches Graphen-Format zum Einbetten"));
    System.out.println(graph.gibAlsTexUmgebung());

    gibÜberschriftAus(Farbe.grün("Als TikZ-Umgebung"));
    System.out.println(new TexTikz(graph).gibTikzUmgebung());

    gibÜberschriftAus(Farbe.grün("Adjazenz-Matrix"));
    GraphAdjazenzMatrix matrix = new GraphAdjazenzMatrix(graph.toString());
    System.out.println(new TexAdjazenzMatrix(matrix).gibTexAusgabe());

    gibÜberschriftAus(Farbe.grün("Adjazenz-Liste"));
    GraphAdjazenzListe liste = new GraphAdjazenzListe(graph.toString());
    System.out.println(new TexAdjazenzListe(liste).gibTexAusgabe());
    System.out.println("\n\n");
  }
}
