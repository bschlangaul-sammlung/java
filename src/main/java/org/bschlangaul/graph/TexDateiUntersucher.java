package org.bschlangaul.graph;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      System.out.println(new EinfachesGraphenFormat(format));
    }

    for (String format : texGraphenFormat) {
      System.out.println(format);
      System.out.println(new TexGraphenFormat(format));
    }
  }

  public static String umgebungsName = "liEinfachesGraphenFormat";

  public String[] sucheNachEinfachem(String inhalt) {
    Pattern pattern = Pattern.compile(
        "\\\\begin\\{liEinfachesGraphenFormat\\}(?<format>.*?)\\\\end\\{liEinfachesGraphenFormat\\}", Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(inhalt);
    ArrayList<String> ausgabe = new ArrayList<String>();
    while (ergebnis.find()) {
      ausgabe.add(ergebnis.group("format"));
    }
    return ausgabe.toArray(new String[0]);
  }

  public String[] sucheNachTex(String inhalt) {
    Pattern pattern = Pattern.compile(TexGraphenFormat.globalerRegex, Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(inhalt);
    ArrayList<String> ausgabe = new ArrayList<String>();
    while (ergebnis.find()) {
      ausgabe.add(ergebnis.group(0));
    }
    return ausgabe.toArray(new String[0]);
  }
}
