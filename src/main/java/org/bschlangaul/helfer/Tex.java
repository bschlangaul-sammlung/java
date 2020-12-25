package org.bschlangaul.helfer;

public class Tex {

  public static String makro(String name, String inhalt) {
    return String.format("\\%s{%s}", name, inhalt);
  }

  public static String umgebung(String name, String inhalt) {
    inhalt = inhalt.replaceFirst("\\s+$", "");
    return String.format("%s\n%s\n%s", makro("begin", name), inhalt, makro("end", name));
  }

}
