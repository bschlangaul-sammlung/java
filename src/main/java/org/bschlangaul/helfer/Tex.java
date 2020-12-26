package org.bschlangaul.helfer;

public class Tex {

  public static String makro(String name, String inhalt) {
    return String.format("\\%s{%s}", name, inhalt);
  }

  public static String umgebung(String name, String inhalt) {
    return String.format("%s\n%s\n%s", makro("begin", name), reinigeInhalt(inhalt), makro("end", name));
  }

  private static String reinigeInhalt(String inhalt) {
    return inhalt.replaceFirst("\\s+$", "");
  }

  public static String umgebung(String name, String inhalt, String option) {
    return String.format("%s[%s]\n%s\n%s", makro("begin", name), option, reinigeInhalt(inhalt), makro("end", name));
  }

}
