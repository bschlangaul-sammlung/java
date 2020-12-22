package org.bschlangaul.graph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TexDateiUntersucher {

  public static void untersucheInhalt(String inhalt) {
    Pattern pattern = Pattern.compile("\\\\begin\\{liEinfachesGraphenFormat\\}(?<format>.*?)\\\\end\\{liEinfachesGraphenFormat\\}", Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(inhalt);
    while(ergebnis.find()) {
      System.out.print(ergebnis.group("format"));
   }
  }
}
