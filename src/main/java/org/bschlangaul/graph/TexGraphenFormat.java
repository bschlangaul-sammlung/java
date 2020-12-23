package org.bschlangaul.graph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TexGraphenFormat {

  EinfachesGraphenFormat graph;

  public TexGraphenFormat(String texEingabe) {
    graph = new EinfachesGraphenFormat();
    Pattern pattern = Pattern.compile("\\\\graph knoten \\{(?<knoten>.*?)\n\\} kanten \\{(?<kanten>.*)\n\\}",
        Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(texEingabe);
    while (ergebnis.find()) {
      verarbeiteKnoten(ergebnis.group("knoten"));
      verarbeiteKanten(ergebnis.group("kanten"));
    }
    graph.gibAusFürKommandozeile();
    System.out.println(graph);
  }

  private String[] trenneZeilen(String texEingabe) {
    return texEingabe.split("[\\r\\n]+");
  }

  private void verarbeiteKnoten(String texEingabe) {
    for (String zeile : trenneZeilen(texEingabe)) {
      Matcher ergebnis = finde("\\\\knoten\\{(?<name>\\w*?)\\}\\((?<x>\\d+),(?<y>\\d+)\\)", zeile);
      if (ergebnis.find()) {
        graph.fügeKnotenEin(ergebnis.group("name"), ergebnis.group("x"), ergebnis.group("y"));
      }
    }
  }

  private void verarbeiteKanten(String texEingabe) {
    for (String zeile : trenneZeilen(texEingabe)) {
      Matcher ergebnis = finde(
          "\\\\(?<makro>kante|kanteR|kanteO|KANTE)\\((?<von>\\w*?)-(?<nach>\\w*?)\\)\\{$?(?<gewicht>\\d+)$?\\}", zeile);
      if (ergebnis.find()) {
        boolean gerichtet = false;
        if (ergebnis.group("makro") == "kanteR")
          gerichtet = true;
        graph.fügeKanteEin(ergebnis.group("von"), ergebnis.group("nach"), ergebnis.group("gewicht"), gerichtet);
      }
    }
  }

  private Matcher finde(String regex, String suchString) {
    Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
    return pattern.matcher(suchString);
  }

}
