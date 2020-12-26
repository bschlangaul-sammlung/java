package org.bschlangaul.graph;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bschlangaul.helfer.Farbe;

/**
 * Suche in einer TeX-Datei nach Graph-Spezifikation (entweder im einfachen
 * Graphenformat oder im TeX-Graphenformat.)
 */
public class TexDateiUntersucher {

  String inhalt;

  String[] einfachesGraphenFormat;

  String[] texGraphenFormat;

  public TexDateiUntersucher(String inhalt) {
    einfachesGraphenFormat = sucheNachEinfachem(inhalt);
    texGraphenFormat = sucheNachTex(inhalt);

    for (String format : einfachesGraphenFormat) {
      führeGraphenFormatAus(new EinfachesGraphenFormat(format));
    }

    for (String format : texGraphenFormat) {
      führeGraphenFormatAus(new EinfachesGraphenFormat(new TexGraphenFormat(format).gibEinfachesGraphenFormat()));
    }
  }

  private void gibÜberschriftAus(String überschrift) {
    System.out.println(String.format("\n%s\n", Farbe.rot(überschrift)));
  }

  public static String umgebungsName = "liEinfachesGraphenFormat";

  private String[] sucheNachEinfachem(String inhalt) {
    Pattern pattern = Pattern.compile(
        "\\\\begin\\{liEinfachesGraphenFormat\\}(?<format>.*?)\\\\end\\{liEinfachesGraphenFormat\\}", Pattern.DOTALL);
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

  private void führeGraphenFormatAus(EinfachesGraphenFormat graph) {
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
