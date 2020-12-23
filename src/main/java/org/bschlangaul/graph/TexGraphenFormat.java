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
      Matcher ergebnis = finde("\\\\knoten\\{?(?<name>\\w*?)\\}?\\((?<x>\\d+),(?<y>\\d+)\\)", zeile);
      if (ergebnis.find()) {
        graph.fügeKnotenEin(ergebnis.group("name"), ergebnis.group("x"), ergebnis.group("y"));
      }
    }
  }

  private void verarbeiteKanten(String texEingabe) {
    for (String zeile : trenneZeilen(texEingabe)) {
      zeile = zeile.trim();
      if (!zeile.equals("")) {
        Matcher ergebnis = null;
        String gewicht = "1";
        boolean gerichtet = false;

        String regexGewicht = "\\{\\$?(?<gewicht>\\d+)\\$?\\}$";

        // \kanteO(#1-#2)
        String regexOhneGewicht = "^\\\\kanteO\\((?<von>\\w*?)-(?<nach>\\w*?)\\)$";
        // \kanteR($1>$3){$2}
        String regexGerichtet = "^\\\\kanteR\\((?<von>\\w*?)>(?<nach>\\w*?)\\)" + regexGewicht;
        // \kante($1-$3){$2}
        // \KANTE(#1-#2)#3
        String regexNormal = "^\\\\(kante|KANTE)\\((?<von>\\w*?)-(?<nach>\\w*?)\\)" + regexGewicht;
        if (zeile.matches(regexOhneGewicht)) {
          ergebnis = finde(regexOhneGewicht, zeile, true);
        } else if (zeile.matches(regexGerichtet)) {
          ergebnis = finde(regexGerichtet, zeile, true);
          gerichtet = true;
          gewicht = ergebnis.group("gewicht");
        } else if (zeile.matches(regexNormal)) {
          ergebnis = finde(regexNormal, zeile, true);
          gewicht = ergebnis.group("gewicht");
        } else {
          System.out.println(String.format("Diese Zeile kann nicht erkannt werden: %s", zeile));
        }
        if (ergebnis != null)
          graph.fügeKanteEin(ergebnis.group("von"), ergebnis.group("nach"), gewicht, gerichtet);
      }
    }
  }

  private Matcher finde(String regex, String suchString) {
    return finde(regex, suchString, false);
  }

  private Matcher finde(String regex, String suchString, boolean rufeFindAuf) {
    Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(suchString);
    if (rufeFindAuf)
      ergebnis.find();
    return ergebnis;
  }

  public String gibEinfachesGraphenFormat() {
    return graph.toString();
  }

}
